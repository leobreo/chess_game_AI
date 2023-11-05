package schach.piece;

import java.util.Set;
import schach.Chessboard;
import schach.Position;
import schach.Symbols;
import schach.move.Move;

/**
 * The Knight class:
 *
 * @author J. Horbank, L. Brenk
 * @version 1.0
 */
public class Knight extends Piece {
    private final int[][] offsets = {
        {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    /**
     * Creates a Knight
     *
     * @param color the color
     */
    public Knight(Color color) {
        super(color);
        value = 3;
    }

    @Override
    public Set<Move> generatePseudoLegalMoves(Chessboard board, Position current) {
        return generateJumpingMoves(board, current, offsets);
    }

    @Override
    public <S> S getSymbol(Symbols<S> symbols) {
        return symbols.knight(getColor());
    }
}
