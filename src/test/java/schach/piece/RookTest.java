package schach.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schach.Chessboard;
import schach.Position;
import schach.TestUtils;
import schach.move.BasicMove;

/**
 * Tests for {@link Rook}
 */
public class RookTest {
    Chessboard board;

    /**
     * Set up a chessboard to execute tests on.
     */
    @BeforeEach
    public void setUp() {
        var boardStr = ""
            + ". . . . . . . k\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . R . . r .\n"
            + ". . . . . . . .\n"
            + ". . . P . . . .\n"
            + "K . . . . . . .";

        board = TestUtils.parseBoard(boardStr);
    }

    private boolean reachable(Position start, Position target) {
        var knight = board.getPiece(start).get();
        var move = new BasicMove(start, target);
        return knight.generatePseudoLegalMoves(board, start).contains(move);
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
     * Tests if all reachable squares in the lower direction are accurate.
     */
    @Test
    public void testReachableLeft() {
        var start = new Position(4, 4);
        var target = new Position(4, 3);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if the path is blocked by an enemy
     */
    @Test
    public void testIfPathBlockedbyEnemy() {
        var start = new Position(4, 4);
        var target = new Position(4, 8);
        assertFalse(reachable(start, target));
    }

    /**
     * Tests if the path is blocked by an own piece
     */
    @Test
    public void testIfPathBlockedbyOwn() {
        var start = new Position(4, 4);
        var target = new Position(1, 4);
        assertFalse(reachable(start, target));
    }

    /**
     * Tests if a rook actually beats a piece it can legally beat.
     */
    @Test
    public void testBeat() {
        var start = new Position(4, 4);
        var target = new Position(4, 7);
        assertTrue(reachable(start, target));
    }
}
