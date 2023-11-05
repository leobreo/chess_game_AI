package schach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schach.cli.ASCIISymbols;
import schach.cli.CliPlayer;
import schach.cli.CliView;
import schach.piece.Piece;

/**
 * Tests for {@link GameController}
 */
public class GameControllerTest {
    GameController controller;

    @BeforeEach
    public void setup() {
        var board = TestUtils.parseBoard(""
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . p . .\n"
            + ". . . . . . q .\n"
            + ". . . . . . . K\n");

        Game game = new Game(board);
        game.updateStatus();
        CliView view = new CliView(new ASCIISymbols());
        controller = new GameController(game, view, new CliPlayer(view, game, Piece.Color.White),
            new CliPlayer(view, game, Piece.Color.Black));
    }

    /**
     * Tests run method
     */
    @Test
    public void testRun() {
        assertEquals(0, controller.run(), "runTest");
    }
}
