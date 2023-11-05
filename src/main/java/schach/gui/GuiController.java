package schach.gui;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import schach.AIPlayer;
import schach.Game;
import schach.GameController;
import schach.Player;
import schach.piece.Piece;

public class GuiController {
    private GameController gameController;
    private GuiView view;
    private Thread gameThread;

    /**
     * Instantiate Gui Controller
     * @param view
     */
    public GuiController(GuiView view) {
        this.view = view;
        view.getPvpButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.setupMainWindow(view.getSettings().getBundle());
                var game = new Game();
                var white = new GuiPlayer(Piece.Color.White, view, game);
                var black = new GuiPlayer(Piece.Color.Black, view, game);
                gameController = new GameController(game, view, white, black);
                startGame();
            }
        });

        view.getPvcButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.setupMainWindow(view.getSettings().getBundle());
                var game = new Game();
                Player black;
                Player white;
                if (view.promptPlayerForColor() == Piece.Color.White) {
                    white = new GuiPlayer(Piece.Color.White, view, game);
                    black =
                        new AIPlayer(game, view.getSettings().getSearchDepth(), Piece.Color.Black);
                } else {
                    black = new GuiPlayer(Piece.Color.Black, view, game);
                    white =
                        new AIPlayer(game, view.getSettings().getSearchDepth(), Piece.Color.White);
                }
                gameController = new GameController(game, view, white, black);
                startGame();
            }
        });

        view.mainStage.setOnCloseRequest(e -> quit());
        view.getQuitButton().setOnAction(actionEvent -> quit());
    }

    /**
     * Quit game and turn off threads gracefully
     */
    public void quit() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Start a new game
     */
    public void startGame() {
        if (gameThread != null) {
            stopGame();
        }
        Runnable task = () -> {
            gameController.run();
        };
        gameThread = new Thread(task);
        gameThread.start();
    }

    /**
     * Stop game, DANGEROUS!
     */
    public void stopGame() {
        gameThread.stop();
    }
}
