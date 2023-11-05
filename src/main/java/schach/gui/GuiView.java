package schach.gui;

import java.util.*;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import schach.*;
import schach.move.Move;
import schach.piece.*;

/**
 * Main GUI Window
 *
 * @author K. Schweppe
 * @author I. Kocic
 * @version 2.5
 */
public class GuiView extends VBox implements schach.View {
    private Settings settings;

    /**
     * Main GUI Window
     */
    protected Stage mainStage;

    private GridPane mainGrid;
    private Board board;
    private FlowPane beaten;

    private Button pvcButton;
    private Button pvpButton;
    private Button settingsButton;

    private Button quitButton;

    private GridPane settingsPane;

    /**
     * Create a GUI GuiView
     */
    public GuiView(Stage mainStage) {
        this.mainStage = mainStage;
        this.settings = new Settings();

        setAlignment(Pos.CENTER);
        setSpacing(10);

        Button btnENG = new Button("English");
        Button btnGER = new Button("Deutsch");
        Button btnBCS = new Button("Srpski");
        getChildren().addAll(btnENG, btnGER, btnBCS);

        initializeButtons();

        settingsPane = createSettingsPane();

        btnENG.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                settings.setLanguage("English");
                setupMainWindow(settings.getBundle());
                annotateButtons(settings.getBundle());
            }
        });

        btnGER.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                settings.setLanguage("German");
                setupMainWindow(settings.getBundle());
                annotateButtons(settings.getBundle());
            }
        });
    }

    @Override
    public void drawBoard(Chessboard chessboard) {
        board.updateBoard(chessboard);
        Platform.runLater(() -> listBeaten(chessboard.getBeatenPieces()));
    }

    /**
     * Lists beaten figures in a FlowPane
     * @param beatenPieces
     */
    public void listBeaten(Stack<Piece> beatenPieces) {
        if (beaten.getChildren().size() < beatenPieces.size()) {
            var image = new ImageView(beatenPieces.peek().getSymbol(new ImageSymbols()));
            image.setFitWidth(40);
            image.setPreserveRatio(true);
            beaten.getChildren().add(image);
        }
    }

    @Override
    public void showStatus(Game.GameState state) {
        switch (state) {
            case Checkmate:
                System.out.println("Checkmate");
                Platform.runLater(() -> {
                    var checkAlert = new Alert(Alert.AlertType.INFORMATION, "Checkmate.");
                    checkAlert.showAndWait();
                });
            case Draw:
                Platform.runLater(() -> {
                    var checkAlert = new Alert(Alert.AlertType.INFORMATION, "Draw.");
                    checkAlert.showAndWait();
                });
            default:
                System.out.println("Bla");
        }
    }

    @Override
    public void confirmMove(Move move) {
        Platform.runLater(() -> getBoard().getHistory().add(move.toString()));
    }

    @Override
    public void notifyCheck(Piece.Color color) {
        if (settings.isInformOfCheckEnabled()) {
            Platform.runLater(() -> {
                        var checkAlert = new Alert(Alert.AlertType.WARNING, "You are currently in Check");
                        checkAlert.showAndWait();
                    });
        }
    }

    /**
     * Main window instantiation
     * @param bundle
     */
    public void setupMainWindow(ResourceBundle bundle) {
        resetBoard();
        // For resizing
        mainGrid = new GridPane();

        var height = 650;
        var width = 800;
        var scene = new Scene(mainGrid, width, height);
        mainGrid.setMinWidth(320);
        mainGrid.setMinHeight(320);
        mainStage.setScene(scene);

        mainGrid.setAlignment(Pos.CENTER);
        mainGrid.setHgap(30);
        mainGrid.setVgap(30);
        mainGrid.setPadding(new Insets(25, 25, 25, 25));

        var welcomeTitle = new Text(bundle.getString("welcome"));
        welcomeTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        mainGrid.add(welcomeTitle, 0, 0, 1, 1);
        mainGrid.add(pvpButton, 0, 1);
        mainGrid.add(pvcButton, 0, 2);
        mainGrid.add(settingsButton, 0, 3);
        mainGrid.add(quitButton, 0, 5);

        mainGrid.getChildren().add(createVerticalSeparator(height));

        mainGrid.add(board, 2, 1, 8, 8);

        var history = new ListView<String>();
        history.setPrefWidth(70);
        history.setPrefHeight(100);
        history.setItems(board.getHistory());
        mainGrid.add(history, 0, 6, 1, 6);

        beaten = new FlowPane();
        mainGrid.add(beaten, 2, 10, 8, 1);
        settingsPane = createSettingsPane();
    }

    /**
     * Pane containing settings instantional
     * @return
     */
    private GridPane createSettingsPane() {
        var settingsPane = new GridPane();
        Text settingsTitle = new Text(settings.getBundle().getString("settings"));
        settingsTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        settingsPane.setHgap(30);
        settingsPane.setVgap(30);
        var checkNotification = new ToggleButton("Check Notification");
        settingsPane.add(checkNotification, 0, 1);
        var automaticRotate = new ToggleButton("Automatic Rotate");
        settingsPane.add(automaticRotate, 0, 2);
        var highlightLegal = new ToggleButton("Highlight Legal");
        settingsPane.add(highlightLegal, 0, 3);
        settingsPane.add(settingsTitle, 0, 0);

        highlightLegal.selectedProperty().bindBidirectional(
            settings.highlightLegalMovesEnabledProperty());

        automaticRotate.selectedProperty().bindBidirectional(
            settings.automaticRotateEnabledProperty());

        checkNotification.selectedProperty().bindBidirectional(
            settings.informOfCheckEnabledProperty());

        return settingsPane;
    }

    /**
     * Shows the Pane
     * @param settingsPane
     */
    private void displaySettingsPane(GridPane settingsPane) {
        mainGrid.getChildren().remove(board);
        mainGrid.add(settingsPane, 2, 1, 8, 8);
    }

    /**
     * Hides the settings Pane
     * @param settingsPane
     */
    private void closeSettingsPane(GridPane settingsPane) {
        mainGrid.getChildren().remove(settingsPane);
        mainGrid.add(board, 2, 1, 8, 8);
    }

    /**
     * Button initialization
     */
    private void initializeButtons() {
        pvpButton = new Button();
        pvcButton = new Button();
        settingsButton = createSettingsButton();
        quitButton = new Button();
    }

    /**
     * Adds text to the buttons after language set
     * @param bundle
     */
    private void annotateButtons(ResourceBundle bundle) {
        pvpButton.setText(bundle.getString("pvp"));
        pvcButton.setText(bundle.getString("pvc"));
        settingsButton.setText(bundle.getString("settings"));
        quitButton.setText(bundle.getString("quit"));
    }

    /**
     * Vertical separator to divide scene
     * @param sceneHeight
     * @return
     */
    private Separator createVerticalSeparator(int sceneHeight) {
        var separator = new Separator();
        separator.setValignment(VPos.CENTER);
        separator.setOrientation(Orientation.VERTICAL);
        separator.setPrefHeight(sceneHeight * 0.9);
        GridPane.setConstraints(separator, 1, 1);
        GridPane.setRowSpan(separator, 6);
        return separator;
    }

    /**
     * Let player decide his piece color for the game
     * @return
     */
    public Piece.Color promptPlayerForColor() {
        var bundle = settings.getBundle();
        List<String> choices = Arrays.asList(bundle.getString("white"), bundle.getString("black"));
        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setHeaderText(bundle.getString("playerColorPrompt"));
        dialog.setContentText(bundle.getString("playerColorPromptContext"));
        dialog.setTitle(bundle.getString("playerColorPromptTitle"));
        ((Button) dialog.getDialogPane().lookupButton(ButtonType.OK))
            .setText(bundle.getString("ok"));
        ((Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL))
            .setText(bundle.getString("cancel"));
        Optional<Piece.Color> color = dialog.showAndWait().map(
            c -> bundle.getString("white").equals(c) ? Piece.Color.White : Piece.Color.Black);
        // TODO: Fix with color.ifPresent(c -> {return c;}) or some such.
        return color.get();
    }

    /**
     * Let player choose how to promote pawn
     * @param playerColor
     * @return
     */
    public Piece promptPlayerForPromotionPiece(Piece.Color playerColor) {
        var bundle = settings.getBundle();

        var q = bundle.getString("queen");
        var r = bundle.getString("rook");
        var b = bundle.getString("bishop");
        var n = bundle.getString("knight");

        List<String> choices = Arrays.asList(q, r, b, n);
        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setHeaderText(bundle.getString("promotionPrompt"));
        dialog.setContentText(bundle.getString("promotionPromptContext"));
        dialog.setTitle(bundle.getString("promotionPromptTitle"));
        ((Button) dialog.getDialogPane().lookupButton(ButtonType.OK))
            .setText(bundle.getString("ok"));
        ((Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL))
            .setText(bundle.getString("cancel"));

        Optional<Piece> piece = dialog.showAndWait().map(c -> {
            if (c.equals(r)) {
                return new Rook(playerColor);
            } else if (c.equals(b)) {
                return new Bishop(playerColor);
            } else if (c.equals(n)) {
                return new Knight(playerColor);
            } else {
                return new Queen(playerColor);
            }
        });
        return piece.get();
    }

    /**
     * Instantiate settings button and setup onClick behavior
     * @return
     */
    private Button createSettingsButton() {
        var b = new Button();
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (mainGrid.getChildren().contains(settingsPane)) {
                    closeSettingsPane(settingsPane);
                } else {
                    displaySettingsPane(settingsPane);
                }
            }
        });
        return b;
    }

    /**
     * Gets the Board, which is the View's equivalent of the Chessboard
     * @return
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Access game settings
     * @return
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Get Player vs Comp. Button (so that controller can setup onClick behavior)
     * @return
     */
    public Button getPvcButton() {
        return pvcButton;
    }

    /**
     * Get Player vs Player Button (so that controller can setup onClick behavior)
     * @return
     */
    public Button getPvpButton() {
        return pvpButton;
    }

    /**
     * Get quit button (so that controller can setup onClick behavior)
     * @return
     */
    public Button getQuitButton() {
        return quitButton;
    }

    /**
     * Reset board, for a clean new game
     */
    public void resetBoard() {
        board = new Board(settings);
    }
}
