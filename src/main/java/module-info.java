module schach {
    requires javafx.controls;
    requires transitive javafx.graphics;

    exports schach;
    exports schach.cli;
    exports schach.gui;
    exports schach.piece;
    exports schach.move;
    opens schach;
}
