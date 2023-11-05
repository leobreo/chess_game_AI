package schach.move;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import schach.Chessboard;
import schach.Position;
import schach.piece.Piece.Color;

/**
 * Representation of a castling move
 *
 * @author J.Horbank, K. Schweppe
 * @version 1.2
 */
public class CastlingMove extends Move {
    private final Position rookSource, rookTarget;
    private final CastlingType type;

    public enum CastlingType {
        Short(1),
        Long(-1);

        private int direction;

        CastlingType(int direction) {
            this.direction = direction;
        }

        public int direction() {
            return direction;
        }
    }

    /**
     * Create a CastlingMove
     *
     * @param source source position
     * @param target target position
     */
    public CastlingMove(Position source, Position target) {
        super(source, target);
        type = source.getCol() < target.getCol() ? CastlingType.Short : CastlingType.Long;
        this.rookSource = type == CastlingType.Short ? source.offset(0, 3) : source.offset(0, -4);
        this.rookTarget = type == CastlingType.Short ? source.offset(0, 1) : source.offset(0, -1);
    }

    @Override
    public void doMove(Chessboard board) {
        board.setPiece(target, board.getPiece(source));
        board.setPiece(source, Optional.empty());

        board.setPiece(rookTarget, board.getPiece(rookSource));
        board.setPiece(rookSource, Optional.empty());

        board.getPiece(target).ifPresent(p -> p.onMoved(board, target, 1));
        board.getPiece(rookTarget).ifPresent(p -> p.onMoved(board, rookTarget, 1));
    }

    @Override
    public void undoMove(Chessboard board) {
        board.setPiece(source, board.getPiece(target));
        board.setPiece(target, Optional.empty());

        board.setPiece(rookSource, board.getPiece(rookTarget));
        board.setPiece(rookTarget, Optional.empty());

        board.getPiece(source).ifPresent(p -> p.onMoved(board, source, -1));
        board.getPiece(rookSource).ifPresent(p -> p.onMoved(board, rookSource, -1));
    }

    @Override
    public Set<Position> getNoAttackingAllowed(Color color) {
        var positions = super.getNoAttackingAllowed(color);

        for (int offset = 1; offset <= 2; offset++) {
            positions.add(source.offset(0, offset * type.direction()));
        }

        return positions;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CastlingMove) {
            var move = (CastlingMove) obj;

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
