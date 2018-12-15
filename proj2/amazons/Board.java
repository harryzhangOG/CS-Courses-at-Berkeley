package amazons;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import static amazons.Piece.*;
/** The state of an Amazons Game.
 *  @author Harry Zhang
 */
class Board {

    /** The number of squares on a side of the board. */
    static final int SIZE = 10;

    /** Initializes a game board with SIZE squares on a side in the
     *  initial position. */
    Board() {
        init();
    }

    /** Initializes a copy of MODEL. */
    Board(Board model) {
        copy(model);
    }

    /** Copies MODEL into me. */
    void copy(Board model) {
        _turn = model._turn;
        _winner = model._winner;
        for (int col = 0; col < Board.SIZE; col++) {
            for (int row = 0; row < Board.SIZE; row++) {
                Piece piece = model.get(col, row);
                this.put(piece, Square.sq(col, row));
            }
        }
        _numMoves = model._numMoves;
    }

    /** Clears the board to the initial position. */
    void init() {
        _turn = WHITE;
        _winner = EMPTY;
        for (int i = 0; i < SIZE; i += 1) {
            for (int j = 0; j < SIZE; j += 1) {
                put(EMPTY, i, j);
            }
        }
        for (int[] w : _whitePosn) {
            put(WHITE, w[0], w[1]);
        }
        for (int[] b : _blackPosn) {
            put(BLACK, b[0], b[1]);
        }
    }

    /** Return the Piece whose move it is (WHITE or BLACK). */
    Piece turn() {
        return _turn;
    }

    /** Return the number of moves (that have not been undone) for this
     *  board. */
    int numMoves() {
        return _numMoves;
    }

    /** Return the winner in the current position, or null if the game is
     *  not yet finished. */
    Piece winner() {
        enumEmpty();
        if (emptyBlack() == 0) {
            _winner = WHITE;
        } else if (emptyWhite() == 0) {
            _winner = BLACK;
        } else {
            _winner = null;
        }
        return _winner;

    }
    /** DECIDE how many EMPTY PIECES on the BOARD. */
    void enumEmpty() {
        HashSet<Square> emptyBlack = new HashSet<>();
        HashSet<Square> emptyWhite = new HashSet<>();
        for (int i = 0; i < SIZE; i += 1) {
            for (int j = 0; j < SIZE; j += 1) {
                if (get(i, j).equals(BLACK)) {
                    for (int k = 0; k < DIR.length; k += 1) {
                        int newi = i + DIR[k][0];
                        int newj = j + DIR[k][1];
                        if (Square.exists(newi, newj)) {
                            if (get(i + DIR[k][0],
                                    j + DIR[k][1]).equals(EMPTY)) {
                                emptyBlack.add(Square.sq(i + DIR[k][0],
                                        j + DIR[k][1]));
                            }
                        }
                    }
                } else if (get(i, j).equals(WHITE)) {
                    for (int k = 0; k < DIR.length; k += 1) {
                        int newi = i + DIR[k][0];
                        int newj = j + DIR[k][1];
                        if (Square.exists(newi, newj)) {
                            if (get(i + DIR[k][0],
                                    j + DIR[k][1]).equals(EMPTY)) {
                                emptyWhite.add(Square.sq(i + DIR[k][0],
                                        j + DIR[k][1]));
                            }
                        }
                    }
                }
            }
        }
        _emptyBlack = emptyBlack.size();
        _emptyWhite = emptyWhite.size();
    }
    /** Return how many EMPTY PIECES left for WHITE. */
    int emptyWhite() {
        return _emptyWhite;
    }
    /** Return how many EMPTY PIECES left for BLACK. */
    int emptyBlack() {
        return _emptyBlack;
    }



    /** Return the contents the square at S. */
    final Piece get(Square s) {
        return _content.get(s);
    }

    /** Return the contents of the square at (COL, ROW), where
     *  0 <= COL, ROW <= 9. */
    final Piece get(int col, int row) {
        return _content.get(Square.sq(col, row));
    }

    /** Return the contents of the square at COL ROW. */
    final Piece get(char col, char row) {
        return get(col - 'a', row - '1');
    }

    /** Set square S to P. */
    final void put(Piece p, Square s) {
        put(p, s.col(), s.row());
    }

    /** Set square (COL, ROW) to P. */
    final void put(Piece p, int col, int row) {
        _content.put(Square.sq(col, row), p);
    }

    /** Set square COL ROW to P. */
    final void put(Piece p, char col, char row) {
        put(p, col - 'a', row - '1');
    }

    /** Return true iff FROM - TO is an unblocked queen move on the current
     *  board, ignoring the contents of ASEMPTY, if it is encountered.
     *  For this to be true, FROM-TO must be a queen move and the
     *  squares along it, other than FROM and ASEMPTY, must be
     *  empty. ASEMPTY may be null, in which case it has no effect. */
    boolean isUnblockedMove(Square from, Square to, Square asEmpty) {
        if (!from.isQueenMove(to)) {
            return false;
        } else {
            int dir = from.direction(to);
            int steps = from.steps(to);
            for (int i = 1; i <= steps; i += 1) {
                Square dest = from.queenMove(dir, i);
                if (!dest.equals(asEmpty)) {
                    if (!get(dest).equals(EMPTY)) {
                        return false;
                    }
                }

            }
            return true;
        }
    }

    /** Return true iff FROM is a valid starting square for a move. */
    boolean isLegal(Square from) {
        return get(from).equals(WHITE) || get(from).equals(BLACK);
    }

    /** Return true iff FROM-TO is a valid first part of move, ignoring
     *  spear throwing. */
    boolean isLegal(Square from, Square to) {
        return isUnblockedMove(from, to, null);
    }

    /** Return true iff FROM-TO(SPEAR) is a legal move in the current
     *  position. */
    boolean isLegal(Square from, Square to, Square spear) {
        return isLegal(from) && isUnblockedMove(from, to, null)
                &&  isUnblockedMove(to, spear, from);

    }

    /** Return true iff MOVE is a legal move in the current
     *  position. */
    boolean isLegal(Move move) {
        return isLegal(move.from(), move.to(), move.spear());
    }

    /** Move FROM-TO(SPEAR), assuming this is a legal move. */
    void makeMove(Square from, Square to, Square spear) {
        put(get(from), to);
        put(EMPTY, from);
        put(SPEAR, spear);
        _lastMove.push(Move.mv(from, to, spear));
        _numMoves += 1;

    }
    /** Switch TURN of the board. */
    void switchTurn() {
        _turn = turn().opponent();
    }

    /** Move according to MOVE, assuming it is a legal move. */
    void makeMove(Move move) {
        _lastMove.push(move);
        makeMove(move.from(), move.to(), move.spear());
    }

    /** Undo one move.  Has no effect on the initial board. */
    void undo() {
        Move lastMove = _lastMove.pop();
        Square from = lastMove.from();
        Square to = lastMove.to();
        Square spear = lastMove.spear();
        put(get(to), from);
        put(EMPTY, to);
        put(EMPTY, spear);
        enumEmpty();
        _numMoves -= 1;
    }

    /** Return an Iterator over the Squares that are reachable by an
     *  unblocked queen move from FROM. Does not pay attention to what
     *  piece (if any) is on FROM, nor to whether the game is finished.
     *  Treats square ASEMPTY (if non-null) as if it were EMPTY.  (This
     *  feature is useful when looking for Moves, because after moving a
     *  piece, one wants to treat the Square it came from as empty for
     *  purposes of spear throwing.) */
    Iterator<Square> reachableFrom(Square from, Square asEmpty) {
        return new ReachableFromIterator(from, asEmpty);
    }

    /** Return an Iterator over all legal moves on the current board. */
    Iterator<Move> legalMoves() {
        return new LegalMoveIterator(_turn);
    }

    /** Return an Iterator over all legal moves on the current board for
     *  SIDE (regardless of whose turn it is). */
    Iterator<Move> legalMoves(Piece side) {
        return new LegalMoveIterator(side);
    }

    /** An iterator used by reachableFrom. */
    private class ReachableFromIterator implements Iterator<Square> {

        /** Iterator of all squares reachable by queen move from FROM,
         *  treating ASEMPTY as empty. */
        ReachableFromIterator(Square from, Square asEmpty) {
            _from = from;
            _dir = -1;
            _steps = 0;
            _asEmpty = asEmpty;
            toNext();
        }

        @Override
        public boolean hasNext() {
            return _dir < 8;
        }

        @Override
        public Square next() {
            Square nextSquare = _from.queenMove(_dir, _steps);
            toNext();
            return nextSquare;
        }


        /** Advance _dir and _steps, so that the next valid Square is
         *  _steps steps in direction _dir from _from. */
        private void toNext() {
            if (_dir == -1) {
                _dir += 1;
            }
            if (_dir < 8) {
                Square toTemp = _from.queenMove(_dir, _steps + 1);
                if (!isUnblockedMove(_from, toTemp, _asEmpty)) {
                    _dir += 1;
                    _steps = 0;
                    toNext();
                } else {
                    _steps += 1;
                }
            } else {
                _steps += 1;
            }
        }

        /** Starting square. */
        private Square _from;
        /** Current direction. */
        private int _dir;
        /** Current distance. */
        private int _steps;
        /** Square treated as empty. */
        private Square _asEmpty;
    }

    /** An iterator used by legalMoves. */
    private class LegalMoveIterator implements Iterator<Move> {

        /** All legal moves for SIDE (WHITE or BLACK). */
        LegalMoveIterator(Piece side) {
            for (Square s : _content.keySet()) {
                if (_content.get(s).equals(side)) {
                    _fromPieceList.add(s);
                }
            }
            _startingSquares = _fromPieceList.iterator();
            _spearThrows = NO_SQUARES;
            _pieceMoves = NO_SQUARES;
            _fromPiece = side;
            bool = false;
            toNext();
        }

        @Override
        public boolean hasNext() {
            return _startingSquares.hasNext()
                    || _spearThrows.hasNext() || _pieceMoves.hasNext() || bool;
        }

        @Override
        public Move next() {
            Move returnVal = Move.mv(_start, _nextSquare, nextSpear);
            toNext();
            return returnVal;
        }

        /** Advance so that the next valid Move is
         *  _start-_nextSquare(sp), where sp is the next value of
         *  _spearThrows. */
        private void toNext() {
            if (_spearThrows.equals(NO_SQUARES)
                    && _pieceMoves.equals(NO_SQUARES)
                    && _startingSquares.hasNext()) {
                _start = _startingSquares.next();
                _pieceMoves = reachableFrom(_start, null);
                if (_pieceMoves.hasNext()) {
                    _nextSquare = _pieceMoves.next();
                    _spearThrows = reachableFrom(_nextSquare, _start);
                    if (_spearThrows.hasNext()) {
                        nextSpear = _spearThrows.next();
                    } else {
                        _pieceMoves = reachableFrom(_start, null);
                        toNext();
                    }
                } else {
                    _start = _startingSquares.next();
                    _pieceMoves = reachableFrom(_start, null);
                    toNext();
                }
            } else {
                if (_spearThrows.hasNext()) {
                    nextSpear = _spearThrows.next();
                    if (!_spearThrows.hasNext()) {
                        if (!_pieceMoves.hasNext()
                                && !_startingSquares.hasNext()) {
                            bool = true;
                        }
                    }
                } else if (!_startingSquares.hasNext()
                        && !_pieceMoves.hasNext()
                        && !_spearThrows.hasNext()) {
                    bool = false;
                } else if (_pieceMoves.hasNext()
                        && !_spearThrows.hasNext()) {
                    _nextSquare = _pieceMoves.next();
                    _spearThrows = reachableFrom(_nextSquare, _start);
                    toNext();
                } else if (_startingSquares.hasNext()
                        && !_pieceMoves.hasNext()
                        && !_spearThrows.hasNext()) {
                    _start = _startingSquares.next();
                    _pieceMoves = reachableFrom(_start, null);
                    toNext();
                }
            }
        }
        /** Color of side whose moves we are iterating. */
        private Piece _fromPiece;
        /** List of all SQUARES that are of _FROMPIECE. */
        private List<Square> _fromPieceList = new ArrayList<>();
        /** Current starting square. */
        private Square _start;
        /** Remaining starting squares to consider. */
        private Iterator<Square> _startingSquares;
        /** Current piece's new position. */
        private Square _nextSquare;
        /** Next Spear. */
        private Square nextSpear;
        /** Remaining moves from _start to consider. */
        private Iterator<Square> _pieceMoves;
        /** Remaining spear throws from _piece to consider. */
        private Iterator<Square> _spearThrows;
    }

    @Override
    public String toString() {
        String rtn = "  ";
        int count = 0;
        for (int i = SIZE - 1; i >= 0; i -= 1) {
            for (int j = 0; j < SIZE; j += 1) {
                if (count % 10 == 0 && count != 0) {
                    rtn = rtn + "\n" + "  ";
                }
                rtn = rtn + " " + get(j, i);
                count += 1;
            }
        }
        return rtn + "\n";
    }
    /** Find spears for BOARD.
     * @return spears. */
    public ArrayList<Square> findSpear() {
        ArrayList<Square> spear = new ArrayList<>();
        for (int x = 0; x < Board.SIZE; x++) {
            for (int y = 0; y < Board.SIZE; y++) {
                if (get(Square.sq(x, y)).equals(SPEAR)) {
                    spear.add(Square.sq(x, y));
                }
            }
        }
        _spears = spear;
        return _spears;
    }
    /** Find QUEENS for BOARD.
     * @return black Queens. */
    public ArrayList<Square> findBlackQueen() {
        ArrayList<Square> black = new ArrayList<>();
        for (int x = 0; x < Board.SIZE; x++) {
            for (int y = 0; y < Board.SIZE; y++) {
                if (get(Square.sq(x, y)).equals(SPEAR)) {
                    black.add(Square.sq(x, y));
                }
            }
        }
        _black = black;
        return _black;
    }
    /** Find QUEENS for BOARD.
     * @return white Queens. */
    public ArrayList<Square> findWhiteQueen() {
        ArrayList<Square> white = new ArrayList<>();
        for (int x = 0; x < Board.SIZE; x++) {
            for (int y = 0; y < Board.SIZE; y++) {
                if (get(Square.sq(x, y)).equals(SPEAR)) {
                    white.add(Square.sq(x, y));
                }
            }
        }
        _white = white;
        return _white;
    }

    /** An empty iterator for initialization. */
    private static final Iterator<Square> NO_SQUARES =
        Collections.emptyIterator();

    /** Piece whose turn it is (BLACK or WHITE). */
    private Piece _turn;
    /** Cached value of winner on this board, or EMPTY if it has not been
     *  computed. */
    private Piece _winner;
    /** Initial Position of WHITE PIECES. */
    private int[][] _whitePosn = {{0, 3}, {9, 3}, {3, 0}, {6, 0}};
    /** Initial Position of BLACK PIECES. */
    private int[][] _blackPosn = {{0, 6}, {3, 9}, {6, 9}, {9, 6}};
    /** Stores the content of the PIECE at the current SQUARE. */
    private HashMap<Square, Piece> _content = new HashMap<>();
    /** Keeps track of the last MOVE made. */
    private Stack<Move> _lastMove = new Stack<>();
    /** An integer value that keeps track of NUM_MOVES. */
    private int _numMoves = 0;
    /** The total number of EMPTY SQUARES for BLACK. */
    private int _emptyBlack;
    /** The total number of EMPTY SQUARES for WHITE. */
    private int _emptyWhite;
    /** Directions. */
    private static final int[][] DIR = {
            { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 },
            { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }
    };
    /** A dummy variable that handles edge cases. */
    private boolean bool;
    /** White Queens. */
    private ArrayList<Square> _white = new ArrayList<>();
    /** Black Queens. */
    private ArrayList<Square> _black = new ArrayList<>();
    /** Spears. */
    private ArrayList<Square> _spears = new ArrayList<>();
}
