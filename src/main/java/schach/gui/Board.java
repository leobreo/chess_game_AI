package schach.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import schach.Chessboard;
import schach.Position;
import schach.piece.Piece;

public class Board extends GridPane {
    private Square[][] squares = new Square[8][8];

    private Settings settings;
    private Optional<Square> to;
    private Optional<Square> fro;
    private Optional<Piece> promotionPiece;
    private final ObservableList<String> history = FXCollections.observableList(new LinkedList<>());
    private List<Consumer<Position>> onSquareSelected;
    private Set<Position> markedAsPossible;

    /**
     * Instantiate
     * @param settings
     */
    public Board(Settings settings) {
        this.settings = settings;
        this.to = Optional.empty();
        this.fro = Optional.empty();
        this.promotionPiece = Optional.empty();
        createBlankBoard(60, settings);
        onSquareSelected = new LinkedList<>();
        markedAsPossible = Set.of();
    }

    /**
     * Add consumers
     * @param consumer
     */
    public void setOnSquareSelected(Consumer<Position> consumer) {
        onSquareSelected.add(consumer);
    }

    /**
     * Mark possible square targets
     * @param targets
     */
    public void showPossibleTargets(Set<Position> targets) {
        for (var marked : markedAsPossible) {
            getSquare(marked.getRow(), marked.getCol()).toggleMarkAsPossible();
        }
        for (var pos : targets) {
            getSquare(pos.getRow(), pos.getCol()).toggleMarkAsPossible();
        }
        markedAsPossible = targets;
    }

    /**
     * Getter for squares
     * @param row
     * @param col
     * @return
     */
    public Square getSquare(int row, int col) {
        return squares[row - 1][col - 1];
    }

    /**
     * Instantiate blank board, with fields with appropriate onAction behavior
     * @param fieldSize
     * @param settings
     */
    private void createBlankBoard(int fieldSize, Settings settings) {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                var color = (j % 2 == 0 && i % 2 == 0) || (j % 2 == 1 && i % 2 == 1)
                    ? Color.SADDLEBROWN
                    : Color.ANTIQUEWHITE;
                // The 9-i is for generating tile 1,1 in the lower left corner
                var field = createField(i, j, color, fieldSize);
                add(field, j, 9 - i);
                addField(field);
            }
        }
    }


    /**
     * Instantiate a field with appropriate onClick behavior
     * @param row
     * @param col
     * @param color
     * @param size
     * @return
     */
    private Square createField(int row, int col, Color color, int size) {
        var field = new Square(row, col, color, size);
        // TODO: Make field squares resizable with clever dimension bindings, see non-working code
        // below for inspiration
        //        field.heightProperty().bind(
        //                 grid.heightProperty().getValue() > grid.widthProperty().getValue() ?
        //                        grid.heightProperty().divide(8) :
        //                        grid.widthProperty().divide(8)
        //        );
        //        field.widthProperty().bind(field.heightProperty());
        field.setOnMouseClicked(mouseEvent -> {
            var rotatedSquare = getSquare(row, col);
            onSquareSelected.forEach(
                f -> f.accept(new Position(rotatedSquare.getRow(), rotatedSquare.getCol())));
        });
        return field;
    }

    /**
     * Add a field to the mapping of squares
     * @param square
     */
    public void addField(Square square) {
        squares[square.getRow() - 1][square.getCol() - 1] = square;
    }

    /**
     * Update state of the Board
     * @param chessboard
     */
    public void updateBoard(Chessboard chessboard) {
        for (int row = 8; row >= 1; row--) {
            for (int col = 1; col <= 8; col++) {
                getSquare(row, col).updateImage(chessboard.getPiece(new Position(row, col)));
            }
        }
    }

    /**
     * Rotate Board
     * @param rotate
     */
    private void rotateBoard(boolean rotate) {
        getChildren().clear();
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                int translatedRow = rotate ? 9 - row : row;
                add(getSquare(row, col), col, 9 - translatedRow);
            }
        }
    }

    /**
     * Graceful rotation of board via threads
     * @param rotate
     */
    public void requestRotation(boolean rotate) {
        Platform.runLater(() -> rotateBoard(settings.isAutomaticRotateEnabled() && rotate));
    }

    /**
     * History getter
     * @return
     */
    public ObservableList<String> getHistory() {
        return history;
    }
}
