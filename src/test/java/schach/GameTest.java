package schach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import schach.move.BasicMove;
import schach.move.CastlingMove;
import schach.piece.Piece.Color;

/**
 * Tests the game
 */
public class GameTest {
    /**
     * Tests if in check
     */
    @Test
    public void testInCheck() {
        var boardStr = ""
            + ". . . . . . . k\n"
            + ". . . P . . . .\n"
            + ". . R . K . . .\n"
            + ". . . . . . . .\n"
            + ". N . . . . . .\n"
            + ". . . . . . . .\n"
            + "b . . . . . . .\n"
            + ". . . . . R . .";

        var board = TestUtils.parseBoard(boardStr);
        var game = new Game(board);

        assertTrue(game.inCheck(Color.White));
    }

    /**
     * Test if Checkmate
     */
    @Test
    public void testCheckmate() {
        var boardStr = ""
            + ". k . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . r .\n"
            + ". . . . . . . .\n"
            + ". . . . b . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . P\n"
            + ". . . . . . . K\n";

        var board = TestUtils.parseBoard(boardStr);
        var game = new Game(board);

        assertTrue(game.isCheckmate(Color.White));
    }

    /**
     * Test if Stalemate
     */
    @Test
    public void testStalemate() {
        var boardStr = ""
            + ". . . . . . . k\n"
            + ". . . . . K . .\n"
            + ". . . . . . Q .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n";

        var board = TestUtils.parseBoard(boardStr);
        var game = new Game(board);

        assertTrue(game.isStalemate(Color.Black));
    }

    /**
     * Check if FoolsMate
     */
    @Test
    public void testFoolsMate() {
        var boardStr = ""
            + "r n b q k b n r\n"
            + "p p p p p p p p\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + "P P P P P P P P\n"
            + "R N B Q K B N R\n";

        var expected = "\n"
            + "r n b . k b n r\n"
            + "p p p p . p p p\n"
            + ". . . . . . . .\n"
            + ". . . . p . . .\n"
            + ". . . . . . P q\n"
            + ". . . . . P . .\n"
            + "P P P P P . . P\n"
            + "R N B Q K B N R\n";

        var board = TestUtils.parseBoard(boardStr);

        var game = new Game(board);

        assertEquals(game.getCurrentPlayerColor(), Color.White);
        game.makeMove(new BasicMove(new Position(2, 6), new Position(3, 6)));
        assertEquals(game.getCurrentPlayerColor(), Color.Black);
        game.makeMove(new BasicMove(new Position(7, 5), new Position(5, 5)));
        game.makeMove(new BasicMove(new Position(2, 7), new Position(4, 7)));
        game.makeMove(new BasicMove(new Position(8, 4), new Position(4, 8)));

        assertEquals(expected, TestUtils.drawBoard(board));
        assertTrue(game.isCheckmate(Color.White), "checkmate");
    }

    /**
     * Tests if short castling move is working
     */
    @Test
    public void testCastlingShort() {
        var boardStr = ""
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + "R . . . K . N R\n";

        var expected = "\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + "R . . . . R K .\n";

        var board = TestUtils.parseBoard(boardStr);
        var game = new Game(board);

        assertFalse(game.getLegalMoves(Color.White)
                        .contains(new CastlingMove(new Position(1, 5), new Position(1, 7))));
        board.setPiece(new Position(1, 7), Optional.empty());
        game.makeMove(new CastlingMove(new Position(1, 5), new Position(1, 7)));

        assertEquals(expected, TestUtils.drawBoard(board));
    }

    /**
     * Tests if long castling move is working
     */
    @Test
    public void testCastlingLong() {
        var boardStr = ""
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + "R N . . K . . R\n";

        var expected = "\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . K R . . . R\n";

        var board = TestUtils.parseBoard(boardStr);
        var game = new Game(board);

        assertFalse(game.getLegalMoves(Color.White)
                        .contains(new CastlingMove(new Position(1, 5), new Position(1, 3))));
        board.setPiece(new Position(1, 2), Optional.empty());
        game.makeMove(new CastlingMove(new Position(1, 5), new Position(1, 3)));

        assertEquals(expected, TestUtils.drawBoard(board));
    }
}
