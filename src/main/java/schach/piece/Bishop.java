package schach.piece;

import java.util.Set;
import schach.Chessboard;
import schach.Position;
import schach.Symbols;
import schach.move.Move;

/**
 * A Bishop:
 *
 * @author J. Horbank, L. Brenk
 * @version 1.0
 */
public class Bishop extends Piece {
    /**
     * Creates a Bishop
     *
     * @param color the color
     */
    public Bishop(Color color) {
        super(color);
        value = 3;
    }

    @Override
    public Set<Move> generatePseudoLegalMoves(Chessboard board, Position current) {
        return generateDiagonalSlidingMoves(board, current);
    }

    @Override
    public <S> S getSymbol(Symbols<S> symbols) {
        return symbols.bishop(getColor());
    }
}
