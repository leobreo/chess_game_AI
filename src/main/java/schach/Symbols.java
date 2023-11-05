package schach;

import schach.piece.Piece;

/**
 * Symbols for pieces.
 * The type parameter S specifies the type of the symbol, e.g. Character or Image
 *
 * @author  K. Schweppe
 * @version 1.0
 */
public interface Symbols<S> {
    /**
     * @param color The color
     * @return The symbol for Queen
     */
    S queen(Piece.Color color);

    /**
     * @param color The color
     * @return The symbol for King
     */
    S king(Piece.Color color);

    /**
     * @param color The color
     * @return The symbol for Rook
     */
    S rook(Piece.Color color);

    /**
     * @param color The color
     * @return The symbol for Bishop
     */
    S bishop(Piece.Color color);

    /**
     * @param color The color
     * @return The symbol for Knight
     */
    S knight(Piece.Color color);

    /**
     * @param color The color
     * @return The symbol for Pawn
     */
    S pawn(Piece.Color color);
}
