package schach.move;

import java.util.Objects;
import java.util.Optional;
import schach.Chessboard;
import schach.Position;
import schach.cli.ASCIISymbols;
import schach.piece.Piece;
import schach.piece.Queen;

/**
 * Representation of a promotion move
 *
 * @author J.Horbank
 * @version 1.1
 */
public class PromotionMove extends Move {
    private final Piece newPiece;
    private Optional<Piece> oldPiece;
    private boolean beaten = false;

    /**
     * create a PromotionMove
     *
     * @param source source position
     * @param target target position
     * @param newPiece the Piece that the Pawn turns into.
     */
    public PromotionMove(Position source, Position target, Piece newPiece) {
        super(source, target);
        this.newPiece = newPiece;
    }

    @Override
    public void doMove(Chessboard board) {
        oldPiece = board.getPiece(source);

        beaten = board.capture(target).isPresent();

        board.setPiece(target, Optional.of(newPiece));
        board.setPiece(source, Optional.empty());

        board.getPiece(target).ifPresent(p -> p.onMoved(board, target, 1));
    }

    @Override
    public void undoMove(Chessboard board) {
        board.setPiece(source, oldPiece);

        if (beaten) {
            board.setPiece(target, Optional.of(board.takeLastBeaten()));
        } else {
            board.setPiece(target, Optional.empty());
        }

        board.getPiece(source).ifPresent(p -> p.onMoved(board, source, -1));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PromotionMove) {
            var move = (PromotionMove) obj;

            if (source.equals(move.getSource()) && target.equals(move.getTarget())
                && this.newPiece.getClass().equals(move.newPiece.getClass())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }

    @Override
    public String toString() {
        String promoChar = newPiece instanceof Queen
            ? ""
            : Character.toString(newPiece.getSymbol(new ASCIISymbols())).toUpperCase();
        return String.format("%s%s", super.toString(), promoChar);
    }
}
