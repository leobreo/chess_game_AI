package schach.gui;

import javafx.scene.image.Image;
import schach.Symbols;
import schach.piece.Piece;

public class ImageSymbols implements Symbols<Image> {
    /**
     * @param color The color
     * @return The symbol for Queen
     */
    @Override
    public Image queen(Piece.Color color) {
        return color == Piece.Color.White
                ? new Image(getClass().getResourceAsStream("/images/qw.png"))
                : new Image(getClass().getResourceAsStream("/images/qb.png"));
    }

    /**
     * @param color The color
     * @return The symbol for King
     */
    @Override
    public Image king(Piece.Color color) {
        return color == Piece.Color.White
                ? new Image(getClass().getResourceAsStream("/images/kw.png"))
                : new Image(getClass().getResourceAsStream("/images/kb.png"));
    }

    /**
     * @param color The color
     * @return The symbol for Rook
     */
    @Override
    public Image rook(Piece.Color color) {
        return color == Piece.Color.White
                ? new Image(getClass().getResourceAsStream("/images/rw.png"))
                : new Image(getClass().getResourceAsStream("/images/rb.png"));
    }

    /**
     * @param color The color
     * @return The symbol for Bishop
     */
    @Override
    public Image bishop(Piece.Color color) {
        return color == Piece.Color.White
                ? new Image(getClass().getResourceAsStream("/images/bw.png"))
                : new Image(getClass().getResourceAsStream("/images/bb.png"));
    }

    /**
     * @param color The color
     * @return The symbol for Knight
     */
    @Override
    public Image knight(Piece.Color color) {
        return color == Piece.Color.White
                ? new Image(getClass().getResourceAsStream("/images/nw.png"))
                : new Image(getClass().getResourceAsStream("/images/nb.png"));
    }

    /**
     * @param color The color
     * @return The symbol for Pawn
     */
    @Override
    public Image pawn(Piece.Color color) {
        return color == Piece.Color.White
                ? new Image(getClass().getResourceAsStream("/images/pw.png"))
                : new Image(getClass().getResourceAsStream("/images/pb.png"));
    }
}
