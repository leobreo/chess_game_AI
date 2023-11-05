package schach.move;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schach.Chessboard;
import schach.Position;
import schach.TestUtils;
import schach.piece.Pawn;
import schach.piece.Piece.Color;
import schach.piece.Queen;
import schach.piece.Rook;

/**
 * Tests for Moves
 */
public class MoveTest {
    Chessboard board;

    /**
     * sets up the board
     */
    @BeforeEach
    public void setUp() {
        var boardStr = ""
            + ". . . . . . . .\n"
            + ". . . . . P . .\n"
            + ". . . . . . . .\n"
            + ". . p P . . . .\n"
            + ". . . . . . . .\n"
            + ". . . . . . . .\n"
            + "P . . . . . . .\n"
            + ". . . . K . . R";

        board = TestUtils.parseBoard(boardStr);
    }

    /**
     * Tests a basic move
     */
    @Test
    public void testDoBasicMove() {
        var source = new Position(2, 1);
        var target = new Position(3, 1);
        var move = new BasicMove(source, target);
        move.doMove(board);
        assertTrue(board.getPiece(target).isPresent());
        assertTrue(board.getPiece(source).isEmpty());
    }

    /**
     * Tests a basic move
     */
    @Test
    public void testUndoBasicMove() {
        var source = new Position(2, 1);
        var target = new Position(3, 1);
        var move = new BasicMove(source, target);
        move.doMove(board);
        move.undoMove(board);
        assertTrue(board.getPiece(source).isPresent());
        assertTrue(board.getPiece(target).isEmpty());
    }

    /**
     * Tests enpassant Move
     */
    @Test
    public void testDoEnPassantMove() {
        var source = new Position(5, 4);
        var target = new Position(4, 3);
        var beating = new Position(5, 3);

        var enPassantMove = new EnPassantMove(source, target);

        enPassantMove.doMove(board);
        assertTrue(board.getPiece(target).isPresent());
        assertTrue(board.getPiece(source).isEmpty());
        assertTrue(board.getPiece(beating).isEmpty());
    }

    /**
     * Tests enpassant Move
     */
    @Test
    public void testUndoEnPassantMove() {
        var source = new Position(5, 4);
        var target = new Position(4, 3);
        var beating = new Position(5, 3);

        var enPassantMove = new EnPassantMove(source, target);

        enPassantMove.doMove(board);
        enPassantMove.undoMove(board);
        assertTrue(board.getPiece(target).isEmpty());
        assertTrue(board.getPiece(source).isPresent());
        assertTrue(board.getPiece(beating).isPresent());
    }

    /**
     * Tests Castling Move
     */
    @Test
    public void testDoCastlingMove() {
        var source = new Position(1, 5);
        var target = new Position(1, 7);
        var rookSource = new Position(1, 8);
        var rookTarget = new Position(1, 6);

        var castlingMove = new CastlingMove(source, target);

        castlingMove.doMove(board);
        assertTrue(board.getPiece(target).isPresent());
        assertTrue(board.getPiece(source).isEmpty());
        assertTrue(board.getPiece(rookTarget).isPresent());
        assertTrue(board.getPiece(rookSource).isEmpty());
    }

    /**
     * Tests Castling Move
     */
    public void testUndoCastlingMove() {
        var source = new Position(1, 5);
        var target = new Position(1, 7);
        var rookSource = new Position(1, 8);
        var rookTarget = new Position(1, 6);

        var castlingMove = new CastlingMove(source, target);

        castlingMove.doMove(board);
        castlingMove.undoMove(board);
        assertTrue(board.getPiece(target).isEmpty());
        assertTrue(board.getPiece(source).isPresent());
        assertTrue(board.getPiece(rookTarget).isEmpty());
        assertTrue(board.getPiece(rookSource).isPresent());
    }

    /**
     * Tests Promotion Move
     */
    @Test
    public void testPromotionMove() {
        var source = new Position(7, 6);
        var target = new Position(8, 6);

        var promoMove = new PromotionMove(source, target, new Queen(Color.White));

        promoMove.doMove(board);
        assertTrue(board.getPiece(target).filter(p -> p instanceof Queen).isPresent());
        assertTrue(board.getPiece(source).isEmpty());

        promoMove.undoMove(board);
        assertTrue(board.getPiece(target).isEmpty());
        assertTrue(board.getPiece(source).filter(p -> p instanceof Pawn).isPresent());
    }

    /**
     * Tests toString
     */
    @Test
    public void testToString() {
        var move = new BasicMove(new Position(1, 1), new Position(2, 2));
        assertEquals("a1-b2", move.toString(), "a1-b2 toString() test");
    }

    /**
     * Tests promotion move toString
     */
    @Test
    public void testPromotionToString() {
        var move = new PromotionMove(new Position(7, 1), new Position(8, 1), new Rook(Color.White));
        assertEquals("a7-a8R", move.toString(), "a7-a8R toString() test");
    }
}
