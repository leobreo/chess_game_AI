package schach;

import schach.Game.GameState;
import schach.move.Move;
import schach.piece.Piece;

/**
 * View interface
 */
public interface View {
    /**
     * Show current game state
     *
     * @param state Game state
     */
    void showStatus(GameState state);

    /**
     * Notify player is in check
     *
     * @param color Color of player in check
     */
    void notifyCheck(Piece.Color color);

    /**
     * Confirm the last omve
     *
     * @param move Last move
     */
    void confirmMove(Move move);

    /**
     * Draw the chessboard
     *
     * @param board The chessboard
     */
    void drawBoard(Chessboard board);
}
