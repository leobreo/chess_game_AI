package schach;

import java.util.Set;
import schach.move.Move;
import schach.piece.Piece;
import schach.piece.Piece.Color;

/**
 * Representation of a Chessboard
 *
 * @author K. Schweppe
 * @version 1.0
 */
public class Game {
    /**
     * Game states
     */
    public enum GameState { Running, Checkmate, Stalemate, Draw, Paused }

    private final Chessboard board;
    private GameState state;

    /**
     * Contructor of Game
<<<<<<< HEAD
     * @param board the board
=======
     * @param board
>>>>>>> Most of missing JavaDocs
     */
    public Game(Chessboard board) {
        this.board = board;
        state = GameState.Running;
    }
    /**
     * Constructor of Game
     */
    public Game() {
        this(new Chessboard());
    }

    /**
     * @return chessboard
     */
    public Chessboard getChessboard() {
        return board;
    }

    /**
     * @return state
     */
    public GameState getState() {
        return state;
    }

    /**
     * @return round
     */
    public int round() {
        return board.getMoves().size() + 1;
    }

    /**
     * @return Color of the current player
     */
    public Color getCurrentPlayerColor() {
        return round() % 2 == 0 ? Piece.Color.Black : Piece.Color.White;
    }

    /**
     * Method to make a move
<<<<<<< HEAD
     *
=======
     * 
>>>>>>> Most of missing JavaDocs
     * @param move Move to make
     */
    public void makeMove(Move move) {
        move.doMove(board);
        board.getMoves().push(move);
    }

    /**
     * Undo the last move
     */
    public void undoLastMove() {
        var move = board.getMoves().pop();
        move.undoMove(board);
    }

    /**
<<<<<<< HEAD
     * Checks if a move is legal or not.
     * If that move puts the current player immediately in check it is not legal.
     *
=======
     * Checks if a move is legal or not. 
     * If that move puts the current player immediately in check it is not legal
     * 
>>>>>>> Most of missing JavaDocs
     * @param move
     * @param color
     * @return boolean wether the move puts the current player in check
     */
    private boolean isLegal(Move move, Piece.Color color) {
        makeMove(move);
        var legal = !inCheck(color);
        undoLastMove();
        return legal;
    }

    /**
<<<<<<< HEAD
     * Gets all possible unfiltered moves for a certain player.
     * Removes all moves, that are not legal by isLegal or all moves that are not accepted by the
     * getNoAttackingAllowed Method.
     *
     * @param color Color of the player
     * @return Set of legal moves
=======
     * Gets all possible unfiltered moves for a certain piece.
     * Removes all moves, that are not legal by isLegal or all moves that are not accepted by the getNoAttackingAllowed Method
     * @param color
     * @return
>>>>>>> Most of missing JavaDocs
     */
    public Set<Move> getLegalMoves(Piece.Color color) {
        var moves = board.getPseudoLegalMoves(color);
        var attacked = board.attackedBy(color.opponent());

        moves.removeIf(
            move -> move.getNoAttackingAllowed(color).stream().anyMatch(attacked::contains));
        moves.removeIf(move -> !isLegal(move, color));

        return moves;
    }

    /**
     * Test if a player's king is in check
     *
     * @param color Color of the king
     * @return true if in check
     */
    public boolean inCheck(Piece.Color color) {
        return board.attackedBy(color.opponent()).contains(board.getKingPosition(color));
    }

    /**
     * Checkmate: the king is in check and there is no way to remove the threat
     *
     * @param color Color of the player
     * @return true if in checkmate position
     */
    public boolean isCheckmate(Piece.Color color) {
        return inCheck(color) && getLegalMoves(color).isEmpty();
    }

    /**
     * Stalemate: player has not possible moves to make and is not incheck
     *
     * @param color Color of the player
     * @return true if in stalemate
     */
    public boolean isStalemate(Piece.Color color) {
        return !inCheck(color) && getLegalMoves(color).isEmpty();
    }

    /**
     * TODO A tie
     *
     * @return false
     */
    public boolean isDraw() {
        return false;
    }

    /**
     * updates the status
     */
    public void updateStatus() {
        if (isCheckmate(getCurrentPlayerColor())) {
            state = GameState.Checkmate;
        } else if (isStalemate(getCurrentPlayerColor())) {
            state = GameState.Stalemate;
        } else if (isDraw()) {
            state = GameState.Draw;
        } else {
            state = GameState.Running;
        }
    }
}
