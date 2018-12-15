package amazons;
/** A Player that takes input as text commands from the standard input.
 *  @author Harry Zhang
 */
class TextPlayer extends Player {

    /** A new TextPlayer with no piece or controller (intended to produce
     *  a template). */
    TextPlayer() {
        this(null, null);
    }

    /** A new TextPlayer playing PIECE under control of CONTROLLER. */
    private TextPlayer(Piece piece, Controller controller) {
        super(piece, controller);
    }

    @Override
    Player create(Piece piece, Controller controller) {
        return new TextPlayer(piece, controller);
    }

    @Override
    String myMove() {
        while (true) {
            String line = _controller.readLine();
            if (line == null) {
                return "quit";
            } else if (Move.isGrammaticalMove(line)) {
                Piece turn = _controller.board().turn();
                Move mv = Move.mv(line);
                if (mv == null) {
                    _controller.reportError("Invalid move. "
                            + "Please try again.");
                    continue;
                }
                Piece inputTurn = _controller.board().get(mv.from());
                if (turn != inputTurn || !_controller.board().isLegal(mv)) {
                    _controller.reportError("Invalid move. "
                            + "Please try again.");
                    continue;
                }
            }
            return line;
        }
    }
}
