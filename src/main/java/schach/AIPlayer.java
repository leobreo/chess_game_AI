package schach;

import java.util.Optional;
import schach.move.Move;
import schach.piece.Piece;
/**
<<<<<<< HEAD:src/main/java/schach/AIPlayer.java
 * This class defines the Computer Player and its methods.
=======
 * This class defines the Computer Player and its methods. 
>>>>>>> Most of missing JavaDocs:src/main/java/schach/cli/AIPlayer.java
 */
public class AIPlayer extends Player {
    private Game game;
    private final int searchDepth;
    private Move bestMove;
<<<<<<< HEAD:src/main/java/schach/AIPlayer.java
    /**
     * The constructor of the AIPlayer
     * @param game
     * @param searchDepth
     * @param color
     */
=======
/**
 * The constructor of the AIPlayer
 * @param game
 * @param searchDepth
 * @param color
 */
>>>>>>> Most of missing JavaDocs:src/main/java/schach/cli/AIPlayer.java
    public AIPlayer(Game game, int searchDepth, Piece.Color color) {
        super(color);
        this.game = game;
        this.searchDepth = searchDepth;
    }

    @Override
    public Optional<Move> chooseMove() {
        alphaBetaMax(searchDepth, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return Optional.ofNullable(bestMove);
    }

    /**
<<<<<<< HEAD:src/main/java/schach/AIPlayer.java
     * The pruning method which lets the AIPlayer decide which move to choose.
     * Therefore it uses all legalMoves, so all possible unfiltered moves (from Game to Chessboard
     * to Piece). Value alpha describes the AIPlayers minimum while value beta stands for the
     * HumanPlayers maximum. First we check if the depth is zero, in that case we return the
     * evaluation of the board in that position. If not, we recursively call the alphaBetaMax and
     * alphaBetaMin Functions which provides the move we can use in the chooseMove Method.
=======
     * The pruning method which lets the AIPlayer decide which move to choose. 
     * Therefore it uses all legalMoves, so all possible unfiltered moves (from Game to Chessboard to Piece).
     * Value alpha describes the AIPlayers minimum while value beta stands for the HumanPlayers maximum.
     * First we check if the depth is zero, in that case we return the evaluation of the board in that position. 
     * If not, we recursively call the alphaBetaMax and alphaBetaMin Functions which provides the move we can use in the chooseMove Method.
>>>>>>> Most of missing JavaDocs:src/main/java/schach/cli/AIPlayer.java
     * @param depth
     * @param alphaStart
     * @param betaStart
     * @return alpha
     */
    private int alphaBetaMax(int depth, int alphaStart, int betaStart) {
        if (depth == 0) {
            return game.getChessboard().evaluateBoard(playerColor);
        }

        var alpha = alphaStart;
        var beta = betaStart;

        var moves = game.getLegalMoves(playerColor);

        for (Move move : moves) {
            game.makeMove(move);
            int value = alphaBetaMin(depth - 1, alpha, beta);
            game.undoLastMove();

            if (value > alpha) {
                alpha = value;
                if (depth == searchDepth) {
                    bestMove = move;
                }
            }
            if (alpha >= beta) {
                return alpha;
            }
        }

        return alpha;
    }

    /**
<<<<<<< HEAD:src/main/java/schach/AIPlayer.java
     * The alphaBetMin Method for Alpha-Beta-Pruning which is called recursively by the alphaBetaMax
     * Method.
     *
=======
     * The alphaBetMin Method for Alpha-Beta-Pruning which is called recursively by the alphaBetaMax Method.
     * 
>>>>>>> Most of missing JavaDocs:src/main/java/schach/cli/AIPlayer.java
     * @param depth
     * @param alphaStart
     * @param betaStart
     * @return beta
     */
    private int alphaBetaMin(int depth, int alphaStart, int betaStart) {
        if (depth == 0) {
            return game.getChessboard().evaluateBoard(playerColor);
        }

        var alpha = alphaStart;
        var beta = betaStart;

        var moves = game.getLegalMoves(playerColor.opponent());

        for (Move move : moves) {
            game.makeMove(move);
            int value = alphaBetaMax(depth - 1, alpha, beta);
            game.undoLastMove();

            if (value <= beta) {
                beta = value;
            }
            if (alpha >= beta) {
                return beta;
            }
        }

        return beta;
    }
}
