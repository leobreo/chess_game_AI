package schach.piece;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schach.Chessboard;
import schach.Position;
import schach.TestUtils;
import schach.move.BasicMove;
import schach.piece.Piece.Color;

/**
 * Tests for {@link King}
 */
public class KingTest {
    Chessboard board;

    /**
     * Set up a chessboard to execute tests on.
     */
    @BeforeEach
    public void setUp() {
        var boardStr = ""
            + ". . . . . . . .\n"
            + ". . . . . . k .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". K . . . . . .\n"
            + ". . . . . . . .";

        board = TestUtils.parseBoard(boardStr);
    }

    private boolean reachable(Position start, Position target) {
        var king = board.getPiece(start).get();
        var move = new BasicMove(start, target);
        return king.generatePseudoLegalMoves(board, start).contains(move);
    }

    /**
     * Tests if all reachable squares in the upper direction are accurate.
     */
    @Test
    public void testReachableUp() {
        var start = new Position(2, 2);
        var target = new Position(3, 2);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the lower direction are accurate.
     */
    @Test
    public void testReachableDown() {
        var start = new Position(2, 2);
        var target = new Position(1, 2);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the right direction are accurate.
     */
    @Test
    public void testReachableRight() {
        var start = new Position(2, 2);
        var target = new Position(2, 3);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the left direction are accurate.
     */
    @Test
    public void testReachableLeft() {
        var start = new Position(2, 2);
        var target = new Position(2, 1);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the upper right direction are accurate.
     */
    @Test
    public void testReachableUpRight() {
        var start = new Position(2, 2);
        var target = new Position(3, 3);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the upper left direction are accurate.
     */
    @Test
    public void testReachableUpLeft() {
        var start = new Position(2, 2);
        var target = new Position(3, 1);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the lower right direction are accurate.
     */
    @Test
    public void testReachableDownRight() {
        var start = new Position(2, 2);
        var target = new Position(1, 3);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the lower left direction are accurate.
     */
    @Test
    public void testReachableDownLeft() {
        var start = new Position(2, 2);
        var target = new Position(1, 1);
        assertTrue(reachable(start, target));
    }

    /**
     * Test if path is blocked by own color
     */
    @Test
    public void testIfPathBlockedbyOwn() {
        var start = new Position(2, 2);
        var target = new Position(3, 3);
        var rook = new Rook(Color.White);
        var rookPos = new Position(3, 3);
        var king = board.getPiece(start).get();
        var move = new BasicMove(start, target);
        board.setPiece(rookPos, Optional.of(rook));
        assertFalse(king.generatePseudoLegalMoves(board, start).contains(move));
    }

    /**
     * Test if king actually beats teh piece it can legally beat.
     */
    @Test
    public void testBeat() {
        var start = new Position(2, 2);
        var target = new Position(3, 3);
        var rook = new Rook(Color.Black);
        var rookPos = new Position(3, 3);
        var king = board.getPiece(start).get();
        var move = new BasicMove(start, target);
        board.setPiece(rookPos, Optional.of(rook));
        assertTrue(king.generatePseudoLegalMoves(board, start).contains(move));
    }
}
