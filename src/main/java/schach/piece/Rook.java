package schach.piece;

import java.util.Set;
import schach.Chessboard;
import schach.Position;
import schach.Symbols;
import schach.move.Move;

/**
 * The Rook class:
 *
 * @author J. Horbank, L. Brenk
 * @version 1.0
 */
public class Rook extends Piece {
    /**
     * Creates a rook:
     *
     * @param color Color of this piece
     */
    public Rook(Color color) {
        super(color);
        value = 5;
    }

    @Override
    public Set<Move> generatePseudoLegalMoves(Chessboard board, Position current) {
        return generateStraightSlidingMoves(board, current);
    }

    @Override
    public <S> S getSymbol(Symbols<S> symbols) {
        return symbols.rook(getColor());
    }
}
