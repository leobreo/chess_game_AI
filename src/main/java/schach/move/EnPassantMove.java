package schach.move;

import java.util.Objects;
import java.util.Optional;
import schach.Chessboard;
import schach.Position;

/**
 * Representation of a en-Passant move
 *
 * @author J. Horbank
 * @version 1.1
 */
public class EnPassantMove extends Move {
    private final Position beating;

    /**
     * Create a new EnPassantMove
     *
     * @param source source position
     * @param target target position
     */
    public EnPassantMove(Position source, Position target) {
        super(source, target);
        this.beating = findBeatingPosition();
    }
    /**
     * @return Position where to beat the opponent with enpassant move
     */
    private Position findBeatingPosition() {
        var fileOffset = target.getCol() - source.getCol();
        return source.offset(0, fileOffset);
    }

    @Override
    public void doMove(Chessboard board) {
        board.capture(beating);
        board.setPiece(target, board.getPiece(source));
        board.setPiece(source, Optional.empty());

        board.getPiece(target).ifPresent(p -> p.onMoved(board, target, 1));
    }

    @Override
    public void undoMove(Chessboard board) {
        board.setPiece(source, board.getPiece(target));
        board.setPiece(target, Optional.empty());
        board.setPiece(beating, Optional.of(board.takeLastBeaten()));

        board.getPiece(source).ifPresent(p -> p.onMoved(board, source, -1));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EnPassantMove) {
            var move = (EnPassantMove) obj;

            if (this.source.equals(move.getSource()) && this.target.equals(move.getTarget())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }
}
