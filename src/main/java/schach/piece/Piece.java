package schach.piece;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import schach.Chessboard;
import schach.Position;
import schach.Symbols;
import schach.move.BasicMove;
import schach.move.Move;

/**
 * Abstract representation of a Piece
 *
 * @author K. Schweppe, J. Horbank
 * @version 1.3
 */
public abstract class Piece {
    /**
     * Chess Player Colors
     */
    public enum Color {
        White,
        Black;

        /**
         * Get the opposing Color
         *
         * @return color
         */
        public Color opponent() {
            return this == White ? Black : White;
        }
    }

    /**
     * Types of Row Movement
     */
    private enum RowMoveType { Up, Stay, Down }

    /**
     * Types of Column Movement
     */
    private enum ColMoveType { Left, Stay, Right }

    private final Color color;
    private int moveCount;
    protected int value;

    /**
     * Constructor
     *
     * @param color the Color
     */
    public Piece(Color color) {
        this.color = color;
        this.moveCount = 0;
    }

    /**
     * @return Value of this piece
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Get the symbol for this piece
     *
     * @param symbols Symbols interface
     * @param <S> Symbol type
     * @return A Symbol
     */
    public abstract <S> S getSymbol(Symbols<S> symbols);

    /**
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets count: how often has that piece moved yet
     *
     * @return move count
     */
    public int getMoveCount() {
        return moveCount;
    }

    /**
     * The piece has been moved to a new position
     *
     * @param board The board
     * @param target New position
     * @param moveCount Move count difference, may be negative
     */
    public void onMoved(Chessboard board, Position target, int moveCount) {
        this.moveCount += moveCount;
    }

    /**
     * Calculates possible positions to move to based on the piece rules
     *
     * @param board   the board
     * @param current the current position of the piece
     * @return A map of positions to possible moves
     */
    public abstract Set<Move> generatePseudoLegalMoves(Chessboard board, Position current);

    /**
     * Check if a piece can be moved to a target position from its initial position
     *
     * @param board    the board
     * @param pos      the position to be checked
     * @param previous the position the piece has moved on right before moving to
     *                 'pos'. For pieces that jump(Knight) and pieces that only move
     *                 1 square, this is the starting position. For pieces that move
     *                 in a line, this has to be the position the piece had to move
     *                 through right before reaching 'pos'.
     * @return true if the piece can be placed
     */
    protected boolean canMoveToPosition(Chessboard board, Position pos, Position previous) {
        if (!pos.isOnBoard()) {
            return false;
        } else if (board.getPiece(previous).isPresent() && board.getPiece(previous).get() != this) {
            return false;
        } else if (board.getPiece(pos, getColor()).isPresent()) {
            return false;
        }
        return true;
    }

    /**
     * Calculates a set of possible move-ending positions for pieces that 'slide'.
     *
     * @param board        the board.
     * @param start        the current of the piece.
     * @param rowDirection the move direction on the row.
     * @param colDirection the move direction on the column.
     * @return A map of positions to possible moves.
     */
    private Set<Move> generateMovesForDirection(
        Chessboard board, Position start, RowMoveType rowDirection, ColMoveType colDirection) {
        var moves = new HashSet<Move>();

        var pre = start;

        for (int offset = 1; offset < 8; offset++) {
            int rowOffset = offset * (rowDirection.ordinal() - 1);
            int colOffset = offset * (colDirection.ordinal() - 1);

            var pos = start.offset(rowOffset, colOffset);

            if (canMoveToPosition(board, pos, pre)) {
                moves.add(new BasicMove(start, pos));
            } else {
                break;
            }

            pre = pos;
        }
        return moves;
    }

    /**
     * Calculates a set of possbile move-ending positions for piece that 'jump'
     *
     * @param board   the board
     * @param start   the start position
     * @param offsets the piece specific offsets.
     * @return Set of moves
     */
    protected Set<Move> generateJumpingMoves(Chessboard board, Position start, int[][] offsets) {
        var moves = new HashSet<Move>();

        Arrays.asList(offsets).forEach(offset -> {
            var target = start.offset(offset[0], offset[1]);

            if (canMoveToPosition(board, target, start)) {
                moves.add(new BasicMove(start, target));
            }
        });
        return moves;
    }

    /**
     * Calculates the horizontal and vertical move lines.
     *
     * @param board the board
     * @param current current position
     * @return Set of moves
     */
    protected Set<Move> generateStraightSlidingMoves(Chessboard board, Position current) {
        var moves = new HashSet<Move>();

        moves.addAll(
            generateMovesForDirection(board, current, RowMoveType.Stay, ColMoveType.Right));
        moves.addAll(generateMovesForDirection(board, current, RowMoveType.Stay, ColMoveType.Left));
        moves.addAll(generateMovesForDirection(board, current, RowMoveType.Up, ColMoveType.Stay));
        moves.addAll(generateMovesForDirection(board, current, RowMoveType.Down, ColMoveType.Stay));

        return moves;
    }

    /**
     * Calculates the diagonal move lines.
     *
     * @param board the board
     * @param current current position
     * @return Set of moves
     */
    protected Set<Move> generateDiagonalSlidingMoves(Chessboard board, Position current) {
        var moves = new HashSet<Move>();

        moves.addAll(generateMovesForDirection(board, current, RowMoveType.Up, ColMoveType.Right));
        moves.addAll(generateMovesForDirection(board, current, RowMoveType.Up, ColMoveType.Left));
        moves.addAll(
            generateMovesForDirection(board, current, RowMoveType.Down, ColMoveType.Right));
        moves.addAll(generateMovesForDirection(board, current, RowMoveType.Down, ColMoveType.Left));

        return moves;
    }
}
