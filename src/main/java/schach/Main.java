package schach;

/**
 * The Main class
 *
 * @author K. Schweppe
 * @version 1.0
 */
public class Main {
    /**
     * Main entry point
     * @param args
     */
    public static void main(String[] args) {
        var options = Options.parse(args);

        if (options.isGUI()) {
            GUI.main(args);
        } else {
            CLI.main(options.isSimple());
        }
    }
}
