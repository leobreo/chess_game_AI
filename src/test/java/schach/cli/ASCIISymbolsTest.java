package schach.cli;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schach.piece.Piece;

/**
 * Tests for {@link ASCIISymbols}
 */
public class ASCIISymbolsTest {
    private final Piece.Color white = Piece.Color.White;
    private final Piece.Color black = Piece.Color.Black;

    private ASCIISymbols s;

    @BeforeEach
    public void setUp() {
        s = new ASCIISymbols();
    }
    /**
     * Tests if  q,Q represents the queen
     */
    @Test
    public void queen() {
        assertEquals('Q', s.queen(white));
        assertEquals('q', s.queen(black));
    }

    /**
     * Tests if  r,R represents the rooks
     */
    @Test
    public void rook() {
        assertEquals('R', s.rook(white));
        assertEquals('r', s.rook(black));
    }

    /**
     * Tests if  b,B represents the bishop
     */
    @Test
    public void bishop() {
        assertEquals('B', s.bishop(white));
        assertEquals('b', s.bishop(black));
    }

    /**
     * Tests if  k,K represents the king
     */
    @Test
    public void king() {
        assertEquals('K', s.king(white));
        assertEquals('k', s.king(black));
    }

    /**
     * Tests if  n,N represents the knight
     */
    @Test
    public void knight() {
        assertEquals('N', s.knight(white));
        assertEquals('n', s.knight(black));
    }

    /**
     * Tests if  p,P represents the pawn
     */
    @Test
    public void pawn() {
        assertEquals('P', s.pawn(white));
        assertEquals('p', s.pawn(black));
    }
}
