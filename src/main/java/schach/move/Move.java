package schach.move;

import java.util.HashSet;
import java.util.Set;
import schach.Chessboard;
import schach.Position;
import schach.piece.Piece;

/**
 * Representation of a Move, which is extended by special move classes and implemented by these.
 *
 * @author K. Schweppe
 * @version 1.2
 */
public abstract class Move {
    protected final Position source, target;

    /**
     * Create a Move
     *
     * @param source source position
     * @param target target position
     */
    public Move(Position source, Position target) {
        this.source = source;
        this.target = target;
    }

    /**
     * Return the current source Position object
<<<<<<< HEAD
     *
=======
     * 
>>>>>>> Most of missing JavaDocs
     * @return source Position
     */
    public Position getSource() {
        return this.source;
    }

    /**
     * Return the current target Position object
<<<<<<< HEAD
     *
=======
     * 
>>>>>>> Most of missing JavaDocs
     * @return target Position
     */
    public Position getTarget() {
        return this.target;
    }

    public Set<Position> getNoAttackingAllowed(Piece.Color color) {
        return new HashSet<Position>();
    }

    /**
     * Execute the Move
     *
     * @param board
     */
    public abstract void doMove(Chessboard board);

    /**
     * Undo the Move
     *
     * @param board the board
     */
    public abstract void undoMove(Chessboard board);

    @Override
    public String toString() {
        return String.format("%s%s-%s%s", colToChar(source.getCol()), source.getRow(),
            colToChar(target.getCol()), target.getRow());
    }

    /**
     * Translates columns to numbers
     *
     * @param index col
     * @return char num
     */
    private char colToChar(int index) {
        var horizontal = "abcdefgh";
        return horizontal.charAt(index - 1);
    }
}
