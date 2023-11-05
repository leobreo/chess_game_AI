package schach;

import schach.Game.GameState;
import schach.piece.Piece.Color;

/**
 * Main game controller
 */
public class GameController {
    private final Game game;
    private final View view;
    private final Player white;
    private final Player black;

    /**
     * Constructor
     *
     * @param model the Model
     * @param view  the GuiView
     */
    public GameController(Game game, View view, Player white, Player black) {
        this.game = game;
        this.view = view;
        this.white = white;
        this.black = black;
    }

    /**
     * Executes Command(s) after parsing user input.
     * Basically the flow of game: Run calls getLegalMoves --> chooseMove --> doMove --> incheck
     * (repeat)
     */
    public int run() {
        view.drawBoard(game.getChessboard());

        while (game.getState() == GameState.Running) {
            var chosenMove = getCurrentPlayer().chooseMove();

            chosenMove.ifPresent(move -> {
                game.makeMove(move);

                if (game.inCheck(game.getCurrentPlayerColor())) {
                    view.notifyCheck(game.getCurrentPlayerColor());
                }

                view.confirmMove(move);
                view.drawBoard(game.getChessboard());
                game.updateStatus();
            });
        }
        view.showStatus(game.getState());

        return 0;
    }

    /**
     * Method determines and returns the next moves palyer
     * @return Player whose turn it is
     */
    private Player getCurrentPlayer() {
        return game.getCurrentPlayerColor() == Color.White ? white : black;
    }
}
