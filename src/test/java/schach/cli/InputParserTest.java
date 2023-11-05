package schach.cli;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schach.*;
import schach.move.BasicMove;
import schach.move.CastlingMove;
import schach.move.EnPassantMove;
import schach.move.PromotionMove;
import schach.piece.Bishop;
import schach.piece.Knight;
import schach.piece.Piece.Color;
import schach.piece.Queen;
import schach.piece.Rook;

/**
 * Tests for {@link InputParser}
 */
public class InputParserTest {
    InputParser parser;

    @BeforeEach
    public void setUp() {
        parser = new InputParser();
    }

    /**
     * Tests correct parsing of basic moves
     */
    @Test
    public void testInputToPossibleMovesBasic() {
        var movesA2A4 = parser.inputToPossibleMoves("a2-a4", Color.White);
        assertTrue(
            movesA2A4.contains(new BasicMove(new Position(2, 1), new Position(4, 1))), "a2-a4");
    }

    /**
     * Tests correct parsing of enpassant moves
     */
    @Test
    public void testInputToPossibleMovesEnPassant() {
        var movesD5C6 = parser.inputToPossibleMoves("d5-c6", Color.White);
        assertTrue(
            movesD5C6.contains(new EnPassantMove(new Position(5, 4), new Position(6, 3))), "d5-c6");
    }

    /**
     * Tests correct parsing of promotion moves
     */
    @Test
    public void testInputToPossibleMovesPromotion() {
        var movesB2C1 = parser.inputToPossibleMoves("b2-c1R", Color.Black);
        assertTrue(movesB2C1.contains(new PromotionMove(
                       new Position(2, 2), new Position(1, 3), new Rook(Color.Black))),
            "b2-c1R");

        var movesB7C8 = parser.inputToPossibleMoves("b7-c8Q", Color.White);
        assertTrue(movesB7C8.contains(new PromotionMove(
                       new Position(7, 2), new Position(8, 3), new Queen(Color.White))),
            "b7-c8Q");
    }

    /**
     * Tests correct parsing of castling moves
     */
    @Test
    public void testInputToPossibleMovesCastling() {
        var movesE8G8 = parser.inputToPossibleMoves("e8-g8R", Color.Black);
        assertTrue(
            movesE8G8.contains(new CastlingMove(new Position(8, 5), new Position(8, 7))), "e8-g8");
    }

    /**
     * Tests correct parsing of moves
     */
    @Test
    public void testInputToPossibleMovesEmpty() {
        assertTrue(parser.inputToPossibleMoves("e8-g9", Color.White).isEmpty());
    }

    /**
     * Tests valid enpassant inputs
     */
    @Test
    public void testValidEnPassantInput() {
        assertTrue(parser.isValidEnPassantInput(new Position(5, 5), new Position(6, 4)));
        assertTrue(parser.isValidEnPassantInput(new Position(5, 5), new Position(6, 6)));
        assertTrue(parser.isValidEnPassantInput(new Position(4, 4), new Position(3, 3)));
        assertTrue(parser.isValidEnPassantInput(new Position(4, 4), new Position(3, 5)));
    }

    /**
     * Tests invalid enpassant inputs
     */
    @Test
    public void testInvalidEnPassantInput() {
        assertFalse(parser.isValidEnPassantInput(new Position(5, 5), new Position(6, 5)));
        assertFalse(parser.isValidEnPassantInput(new Position(4, 4), new Position(3, 4)));
        assertFalse(parser.isValidEnPassantInput(new Position(4, 4), new Position(5, 4)));
        assertFalse(parser.isValidEnPassantInput(new Position(5, 5), new Position(7, 7)));
    }

    /**
     * Tests valid promotion inputs
     */
    @Test
    public void testValidPromotionInput() {
        assertTrue(parser.isValidPromotionInput(new Position(7, 2), new Position(8, 2)));
        assertTrue(parser.isValidPromotionInput(new Position(7, 2), new Position(8, 2)));
        assertTrue(parser.isValidPromotionInput(new Position(2, 5), new Position(1, 6)));
        assertTrue(parser.isValidPromotionInput(new Position(2, 2), new Position(1, 3)));
    }

    /**
     * Tests invalid promotion inputs
     */
    @Test
    public void testInvalidPromotionInput() {
        assertTrue(parser.isValidPromotionInput(new Position(7, 2), new Position(8, 2)));
        assertTrue(parser.isValidPromotionInput(new Position(7, 2), new Position(8, 2)));
        assertTrue(parser.isValidPromotionInput(new Position(2, 5), new Position(1, 6)));
        assertTrue(parser.isValidPromotionInput(new Position(2, 2), new Position(1, 3)));
    }

    /**
     * Tests valid castling inputs
     */
    @Test
    public void testValidCastlingInput() {
        assertTrue(parser.isValidCastlingInput(new Position(1, 5), new Position(1, 7)));
        assertTrue(parser.isValidCastlingInput(new Position(1, 5), new Position(1, 3)));
        assertTrue(parser.isValidCastlingInput(new Position(8, 5), new Position(8, 3)));
        assertTrue(parser.isValidCastlingInput(new Position(8, 5), new Position(8, 7)));
    }

    /**
     * Tests invalid castling inputs
     */
    @Test
    public void testInvalidCastlingInput() {
        assertFalse(parser.isValidCastlingInput(new Position(1, 5), new Position(2, 6)));
        assertFalse(parser.isValidCastlingInput(new Position(2, 6), new Position(2, 7)));
        assertFalse(parser.isValidCastlingInput(new Position(8, 5), new Position(7, 5)));
        assertFalse(parser.isValidCastlingInput(new Position(7, 5), new Position(7, 7)));
    }

    /**
     * Tests if the promotion letters are processed correctly into the right piece.
     */
    @Test
    public void testPromotionPieceMap() {
        assertTrue(parser.promotionPieceMap('N', Color.White) instanceof Knight);
        assertTrue(parser.promotionPieceMap('R', Color.White) instanceof Rook);
        assertTrue(parser.promotionPieceMap('Q', Color.White) instanceof Queen);
        assertTrue(parser.promotionPieceMap('B', Color.White) instanceof Bishop);
    }
}
