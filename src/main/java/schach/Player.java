package schach;

import java.util.Optional;
import java.util.Set;
import schach.move.Move;
import schach.piece.Piece;

/**
 * Abstract class for Player which will be extended and overwritten in HumanPlayer and AIPlayer
 */
public abstract class Player {
    protected Piece.Color playerColor;

    /**
     * Abstract Constructor for Player
     * @param color Player color
     */
    public Player(Piece.Color playerColor) {
        this.playerColor = playerColor;
    }

    /**
     * Abstract Method to retrieve the current players color.
     */
    public Piece.Color getPlayerColor() {
        return this.playerColor;
    }

    /**
     * Method for choosing the Move.
     *
     * @return the Optional move
     */
    public abstract Optional<Move> chooseMove();
}
