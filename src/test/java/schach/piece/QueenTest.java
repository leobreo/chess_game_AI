package schach.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schach.Chessboard;
import schach.Position;
import schach.TestUtils;
import schach.move.BasicMove;

/**
 * Tests for {@link Queen}
 */
public class QueenTest {
    Chessboard board;

    /**
     * Set up a chessboard to execute tests on.
     */
    @BeforeEach
    public void setUp() {
        var boardStr = ""
            + ". . . . . . . k\n"
            + ". . . . . . . .\n"
            + ". q . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . Q . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . p .\n"
            + "K . . . . . . .";

        board = TestUtils.parseBoard(boardStr);
    }

    private boolean reachable(Position start, Position target) {
        var queen = board.getPiece(start).get();
        var move = new BasicMove(start, target);
        return queen.generatePseudoLegalMoves(board, start).contains(move);
    }

    /**
     * Tests if all reachable squares in the upper direction are accurate.
     */
    @Test
    public void testReachableUp() {
        var start = new Position(4, 4);
        var target = new Position(5, 4);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the lower direction are accurate.
     */
    @Test
    public void testReachableDown() {
        var start = new Position(4, 4);
        var target = new Position(3, 4);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the right direction are accurate.
     */
    @Test
    public void testReachableRight() {
        var start = new Position(4, 4);
        var target = new Position(4, 5);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the left direction are accurate.
     */
    @Test
    public void testReachableLeft() {
        var start = new Position(4, 4);
        var target = new Position(4, 3);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the upper right direction are accurate.
     */
    @Test
    public void testReachableUpRight() {
        var start = new Position(4, 4);
        var target = new Position(4, 5);
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
     * Tests if all reachable squares in the lower right direction are accurate.
     */
    @Test
    public void testReachableDownRight() {
        var start = new Position(4, 4);
        var target = new Position(3, 5);
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
     * Tests if path is blocked by an enemy
     */
    @Test
    public void testIfPathBlockedbyEnemy() {
        var start = new Position(4, 4);
        var target = new Position(7, 1);
        assertFalse(reachable(start, target));
    }

    /**
     * Tests if path is blocked by an own piece
     */
    @Test
    public void testIfPathBlockedbyOwn() {
        var start = new Position(4, 4);
        var target = new Position(1, 8);
        assertFalse(reachable(start, target));
    }

    /**
     * Tests if a queen actually beats a piece it can legally beat
     */
    @Test
    public void testBeat() {
        var start = new Position(4, 4);
        var target = new Position(6, 2);
        assertTrue(reachable(start, target));
    }
}
