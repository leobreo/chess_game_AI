package schach.cli;

import schach.Symbols;
import schach.piece.Piece;

/**
 * Unicode Symbols
 *
 * @author  I. Kocic
 * @version 1.0
 */
public class UnicodeSymbols implements Symbols<Character> {
    private boolean isWhite(Piece.Color color) {
        return color == Piece.Color.White;
    }

    @Override
    public Character king(Piece.Color color) {
        return isWhite(color) ? '\u2654' : '\u265A';
    }

    @Override
    public Character queen(Piece.Color color) {
        return isWhite(color) ? '\u2655' : '\u265B';
    }

    @Override
    public Character rook(Piece.Color color) {
        return isWhite(color) ? '\u2656' : '\u265C';
    }

    @Override
    public Character bishop(Piece.Color color) {
        return isWhite(color) ? '\u2657' : '\u265D';
    }

    @Override
    public Character knight(Piece.Color color) {
        return isWhite(color) ? '\u2658' : '\u265E';
    }

    @Override
    public Character pawn(Piece.Color color) {
        return isWhite(color) ? '\u2659' : '\u265F';
    }
}
