package schach.piece;

import java.util.HashSet;
import java.util.Set;
import schach.Chessboard;
import schach.Position;
import schach.Symbols;
import schach.move.BasicMove;
import schach.move.EnPassantMove;
import schach.move.Move;
import schach.move.PromotionMove;

/**
 * The Pawn class
 *
 * @author J. Horbank, L. Brenk
 * @version 1.0
 */
public class Pawn extends Piece {
    private int direction, initialRow;

    /**
     * Creates a new Pawn
     *
     * @param color the color
     */
    public Pawn(Color color) {
        super(color);
        direction = getColor() == Color.White ? 1 : -1;
        initialRow = getColor() == Color.White ? 2 : 7;
        value = 1;
    }

    @Override
    public Set<Move> generatePseudoLegalMoves(Chessboard board, Position current) {
        var moves = new HashSet<Move>();

        var beatRight = current.offset(direction, direction);
        var beatLeft = current.offset(direction, -direction);

        var enPassantRight = current.offset(0, direction);
        var enPassantLeft = current.offset(0, -direction);

        moves.addAll(getPushes(board, current));

        moves.addAll(getBeatingMoves(board, current, beatRight, enPassantRight));
        moves.addAll(getBeatingMoves(board, current, beatLeft, enPassantLeft));

        return moves;
    }

    @Override
    public <S> S getSymbol(Symbols<S> symbols) {
        return symbols.pawn(getColor());
    }
    /**
     * Gets diagonal beating move.
     * If it can, the move can still be a simple of promition move.
     *
     * @return Set of moves that beat pieces
     */
    private Set<Move> getBeatingMoves(
        Chessboard board, Position source, Position target, Position enPassant) {
        var moves = new HashSet<Move>();

        if (target.isOnBoard()) {
            if (canBeatRegular(board, target)) {
                moves.addAll(getSimpleOrPromotionMove(board, source, target));
            }
            if (canBeatEnPassant(board, enPassant)) {
                moves.add(new EnPassantMove(source, target));
            }
        }
        return moves;
    }

    /**
     * Checks if there is a piece on the diagonal neighbour square which the piece can beat.
     *
     * @param board
     * @param target
     * @return boolean whether it can beat regularly
     */
    private boolean canBeatRegular(Chessboard board, Position target) {
        return board.getPiece(target, getColor().opponent()).isPresent();
    }

    /**
     * Checks if an enpassant move is possible.
     *
     * @param board
     * @param enPassant the Position of the piece that is beaten
     * @return boolean if beating with enpassant is possible
     */
    private boolean canBeatEnPassant(Chessboard board, Position enPassant) {
        int enPassantRow = getColor() == Color.White ? 5 : 4;

        if (enPassant.getRow() == enPassantRow) {
            return board.getPiece(enPassant, getColor().opponent())
                .filter(p -> enPassantBeatableFilter(board, p, enPassant))
                .isPresent();
        } else {
            return false;
        }
    }

    /**
     * Checks if pawn that chooses enpassant move has not moved yet.
     *
     * @param board
     * @param piece that beats
     * @param enPassant
     * @return bool if the piece can beat after filter
     */
    private boolean enPassantBeatableFilter(Chessboard board, Piece piece, Position enPassant) {
        return piece instanceof Pawn && piece.getMoveCount() == 1
            && board.getLastMove().getTarget().equals(enPassant);
    }

    /**
     * Straight moves forwward - either Simple or Promotion.
     *
     * @param board
     * @param source
     * @return Set of moves the push one or two
     */
    private Set<Move> getPushes(Chessboard board, Position source) {
        var moves = new HashSet<Move>();

        var singlePush = source.offset(direction, 0);
        var doublePush = source.offset(2 * direction, 0);

        if (singlePush.isOnBoard() && board.getPiece(singlePush).isEmpty()) {
            moves.addAll(getSimpleOrPromotionMove(board, source, singlePush));

            if (doublePush.isOnBoard() && source.getRow() == initialRow
                && board.getPiece(doublePush).isEmpty()) {
                moves.add(new BasicMove(source, doublePush));
            }
        }
        return moves;
    }

    /**
     * All possible combinations of piece transitions from pawn to another piece through promotion.
     *
     * @param source
     * @param target
     * @return Set of moves after promotion-transition
     */
    private Set<Move> getAllPromotionMoveCombinations(Position source, Position target) {
        var moves = new HashSet<Move>();
        moves.add(new PromotionMove(source, target, new Queen(this.getColor())));
        moves.add(new PromotionMove(source, target, new Knight(this.getColor())));
        moves.add(new PromotionMove(source, target, new Rook(this.getColor())));
        moves.add(new PromotionMove(source, target, new Bishop(this.getColor())));
        return moves;
    }

    /**
     * Gets the correct {@link Move} type.
     *
     * @param board  the board
     * @param source the starting position
     * @param target the target position
     * @return the correct move
     */
    protected Set<Move> getSimpleOrPromotionMove(
        Chessboard board, Position source, Position target) {
        var moves = new HashSet<Move>();

        if (isPromotionMove(target)) {
            moves.addAll(getAllPromotionMoveCombinations(source, target));
        } else {
            moves.add(new BasicMove(source, target));
        }

        return moves;
    }

    /**
     * Checks if the move is a promotion move.
     *
     * @param target
     * @return boolean if move is promotion
     */
    private boolean isPromotionMove(Position target) {
        int opponentGroundLine = getColor() == Color.White ? 8 : 1;

        return target.getRow() == opponentGroundLine;
    }
}
