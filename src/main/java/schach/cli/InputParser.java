package schach.cli;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import schach.Position;
import schach.move.BasicMove;
import schach.move.CastlingMove;
import schach.move.EnPassantMove;
import schach.move.Move;
import schach.move.PromotionMove;
import schach.piece.Bishop;
import schach.piece.Knight;
import schach.piece.Piece;
import schach.piece.Queen;
import schach.piece.Rook;

/**
 * Class of Input Parser to process the inputs
 */
public class InputParser {
    private final Pattern moveCommandPattern;

    /**
     * Input Parser Constructor
     */
    public InputParser() {
        moveCommandPattern = Pattern.compile("^([a-h])([1-8])-([a-h])([1-8])([QNRB])?$");
    }

    /**
     * Interpreter of raw console input.
<<<<<<< HEAD
     * The move is being categorized into possible special move categories and then checked in the
     * indivual method.
=======
     * The move is being categorized into possible special move categories and then checked in the indivual method.
>>>>>>> Most of missing JavaDocs
     *
     * @param rawInput directly from sys.in
     * @return Optionally returns Command type
     */
    public List<Move> inputToPossibleMoves(String rawInput, Piece.Color color) {
        var moves = new LinkedList<Move>();
        var patternMatcher = moveCommandPattern.matcher(rawInput);

        if (patternMatcher.matches()) {
            var source = new Position(Integer.parseInt(patternMatcher.group(2)),
                translate(patternMatcher.group(1).charAt(0)));
            var target = new Position(Integer.parseInt(patternMatcher.group(4)),
                translate(patternMatcher.group(3).charAt(0)));
            var promotionPiece = Optional.ofNullable(patternMatcher.group(5))
                                     .map(s -> promotionPieceMap(s.charAt(0), color))
                                     .orElse(new Queen(color));

            moves.add(new BasicMove(source, target));
            if (isValidEnPassantInput(source, target)) {
                moves.add(new EnPassantMove(source, target));
            }
            if (isValidPromotionInput(source, target)) {
                moves.add(new PromotionMove(source, target, promotionPiece));
            }
            if (isValidCastlingInput(source, target)) {
                moves.add(new CastlingMove(source, target));
            }
        }
        return moves;
    }

    /**
     * Checks if the move can be interpreted as an Enpassant move
<<<<<<< HEAD
     *
=======
     * 
>>>>>>> Most of missing JavaDocs
     * @param source
     * @param target
     * @return boolean if the move actually is a valid Enpassant move
     */
    public boolean isValidEnPassantInput(Position source, Position target) {
        var validWhiteSourceRank = source.getRow() == 5;
        var validWhiteTargetRank = target.getRow() == 6;

        var validBlackSourceRank = source.getRow() == 4;
        var validBlackTargetRank = target.getRow() == 3;

        var validFileOffset = Math.abs(target.getCol() - source.getCol()) == 1;

        return validFileOffset
            && (validWhiteSourceRank && validWhiteTargetRank
                || validBlackSourceRank && validBlackTargetRank);
    }
    /**
     * Checks if the move can be interpreted as a Promotion move
<<<<<<< HEAD
     *
=======
     * 
>>>>>>> Most of missing JavaDocs
     * @param source
     * @param target
     * @return boolean if the move actually is a valid Promotion move
     */
    public boolean isValidPromotionInput(Position source, Position target) {
        var validWhiteSourceRank = source.getRow() == 7;
        var validWhiteTargetRank = target.getRow() == 8;

        var validBlackSourceRank = source.getRow() == 2;
        var validBlackTargetRank = target.getRow() == 1;

        var fileOffset = Math.abs(target.getCol() - source.getCol());
        var validFileOffset = fileOffset == 0 || fileOffset == 1;

        return validFileOffset
            && (validWhiteSourceRank && validWhiteTargetRank
                || validBlackSourceRank && validBlackTargetRank);
    }

    /**
     * Checks if the move can be interpreted as a Castling move
<<<<<<< HEAD
     *
=======
     * 
>>>>>>> Most of missing JavaDocs
     * @param source
     * @param target
     * @return boolean if the move actually is a valid Castling move
     */
    public boolean isValidCastlingInput(Position source, Position target) {
        var validWhiteRank = source.getRow() == 1 && target.getRow() == 1;
        var validBlackRank = source.getRow() == 8 && target.getRow() == 8;

        var validSourceFile = source.getCol() == 5;
        var validTargetFile = target.getCol() == 7 || target.getCol() == 3;

        return validSourceFile && validTargetFile && (validWhiteRank || validBlackRank);
    }

    /**
     * Translate column index formats (letter->number)
     *
     * @param chessColumnIndex as letters a-h
     * @return matrix column index
     */
    public static int translate(char chessColumnIndex) {
        var horizontal = "abcdefgh";
        return horizontal.indexOf(chessColumnIndex) + 1;
    }

    /**
     * Interpret and pass promotion piece
     *
     * @param piece
     * @param color
     * @return Piece to which the Pawn will be promoted
     */
    public Piece promotionPieceMap(char piece, Piece.Color color) {
        switch (piece) {
            case 'R':
                return new Rook(color);
            case 'B':
                return new Bishop(color);
            case 'N':
                return new Knight(color);
            default:
                return new Queen(color);
        }
    }
}
