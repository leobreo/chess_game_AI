package schach.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schach.Chessboard;
import schach.Position;
import schach.TestUtils;
import schach.move.BasicMove;

/**
 * Tests for {@link Bishop}
 */
public class BishopTest {
    Chessboard board;

    /**
     * Set up a chessboard to execute tests on
     */
    @BeforeEach
    public void setUp() {
        var boardStr = ""
            + ". . . . . . . k\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . b . . .\n"
            + ". . . B . . . .\n"
            + ". . . . . b . .\n"
            + ". . . . . . . .\n"
            + "K . . . . . . B";

        board = TestUtils.parseBoard(boardStr);
    }

    private boolean reachable(Position start, Position target) {
        var bishop = board.getPiece(start).get();
        var move = new BasicMove(start, target);
        return bishop.generatePseudoLegalMoves(board, start).contains(move);
    }

    /**
     * Tests if all reachable squares in the upper right direction are accurate.
     */
    @Test
    public void testReachableUpRight() {
        var start = new Position(4, 4);
        var target = new Position(5, 5);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the upper left direction are accurate.
     */
    @Test
    public void testReachableUpLeft() {
        var start = new Position(4, 4);
        var target = new Position(5, 3);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the lower left direction are accurate.
     */
    @Test
    public void testReachableDownLeft() {
        var start = new Position(4, 4);
        var target = new Position(3, 3);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the lower right direction are accurate.
     */
    @Test
    public void testReachableDownRight() {
        var start = new Position(4, 4);
        var target = new Position(3, 5);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if path is blocked by an enemy.
     */
    @Test
    public void testIfPathBlockedbyEnemy() {
        var start = new Position(1, 8);
        var target = new Position(8, 1);
        assertFalse(reachable(start, target));
    }

    /**
     * Tests if path is blocked by a piece of your own color.
     */
    @Test
    public void testIfPathBlockedbyOwn() {
        var start = new Position(4, 4);
        var target = new Position(1, 1);
        assertFalse(reachable(start, target));
    }

    /**
     * Tests if a bishop actually beats a piece it can legally beat.
     */
    @Test
    public void testBeat() {
        var start = new Position(1, 8);
        var target = new Position(3, 6);
        assertTrue(reachable(start, target));
    }
}
