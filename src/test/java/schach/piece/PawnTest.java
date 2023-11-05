package schach.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schach.Chessboard;
import schach.Position;
import schach.TestUtils;
import schach.move.BasicMove;
import schach.move.PromotionMove;
import schach.piece.Piece.Color;

/**
 * Tests for {@link Pawn}
 */
public class PawnTest {
    Chessboard board;

    /**
     * Set up a chessboard to execute tests on.
     */
    @BeforeEach
    public void setUp() {
        var boardStr = ""
            + ". . . . . . . k\n"
            + "p p p . P . . .\n"
            + ". R r R . P . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". r R r . . . .\n"
            + "P P P . . . . .\n"
            + "K . . . . . . .";

        board = TestUtils.parseBoard(boardStr);
    }

    private boolean reachable(Position start, Position target) {
        var pawn = board.getPiece(start).get();
        var move = new BasicMove(start, target);
        return pawn.generatePseudoLegalMoves(board, start).contains(move);
    }
    /**
     * Tests if all reachable squares in the upper direction by white pieces are accurate.
     */
    @Test
    public void testReachableWhiteUp() {
        var start = new Position(2, 1);
        var target = new Position(3, 1);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the double upper direction by white pieces are accurate.
     */
    @Test
    public void testReachableWhiteUpUp() {
        var start = new Position(2, 1);
        var target = new Position(4, 1);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the lower direction by black pieces are accurate.
     */
    @Test
    public void testReachableBlackDown() {
        var start = new Position(7, 1);
        var target = new Position(6, 1);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if all reachable squares in the double down direction by black pieces are accurate.
     */
    @Test
    public void testReachableBlackDownDown() {
        var start = new Position(7, 1);
        var target = new Position(5, 1);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests if the path for a white piece is blocked by a black piece
     */
    @Test
    public void testIfWhitePathBlockedByEnemy() {
        var start = new Position(2, 2);
        var target = new Position(3, 2);
        assertFalse(reachable(start, target));
    }

    /**
     * Tests if the path for a black piece is blocked by a white piece
     */
    @Test
    public void testIfBlackPathBlockedByEnemy() {
        var start = new Position(7, 2);
        var target = new Position(6, 2);
        assertFalse(reachable(start, target));
    }

    /**
     * Tests if the path for a white piece is blocked by a white piece
     */
    @Test
    public void testIfWhitePathBlockedByOwn() {
        var start = new Position(2, 3);
        var target = new Position(3, 3);
        assertFalse(reachable(start, target));
    }

    /**
     * Tests if the path for a black piece is blocked by a black piece
     */
    @Test
    public void testIfBlackPathBlockedByOwn() {
        var start = new Position(7, 3);
        var target = new Position(6, 3);
        assertFalse(reachable(start, target));
    }

    /**
     * Tests that a white piece can beat a black piece in upper left direction
     */
    @Test
    public void testWhiteBeatBlackLeft() {
        var start = new Position(2, 3);
        var target = new Position(3, 2);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests that a white piece can beat a black piece in upper right direction
     */
    @Test
    public void testWhiteBeatBlackRight() {
        var start = new Position(2, 3);
        var target = new Position(3, 4);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests that a black piece can beat a white piece in upper left direction
     */
    @Test
    public void testBlackBeatWhiteLeft() {
        var start = new Position(7, 3);
        var target = new Position(6, 2);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests that a black piece can beat a white piece in upper right direction
     */
    @Test
    public void testBlackBeatWhiteRight() {
        var start = new Position(7, 3);
        var target = new Position(6, 4);
        assertTrue(reachable(start, target));
    }

    /**
     * Tests promotion move
     */
    @Test
    public void testPromotionMove() {
        var start = new Position(7, 5);
        var target = new Position(8, 5);
        var pawn = board.getPiece(start).get();
        var move = new PromotionMove(start, target, new Queen(Color.White));
        assertTrue(pawn.generatePseudoLegalMoves(board, start).contains(move));
    }
}
