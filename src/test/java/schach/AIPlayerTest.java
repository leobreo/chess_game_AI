package schach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schach.move.BasicMove;

/**
 * Tests for {@link AIPlayer}
 */
public class AIPlayerTest {
    Game game;
    AIPlayer ai;

    @BeforeEach
    public void setup() {}

    // @Test
    // public void testPutOpponentInCheck() {
    // var board = TestUtils.parseBoard(""
    // + ". . . k . . . .\n"
    // + ". . . . . . . .\n"
    // + ". . Q . . . . .\n"
    // + ". . . . N . . .\n"
    // + ". . . . . . . .\n"
    // + ". . . . . . . .\n"
    // + ". . . . . . . .\n"
    // + ". . . . . . . .\n");

    // game = new Game(board);
    // ai = new AIPlayer(game, 3, game.getCurrentPlayerColor());
    // var move = ai.chooseMove();
    // System.out.println(move.get().toString());
    // assertTrue(move.equals(Optional.of(new BasicMove(new Position(6, 3), new Position(7, 4)))));
    // }

    @Test
    public void testMoveOutOfCheck() {
        var board = TestUtils.parseBoard(""
            + ". . . . . . . .\n"
            + ". P . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". n . q . . . .\n"
            + ". . . . . . . .\n"
            + ". . . K . . . .\n");

        game = new Game(board);
        ai = new AIPlayer(game, 3, game.getCurrentPlayerColor());
        var move = ai.chooseMove();
        assertEquals(move, Optional.of(new BasicMove(new Position(1, 4), new Position(1, 5))));
    }

    @Test
    public void testDoGoodTrade() {
        var board = TestUtils.parseBoard(""
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . p . . .\n"
            + ". . . b . . . .\n"
            + ". . B . P . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n");

        game = new Game(board);
        ai = new AIPlayer(game, 3, game.getCurrentPlayerColor());
        var move = ai.chooseMove();
        assertEquals(move, Optional.of(new BasicMove(new Position(3, 5), new Position(4, 4))));
    }
}
