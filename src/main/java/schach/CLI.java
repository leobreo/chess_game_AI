package schach;

import schach.cli.CliPlayer;
import schach.cli.CliView;
import schach.cli.UnicodeSymbols;
import schach.piece.Piece;

/**
 * CLI application
 *
 * @author K. Schweppe
 * @version 1.0
 */
public class CLI {
    /**
     * Main CLI entry point
     *
     * @param args
     */
    public static void main(boolean simple) {
        var game = new Game();
        var view = new CliView(new UnicodeSymbols());

        var white = new CliPlayer(view, game, Piece.Color.White);
        var black = simple ? new CliPlayer(view, game, Piece.Color.Black)
                           : new AIPlayer(game, 2, Piece.Color.Black);

        var controller = new GameController(game, view, white, black);

        controller.run();
    }
}
