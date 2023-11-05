package schach;

import static schach.piece.Piece.Color.Black;
import static schach.piece.Piece.Color.White;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import schach.move.Move;
import schach.piece.Bishop;
import schach.piece.King;
import schach.piece.Knight;
import schach.piece.Pawn;
import schach.piece.Piece;
import schach.piece.Queen;
import schach.piece.Rook;

/**
 * Chessboard representation
 *
 * @author K. Schweppe
 * @version 1.3
 */
public class Chessboard {
    private final Map<Position, Piece> pieces;
    private final Stack<Piece> beatenPieces = new Stack<Piece>();

    private final Stack<Move> moves = new Stack<Move>();

    private final EnumMap<Piece.Color, Position> kingPositions = new EnumMap<>(Piece.Color.class);

    /**
     * Create a new chessboard with given starting position
     *
     * @param startingPosition Initial positions of pieces
     * @param whiteKingPos Position of the white king
     * @param blackKingPos Position of the black king
     */
    public Chessboard(
        Map<Position, Piece> startingPosition, Position whiteKingPos, Position blackKingPos) {
        this.pieces = startingPosition;
        kingPositions.put(White, whiteKingPos);
        kingPositions.put(Black, blackKingPos);
    }

    /**
     * Create a new chessboard with default starting position
     */
    public Chessboard() {
        this(defaultPositions(), new Position(1, 5), new Position(8, 5));
    }

    /**
     * Get the <code>Optional Piece </code> on the square.
     *
     * @param pos Position of the square
     * @return piece
     */
    public Optional<Piece> getPiece(Position pos) {
        return Optional.ofNullable(pieces.get(pos));
    }

    /**
     * Get the <code>Optional Piece </code> on the square, if it matches the color.
     *
     * @param pos Position of the square
     * @param color Color to match
     * @return piece
     */
    public Optional<Piece> getPiece(Position pos, Piece.Color color) {
        return getPiece(pos).filter(p -> p.getColor() == color);
    }

    /**
     * Set the piece on this <code>Square</code>
     *
     * @param pos Position of the square
     * @param piece Maybe a piece
     */
    public void setPiece(Position pos, Optional<Piece> piece) {
        piece.ifPresentOrElse(p -> pieces.put(pos, p), () -> pieces.remove(pos));
    }

    /**
     * Try to capture a piece. The captured piece is added to the list of beaten pieces
     *
     * @param target Capture target position
     * @return Optionally a captured piece
     * @see #getBeatenPieces()
     */
    public Optional<Piece> capture(Position target) {
        var beatenPiece = getPiece(target);
        beatenPiece.ifPresent(beatenPieces::push);
        pieces.remove(target);
        return beatenPiece;
    }

    /**
     * @return Beaten pieces
     */
    public Stack<Piece> getBeatenPieces() {
        return beatenPieces;
    }

    /**
     * Remove and return the last beaten piece
     *
     * @return Last beaten piece
     */
    public Piece takeLastBeaten() {
        return beatenPieces.pop();
    }

    /**
     * @return Last move
     */
    public Move getLastMove() {
        return moves.peek();
    }

    /**
     * @return All moves
     */
    public Stack<Move> getMoves() {
        return moves;
    }

    /**
     * @param color King's color
     * @param pos King's position
     */
    public void setKingPosition(Piece.Color color, Position pos) {
        kingPositions.put(color, pos);
    }

    /**
     * @param color King's color
     * @return King's position
     */
    public Position getKingPosition(Piece.Color color) {
        return kingPositions.get(color);
    }

    /**
<<<<<<< HEAD
     * Calculates and returns a set of all possible moves of all pieces that are possible
     * within the limits of the pieces moving rules
     *
=======
     * Calculates and returns a set of moves that are possible within the limits of that pieces moving rules
     * 
>>>>>>> Most of missing JavaDocs
     * @param color
     * @return set of possible moves
     */
    public Set<Move> getPseudoLegalMoves(Piece.Color color) {
        var moves = new HashSet<Move>();
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                var pos = new Position(row, col);
                getPiece(pos, color).ifPresent(p -> {
                    moves.addAll(p.generatePseudoLegalMoves(this, pos));
                });
            }
        }
        return moves;
    }

    /**
     * @param color Attacking player
     * @return Set of attacked positions
     */
    public Set<Position> attackedBy(Piece.Color color) {
        return getPseudoLegalMoves(color)
            .stream()
            .map(move -> move.getTarget())
            .collect(Collectors.toSet());
    }

    /**
     * Checks the pieces value
     * 
     * @param color
     * @param piece
     * @return value of piece
     */
    private int evaluatePiece(Piece.Color color, Piece piece) {
        return color == piece.getColor() ? piece.getValue() : -piece.getValue();
    }

    /**
     * Evaluates the value of the current Board situation.
     * All values of all present pieces are added into varible evaluation.
<<<<<<< HEAD
     *
=======
     * 
>>>>>>> Most of missing JavaDocs
     * @param color
     * @return sum of all pieces' values on the board
     */
    public int evaluateBoard(Piece.Color color) {
        int evaluation = 0;
        for (int rank = 1; rank <= 8; rank++) {
            for (int file = 1; file <= 8; file++) {
                var piece = getPiece(new Position(rank, file));
                evaluation += piece.map(p -> evaluatePiece(color, p)).orElse(0);
            }
        }
        return evaluation;
    }

    /**
     * Sets the initial positions of all pieces
<<<<<<< HEAD
     * @return Map that maps pieces to Positions
=======
     * @return Map that maps pieces to Positions 
>>>>>>> Most of missing JavaDocs
     */
    private static Map<Position, Piece> defaultPositions() {
        var pieces = new HashMap<Position, Piece>();

        // Pawns
        for (int col = 1; col <= 8; col++) {
            pieces.put(new Position(2, col), new Pawn(White));
            pieces.put(new Position(7, col), new Pawn(Black));
        }

        // Rooks
        pieces.put(new Position(1, 1), new Rook(White));
        pieces.put(new Position(1, 8), new Rook(White));
        pieces.put(new Position(8, 1), new Rook(Black));
        pieces.put(new Position(8, 8), new Rook(Black));

        // Knights
        pieces.put(new Position(1, 2), new Knight(White));
        pieces.put(new Position(1, 7), new Knight(White));
        pieces.put(new Position(8, 2), new Knight(Black));
        pieces.put(new Position(8, 7), new Knight(Black));

        // Bishops
        pieces.put(new Position(1, 3), new Bishop(White));
        pieces.put(new Position(1, 6), new Bishop(White));
        pieces.put(new Position(8, 3), new Bishop(Black));
        pieces.put(new Position(8, 6), new Bishop(Black));

        // Queens
        pieces.put(new Position(1, 4), new Queen(White));
        pieces.put(new Position(8, 4), new Queen(Black));

        // Kings
        pieces.put(new Position(1, 5), new King(White));
        pieces.put(new Position(8, 5), new King(Black));

        return pieces;
    }
}
