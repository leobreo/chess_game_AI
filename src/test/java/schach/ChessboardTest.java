package schach;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import schach.piece.Piece.Color;

/**
 * Tests the chessboard.
 */
public class ChessboardTest {
    /**
     * Tests the accurate evaluation of the default chessboard.
     */
    @Test
    public void testEvaluateDefaultBoard() {
        var defaultBoard = new Chessboard();
        assertEquals(defaultBoard.evaluateBoard(Color.White), 0);
    }

    /**
     * Tests the accurate evaluation of the modified chessboard.
     */
    @Test
    public void testEvaluateBoard() {
        var boardStr = ""
            + ". . . q . R . k\n"
            + "p . p . . . . .\n"
            + "B . . . . . . n\n"
            + ". . . . . . . .\n"
            + ". Q . p . . . .\n"
            + "B . . . P . . b\n"
            + ". . q . . P . P\n"
            + "R . . . K . . .\n";

        var board = TestUtils.parseBoard(boardStr);
        assertEquals(board.evaluateBoard(Color.White), 1);
    }
}
