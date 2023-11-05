package schach;

import java.util.HashMap;
import java.util.Map;
import schach.piece.Bishop;
import schach.piece.King;
import schach.piece.Knight;
import schach.piece.Pawn;
import schach.piece.Piece;
import schach.piece.Piece.Color;
import schach.piece.Queen;
import schach.piece.Rook;

/**
 * Helper for writing tests
 */
public class TestUtils {
    /**
     * Parse a {@link Chessboard} from a string
     *
     * @param board String definition of the board
     * @return chessboard
     */
    public static Chessboard parseBoard(String board) {
        var pieces = new HashMap<Position, Piece>();
        Position whiteKingPos = null;
        Position blackKingPos = null;
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                var c = board.charAt(((8 - row) * 8 + col - 1) * 2);
                var isWhite = Character.isUpperCase(c);
                var color = isWhite ? Color.White : Color.Black;
                Piece piece = null;
                switch (Character.toLowerCase(c)) {
                    case 'q':
                        piece = new Queen(color);
                        break;
                    case 'r':
                        piece = new Rook(color);
                        break;
                    case 'b':
                        piece = new Bishop(color);
                        break;
                    case 'k':
                        piece = new King(color);
                        if (color == Color.White) {
                            whiteKingPos = new Position(row, col);
                        } else {
                            blackKingPos = new Position(row, col);
                        }
                        break;
                    case 'n':
                        piece = new Knight(color);
                        break;
                    case 'p':
                        piece = new Pawn(color);
                        break;
                    default:
                        break;
                }
                if (piece != null) {
                    pieces.put(new Position(row, col), piece);
                }
            }
        }
        return new Chessboard(pieces, whiteKingPos, blackKingPos);
    }

    /**
     * Draw the board
     */
    public static String drawBoard(Chessboard board) {
        var out = new StringBuilder();
        out.append("\n");
        var symbols = new schach.cli.ASCIISymbols();
        for (int row = 8; row >= 1; row--) {
            for (int col = 1; col <= 8; col++) {
                var pos = new Position(row, col);
                var symbol = board.getPiece(pos).map(p -> p.getSymbol(symbols)).orElse('.');
                out.append(symbol);
                out.append(col == 8 ? '\n' : ' ');
            }
        }
        return out.toString();
    }
}
