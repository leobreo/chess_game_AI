package schach;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import schach.gui.GuiController;
import schach.gui.GuiView;

/**
 * GUI application
 *
 * @author K. Schweppe
 * @author I. Kocic
 * @version 1.3
 */
public class GUI extends Application {
    /**
     * Main GUI entry point
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var view = new GuiView(stage);

        var scene = new Scene(view, 300, 260);

        var controller = new GuiController(view);

        stage.setScene(scene);
        stage.show();
    }
}
