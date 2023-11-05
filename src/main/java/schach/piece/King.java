package schach.piece;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import schach.Chessboard;
import schach.Position;
import schach.Symbols;
import schach.move.CastlingMove;
import schach.move.Move;

/**
 * A King:
 *
 * @author J. Horbank, L. Brenk
 * @version 1.0
 */
public class King extends Piece {
    private final int[][] offsets = {
        {1, 0}, {1, 1}, {0, 1}, {-1, 0}, {0, -1}, {-1, 1}, {-1, -1}, {1, -1}};

    /**
     * Creates a King:
     *
     * @param color Color of this piece
     */
    public King(Color color) {
        super(color);
        value = 4;
    }

    @Override
    public Set<Move> generatePseudoLegalMoves(Chessboard board, Position current) {
        var moves = generateJumpingMoves(board, current, offsets);
        moves.addAll(generateCastlingMoves(board, current));
        return moves;
    }

    /**
     * Generation of castling moves
<<<<<<< HEAD
     *
     * @param board
     * @param source
     * @return Set of castlings moves
=======
     * 
     * @param board
     * @param source
     * @return Set of castlings moves 
>>>>>>> Most of missing JavaDocs
     */
    private Set<Move> generateCastlingMoves(Chessboard board, Position source) {
        var moves = new HashSet<Move>();

        if (getMoveCount() == 0) {
            getCastlingMove(board, source, true).ifPresent(move -> moves.add(move));
            getCastlingMove(board, source, false).ifPresent(move -> moves.add(move));
        }

        return moves;
    }

    /**
     * Calculates the actual castling moves and decides if short or long
     * Used in generateCasstlingMoves
<<<<<<< HEAD
     *
     * @param board the board
     * @param source source position
     * @param isShortCastling true if short castling, false if long castling
=======
     * 
     * @param board
     * @param source
     * @param isShortCastling
>>>>>>> Most of missing JavaDocs
     * @return one long or short castling move
     */
    private Optional<Move> getCastlingMove(
        Chessboard board, Position source, boolean isShortCastling) {
        var target = new Position(source.getRow(), isShortCastling ? 7 : 3);
        var rookSource = new Position(source.getRow(), isShortCastling ? 8 : 1);

        if (board.getPiece(rookSource).filter(piece -> piece.getMoveCount() == 0).isPresent()
            && noPiecesBetween(board, source, isShortCastling)) {
            return Optional.of(new CastlingMove(source, target));
        }
        return Optional.empty();
    }

    /**
     * Checks if there are Pieces in between the king and the rook used for castling move
<<<<<<< HEAD
     *
     * @param board the board
     * @param source source position
     * @param isShortCastling true if short castling, false if long castling
=======
     * 
     * @param board
     * @param source
     * @param isShortCastling
>>>>>>> Most of missing JavaDocs
     * @return boolean whether or not the path is empty
     */
    private boolean noPiecesBetween(Chessboard board, Position source, boolean isShortCastling) {
        var direction = isShortCastling ? 1 : -1;
        for (int offset = 1; offset <= (isShortCastling ? 2 : 3); offset++) {
            if (!board.getPiece(source.offset(0, offset * direction)).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public <S> S getSymbol(Symbols<S> symbols) {
        return symbols.king(getColor());
    }

    @Override
    public void onMoved(Chessboard board, Position target, int moveCount) {
        super.onMoved(board, target, moveCount);
        board.setKingPosition(getColor(), target);
    }
}
