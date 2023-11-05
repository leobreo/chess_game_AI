package schach;

import java.util.Objects;
import javafx.geometry.BoundingBox;

/**
 * The position class.
 *
 * @author K. Schweppe, J. Horbank
 * @version 1.2
 */
public class Position {
    private final int row, col;

    /**
     * Creates a Position
     *
     * @param row The row
     * @param col The column
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * @return col
     */
    public int getCol() {
        return col;
    }

    /**
     * @param rowOffset Row offset
     * @param colOffset Column offset
     *
     * @return new Position with offset
     */
    public Position offset(int rowOffset, int colOffset) {
        return new Position(row + rowOffset, col + colOffset);
    }

    /**
     * @return if position is on board.
     */
    public boolean isOnBoard() {
        var boardBounds = new BoundingBox(1, 1, 7, 7);
        return boardBounds.contains(row, col);
    }

    // for use in Sets
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Position)) {
            return false;
        }

        var otherPosition = (Position) other;
        return otherPosition.row == row && otherPosition.col == col;
    }

    // for use in Sets
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return String.format("%d,%d", row, col);
    }
}
