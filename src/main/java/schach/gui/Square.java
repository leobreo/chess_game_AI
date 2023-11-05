package schach.gui;

import java.util.Optional;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import schach.piece.Piece;

public class Square extends StackPane {
    final int row, col;
    final Color fieldColor;
    int size;
    boolean stroke;
    final private Rectangle rectangle;
    final private ImageView image;
    final private Circle marker;

    /**
     * Instantiate a Square, the View's equivalent of a chessboard field
     * @param row
     * @param col
     * @param color
     * @param size
     */
    public Square(int row, int col, Color color, int size) {
        rectangle = new Rectangle(size, size, color);
        this.row = row;
        this.col = col;
        this.size = size;
        this.fieldColor = color;
        this.stroke = false;
        image = new ImageView();
        marker = new Circle();
        marker.setRadius(10);
        marker.setFill(Color.TRANSPARENT);
        this.getChildren().addAll(rectangle, image, marker);

    }

    /**
     * Toggle a green inward border around the Square
     */
    public void toggleStroke() {
        if (!stroke) {
            rectangle.setStrokeWidth(size / 8);
            rectangle.setStrokeType(StrokeType.INSIDE);
            rectangle.setStroke(Color.SEAGREEN);
        } else {
            rectangle.setStrokeWidth(0);
            rectangle.setStroke(Color.TRANSPARENT);
        }
        stroke = !stroke;
    }

    /**
     * Show new image on Square
     * @param piece
     */
    public void updateImage(Optional<Piece> piece) {
        if (piece.isPresent()) {
            image.setImage(piece.get().getSymbol(new ImageSymbols()));
        } else {
            image.setImage(null);
        }
    }

    /**
     * Get row location
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * Get column location
     * @return
     */
    public int getCol() {
        return col;
    }

    /**
     * Toggle the possible move marking from the Square
     */
    public void toggleMarkAsPossible() {
        if (marker.getFill() == Color.TRANSPARENT) {
            marker.setFill(Color.DARKGRAY);
        } else {
            marker.setFill(Color.TRANSPARENT);
        }
    }
}
