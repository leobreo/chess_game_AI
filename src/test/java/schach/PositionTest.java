package schach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Position}
 */
public class PositionTest {
    /**
     * Tests if the getter method gives
     */
    @Test
    public void testGetter() {
        var p = new Position(1, 2);
        assertEquals(p.getRow(), 1);
        assertEquals(p.getCol(), 2);
    }

    /**
     * Tests if the equal method correctly compares two positions
     */
    @Test
    public void testEquals() {
        var p1 = new Position(1, 2);
        var p2 = new Position(1, 2);
        var p3 = new Position(2, 1);
        var p4 = new Position(2, 3);
        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertNotEquals(p3, p4);
        assertNotEquals(p1, "42");
    }

    /**
     * Tests if the Hash Code is working correctly
     */
    @Test
    public void testHashCode() {
        var s = new HashSet<>();
        s.add(new Position(1, 2));
        s.add(new Position(1, 2));
        assertEquals(s.size(), 1);
    }

    /**
     * Tests if a piece on the board is on the board
     */
    @Test
    public void testIsOnBoard() {
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                assertTrue(new Position(row, col).isOnBoard());
            }
        }
        assertFalse(new Position(0, 5).isOnBoard());
        assertFalse(new Position(4, 9).isOnBoard());
        assertFalse(new Position(-2, -3).isOnBoard());
        assertFalse(new Position(9, 9).isOnBoard());
    }
}
