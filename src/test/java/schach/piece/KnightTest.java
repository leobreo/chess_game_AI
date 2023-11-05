package schach.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schach.Chessboard;
import schach.Position;
import schach.TestUtils;
import schach.move.BasicMove;

/**
 * Tests for {@link Knight}
 */
public class KnightTest {
    Chessboard board;

    /**
     * Set up a chessboard to execute tests on.
     */
    @BeforeEach
    public void setUp() {
        var boardStr = ""
            + ". . . . . . . k\n"
            + ". . . . . . . .\n"
            + ". . . . . P . .\n"
            + ". . . . . . . .\n"
            + ". . . n n . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . p . .\n"
            + "K . . . . . . .";

        board = TestUtils.parseBoard(boardStr);
    }

    private boolean reachable(Position start, Position target) {
        var knight = board.getPiece(start).get();
        var move = new BasicMove(start, target);
        return knight.generatePseudoLegalMoves(board, start).contains(move);
    }

    /**
     * Tests if all reachable squares in the double upper right direction are accurate.
     */
    @Test
    public void testReachableUpUpRight() {
        var start = new Position(4, 4);
        var target = new Position(5, 6);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the upper double right direction are accurate.
     */
    @Test
    public void testReachableUpRightRight() {
        var start = new Position(4, 4);
        var target = new Position(6, 5);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the lower double right direction are accurate.
     */
    @Test
    public void testReachableDownRightRight() {
        var start = new Position(4, 4);
        var target = new Position(6, 3);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the double lower right direction are accurate.
     */
    @Test
    public void testReachableDownDownRight() {
        var start = new Position(4, 4);
        var target = new Position(2, 5);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the double lower left direction are accurate.
     */
    @Test
    public void testReachableDownDownLeft() {
        var start = new Position(4, 4);
        var target = new Position(3, 2);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the lower double left direction are accurate.
     */
    @Test
    public void testReachableDownLeftLeft() {
        var start = new Position(4, 4);
        var target = new Position(2, 3);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the upper double left direction are accurate.
     */
    @Test
    public void testReachableUpLeftLeft() {
        var start = new Position(4, 4);
        var target = new Position(2, 5);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the double upper left direction are accurate.
     */
    @Test
    public void testReachableUpUpLeft() {
        var start = new Position(4, 4);
        var target = new Position(3, 6);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if path is blocked by a piece of own color
     */
    @Test
    public void testIfPathBlockedByOwn() {
        var start = new Position(4, 5);
        var target = new Position(2, 6);
        var knight = board.getPiece(start).get();
        var move = new BasicMove(start, target);
        assertFalse(knight.generatePseudoLegalMoves(board, start).contains(move));
    }

    /**
     * Tests if a knight actiually beats a piece it can legally beat.
     */
    @Test
    public void testBeat() {
        var start = new Position(4, 5);
        var target = new Position(6, 6);
        var knight = board.getPiece(start).get();
        var move = new BasicMove(start, target);
        assertTrue(knight.generatePseudoLegalMoves(board, start).contains(move));
    }
}
