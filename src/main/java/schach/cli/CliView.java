package schach.cli;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import schach.Chessboard;
import schach.Game.GameState;
import schach.Position;
import schach.Symbols;
import schach.View;
import schach.move.Move;
import schach.piece.Piece;
import schach.piece.Piece.Color;

/**
 * The CLI output class
 *
 * @author K. Schweppe
 * @version 1.1
 */
public class CliView implements View {
    private Symbols<Character> symbols;
    private PrintStream out;

    /**
     * Create the View
     */
    public CliView(Symbols<Character> symbols) {
        this.symbols = symbols;

        try {
            out = new PrintStream(System.out, true, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            printMessage(e.getMessage());
            printMessage("Using letters instead of Unicode symbols.");
            this.symbols = new ASCIISymbols();
            out = new PrintStream(System.out, true);
        }
    }

    /**
     * Print a message
     *
     * @param message The message
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void confirmMove(Move move) {
        printMessage("!" + move.toString());
    }

    /**
     * Print beaten pieces
     *
     * @param beaten All beaten piece
     */
    public void listBeaten(Stream<Piece> beaten) {
        out.println(
            beaten.map(p -> p.getSymbol(symbols).toString()).collect(Collectors.joining(" ")));
    }

    /**
     * Draw the chessboard
     *
     * @param board The board
     */
    @Override
    public void drawBoard(Chessboard board) {
        for (int row = 8; row >= 1; row--) {
            System.out.printf("%d ", row);
            for (int col = 1; col <= 8; col++) {
                var position = new Position(row, col);
                var symbol = board.getPiece(position).map(p -> p.getSymbol(symbols)).orElse(' ');
                out.printf("%s ", symbol);
            }
            System.out.println();
        }
        printMessage("  a b c d e f g h");
    }

    @Override
    public void showStatus(GameState state) {
        switch (state) {
            case Checkmate:
                printMessage("Checkmate");
            default:
        }
    }

    @Override
    public void notifyCheck(Color color) {
        printMessage("Check");
    }
}
