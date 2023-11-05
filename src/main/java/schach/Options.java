package schach;

/**
 * Command line options
 *
 * @author  K. Schweppe
 * @version 1.0
 */
public class Options {
    private boolean GUI = true;
    private boolean simple = true;

    /**
     * Parse command line options from a list of Strings
     *
     * @param args List of arguments
     * @return Options object
     */
    public static Options parse(String[] args) {
        var opts = new Options();

        for (var arg : args) {
            if (arg.equals("--no-gui")) {
                opts.GUI = false;
            } else if (arg.equals("--simple")) {
                opts.simple = true;
            } else {
                System.err.printf("Invalid option '%s'\n", arg);
            }
        }

        return opts;
    }

    /**
     * @return true, if the 'no-gui' flag is not set
     */
    public boolean isGUI() {
        return GUI;
    }

    /**
     * @return true, if the 'simple' flag is set
     */
    public boolean isSimple() {
        return simple;
    }
}
