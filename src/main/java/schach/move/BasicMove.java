package schach.move;

import java.util.Objects;
import java.util.Optional;
import schach.Chessboard;
import schach.Position;

/**
 * The Basic Move
 *
 * @author K. Schweppe
 * @version 1.2
 */
public class BasicMove extends Move {
    private boolean beaten = false;

    /**
     * Create a BaiscMove
     *
     * @param source source position
     * @param target target position
     */
    public BasicMove(Position source, Position target) {
        super(source, target);
    }

    @Override
    public void doMove(Chessboard board) {
        beaten = board.capture(target).isPresent();

        board.setPiece(target, board.getPiece(source));
        board.setPiece(source, Optional.empty());

        board.getPiece(target).ifPresent(p -> p.onMoved(board, target, 1));
    }

    @Override
    public void undoMove(Chessboard board) {
        board.setPiece(source, board.getPiece(target));

        if (beaten) {
            board.setPiece(target, Optional.of(board.takeLastBeaten()));
        } else {
            board.setPiece(target, Optional.empty());
        }

        board.getPiece(source).ifPresent(p -> p.onMoved(board, source, -1));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BasicMove) {
            var move = (BasicMove) obj;

            if (source.equals(move.getSource()) && target.equals(move.getTarget())) {
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
