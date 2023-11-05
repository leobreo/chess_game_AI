package schach.cli;

import schach.Symbols;
import schach.piece.Piece;
import schach.piece.Piece.Color;

/**
 * ASCII Symbols
 *
 * @author  K. Schweppe
 * @version 1.0
 */
public class ASCIISymbols implements Symbols<Character> {
    // Uppercase for white, lowercase for black
    private Character colorize(Color color, char c) {
        return color == Piece.Color.White ? Character.toUpperCase(c) : c;
    }

    @Override
    public Character queen(Color color) {
        return colorize(color, 'q');
    }

    @Override
    public Character rook(Color color) {
        return colorize(color, 'r');
    }

    @Override
    public Character bishop(Color color) {
        return colorize(color, 'b');
    }

    @Override
    public Character king(Color color) {
        return colorize(color, 'k');
    }

    @Override
    public Character knight(Color color) {
        return colorize(color, 'n');
    }

    @Override
    public Character pawn(Color color) {
        return colorize(color, 'p');
    }
}
