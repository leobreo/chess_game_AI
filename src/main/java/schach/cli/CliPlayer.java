package schach.cli;

import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import schach.Game;
import schach.Player;
import schach.move.Move;
import schach.piece.Piece;

/**
<<<<<<< HEAD:src/main/java/schach/cli/CliPlayer.java
 * Class of CLI Player
 */
public class CliPlayer extends Player {
=======
 * Class of Human Plyer
 */
public class HumanPlayer extends Player {
>>>>>>> Most of missing JavaDocs:src/main/java/schach/cli/HumanPlayer.java
    private static final Scanner stdin = new Scanner(System.in);
    private final InputParser parser = new InputParser();
    private final CliView view;
    private final Game game;

    /**
     * Constructor of Human Player
     *
     * @param view CLI view
     * @param game The game
     * @param color Player color
     */
    public CliPlayer(CliView view, Game game, Piece.Color color) {
        super(color);
        this.view = view;
        this.game = game;
    }


    @Override
    public Optional<Move> chooseMove() {
        var moves = game.getLegalMoves(getPlayerColor());
        var inputRaw = stdin.nextLine();

        if (inputRaw.equals("beaten")) {
            view.listBeaten(game.getChessboard().getBeatenPieces().stream());
            return Optional.empty();
        }

        var possibleInputMoves = parser.inputToPossibleMoves(inputRaw, playerColor);

        if (possibleInputMoves.isEmpty()) {
            view.printMessage("!Invalid move");
            return Optional.empty();
        }

        for (var move : possibleInputMoves) {
            if (moves.contains(move)) {
                return Optional.of(move);
            }
        }
        view.printMessage("!Move not allowed");

        return Optional.empty();
    }
}
