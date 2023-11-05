package schach.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schach.piece.Piece;

/**
 * Tests for {@link UnicodeSymbols}
 */
public class UnicodeSymbolsTest {
    private final Piece.Color white = Piece.Color.White;
    private final Piece.Color black = Piece.Color.Black;

    private UnicodeSymbols s;

    @BeforeEach
    public void setUp() {
        s = new UnicodeSymbols();
    }

    /**
     * Tests that unicode number equals symbol(king)
     */
    @Test
    public void king() {
        assertEquals('\u2654', s.king(white));
        assertEquals('\u265A', s.king(black));
    }

    /**
     * Tests that unicode number equals symbol(queen)
     */
    @Test
    public void queen() {
        assertEquals('\u2655', s.queen(white));
        assertEquals('\u265B', s.queen(black));
    }

    /**
     * Tests that unicode number equals symbol(rook)
     */
    @Test
    public void rook() {
        assertEquals('\u2656', s.rook(white));
        assertEquals('\u265C', s.rook(black));
    }

    /**
     * Tests that unicode number equals symbol(bishop)
     */
    @Test
    public void bishop() {
        assertEquals('\u2657', s.bishop(white));
        assertEquals('\u265D', s.bishop(black));
    }

    /**
     * Tests that unicode number equals symbol(knight)
     */
    @Test
    public void knight() {
        assertEquals('\u2658', s.knight(white));
        assertEquals('\u265E', s.knight(black));
    }

    /**
     * Tests that unicode number equals symbol(pawn)
     */
    @Test
    public void pawn() {
        assertEquals('\u2659', s.pawn(white));
        assertEquals('\u265F', s.pawn(black));
    }
}
