package amazons;

/** A Player that takes input from a GUI.
 *  @author Harry Zhang
 */
class GUIPlayer extends Player implements Reporter {

    /** A new GUIPlayer that takes moves and commands from GUI.  */
    GUIPlayer(GUI gui) {
        this(null, null, gui);
    }

    /** A new GUIPlayer playing PIECE under control of CONTROLLER, taking
     *  moves and commands from GUI. */
    GUIPlayer(Piece piece, Controller controller, GUI gui) {
        super(piece, controller);
        _gui = gui;
    }

    @Override
    Player create(Piece piece, Controller controller) {
        return new GUIPlayer(piece, controller, _gui);
    }

    @Override
    String myMove() {
        while (true) {
            String line = _gui.readCommand();
            line = line.trim();
            if (line == null) {
                return "quit";
            } else if (Move.isGrammaticalMove(line)) {
                Piece turn = _controller.board().turn();
                Move mv = Move.mv(line);
                if (mv == null) {
                    _gui.reportError("Invalid move. "
                            + "Please try again.");
                    continue;
                }
                Piece inputTurn = _controller.board().get(mv.from());
                if (turn != inputTurn || !_controller.board().isLegal(mv)) {
                    _gui.reportError("Invalid move. "
                            + "Please try again.");
                    continue;
                }
            }
            return line;
        }
    }

    @Override
    public void reportError(String fmt, Object... args) {
        _gui.reportError(fmt, args);
    }

    @Override
    public void reportNote(String fmt, Object... args) {
        _gui.reportNote(fmt, args);
    }

    @Override
    public void reportMove(Move unused) {
    }

    /** The GUI I use for input. */
    private GUI _gui;
}
