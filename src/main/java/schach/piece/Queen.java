package schach.piece;

import java.util.HashSet;
import java.util.Set;
import schach.Chessboard;
import schach.Position;
import schach.Symbols;
import schach.move.Move;

/**
 * The Queen class
 *
 * @author J. Horbank, L. Brenk
 * @version 1.0
 */
public class Queen extends Piece {
    /**
     * Creates a Queen
     *
     * @param color the color
     */
    public Queen(Color color) {
        super(color);
        value = 9;
    }

    @Override
    public Set<Move> generatePseudoLegalMoves(Chessboard board, Position current) {
        var moves = new HashSet<Move>();

        moves.addAll(generateStraightSlidingMoves(board, current));
        moves.addAll(generateDiagonalSlidingMoves(board, current));

        return moves;
    }

    @Override
    public <S> S getSymbol(Symbols<S> symbols) {
        return symbols.queen(getColor());
    }
}
