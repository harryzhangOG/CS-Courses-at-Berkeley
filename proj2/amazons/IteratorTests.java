package amazons;
import org.junit.Test;
import static org.junit.Assert.*;
import ucb.junit.textui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/** Junit tests for our Board iterators.
 *  @author
 */
public class IteratorTests {

    /**
     * Run the JUnit tests in this package.
     */
    public static void main(String[] ignored) {
        textui.runClasses(IteratorTests.class);
    }

    /**
     * Tests reachableFromIterator to make sure it returns all reachable
     * Squares. This method may need to be changed based on
     * your implementation.
     */
    @Test
    public void testReachableFrom() {
        Board b = new Board();
        buildBoard(b, REACHABLEFROMTESTBOARD);
        int numSquares = 0;
        Set<Square> squares = new HashSet<>();
        Iterator<Square> reachableFrom = b.reachableFrom(Square.sq(5, 4), null);
        while (reachableFrom.hasNext()) {
            Square s = reachableFrom.next();
            assertTrue(REACHABLEFROMTESTSQUARESSET.contains(s));
            numSquares += 1;
            squares.add(s);
        }
        assertEquals(REACHABLEFROMTESTSQUARESSET.size(), numSquares);
        assertEquals(REACHABLEFROMTESTSQUARESSET.size(), squares.size());
    }

    /**
     * Tests legalMovesIterator to make sure it returns all legal Moves.
     * This method needs to be finished and may need to be changed
     * based on your implementation.
     */
    @Test
    public void testLegalMoves() {
        Board b = new Board();
        buildBoard(b, LEGALMOVESTESTBOARD);
        int numMoves = 0;
        Set<Move> moves = new HashSet<>();
        Iterator<Move> legalMoves = b.legalMoves(Piece.WHITE);
        while (legalMoves.hasNext()) {
            Move m = legalMoves.next();
            System.out.println(m);
            assertTrue(LEGALMOVESTESTMOVES.contains(m));
            numMoves += 1;
            moves.add(m);
        }
        assertEquals(LEGALMOVESTESTMOVES.size(), numMoves);
        assertEquals(LEGALMOVESTESTMOVES.size(), moves.size());
    }


    private void buildBoard(Board b, Piece[][] target) {
        for (int col = 0; col < Board.SIZE; col++) {
            for (int row = Board.SIZE - 1; row >= 0; row--) {
                Piece piece = target[Board.SIZE - row - 1][col];
                b.put(piece, Square.sq(col, row));
            }
        }
        System.out.println(b);
    }

    static final Piece E = Piece.EMPTY;

    static final Piece W = Piece.WHITE;

    static final Piece B = Piece.BLACK;

    static final Piece S = Piece.SPEAR;

    static final Piece[][] REACHABLEFROMTESTBOARD =
        {
        {E, E, E, E, E, E, E, E, E, E},
        {E, E, E, E, E, E, E, E, W, W},
        {E, E, E, E, E, E, E, S, E, S},
        {E, E, E, S, S, S, S, E, E, S},
        {E, E, E, S, E, E, E, E, B, E},
        {E, E, E, S, E, W, E, E, B, E},
        {E, E, E, S, S, S, B, W, B, E},
        {E, E, E, E, E, E, E, E, E, E},
        {E, E, E, E, E, E, E, E, E, E},
        {E, E, E, E, E, E, E, E, E, E},
        };

    static final Set<Square> REACHABLEFROMTESTSQUARESSET =
            new HashSet<>(Arrays.asList(
                    Square.sq(5, 5),
                    Square.sq(4, 5),
                    Square.sq(4, 4),
                    Square.sq(6, 4),
                    Square.sq(7, 4),
                    Square.sq(6, 5),
                    Square.sq(7, 6),
                    Square.sq(8, 7)));
    static final Piece[][] LEGALMOVESTESTBOARD =
        {
        {S, S, S, S, S, E, E, S, E, E},
        {S, S, S, S, S, E, S, E, S, W},
        {E, S, S, S, S, E, E, S, E, S},
        {E, E, E, S, S, S, S, E, E, S},
        {E, E, E, S, E, E, E, E, B, E},
        {E, E, E, S, E, W, E, E, B, E},
        {E, E, E, S, S, S, B, B, B, E},
        {E, E, E, E, E, E, E, E, E, E},
        {E, E, E, E, E, E, E, E, E, E},
        {E, E, E, E, E, E, E, E, E, E},
        };

    static final Set<Move> LEGALMOVESTESTMOVES =
            new HashSet<>(Arrays.asList(
                    Move.mv(Square.sq(5, 4), Square.sq(5, 5), Square.sq(4, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(5, 5), Square.sq(4, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(5, 5), Square.sq(6, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(5, 5), Square.sq(6, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(5, 5), Square.sq(7, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(5, 5), Square.sq(5, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(6, 5), Square.sq(5, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(6, 5), Square.sq(4, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(6, 5), Square.sq(7, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(6, 5), Square.sq(7, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(6, 5), Square.sq(5, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(6, 5), Square.sq(7, 6)),
                    Move.mv(Square.sq(5, 4), Square.sq(6, 5), Square.sq(8, 7)),
                    Move.mv(Square.sq(5, 4), Square.sq(6, 5), Square.sq(6, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(7, 6), Square.sq(6, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(7, 6), Square.sq(5, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(7, 6), Square.sq(8, 7)),
                    Move.mv(Square.sq(5, 4), Square.sq(7, 6), Square.sq(8, 6)),
                    Move.mv(Square.sq(5, 4), Square.sq(7, 6), Square.sq(7, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(7, 6), Square.sq(7, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(7, 6), Square.sq(6, 7)),
                    Move.mv(Square.sq(5, 4), Square.sq(7, 6), Square.sq(5, 8)),
                    Move.mv(Square.sq(5, 4), Square.sq(8, 7), Square.sq(7, 6)),
                    Move.mv(Square.sq(5, 4), Square.sq(8, 7), Square.sq(6, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(8, 7), Square.sq(5, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(8, 7), Square.sq(8, 6)),
                    Move.mv(Square.sq(5, 4), Square.sq(8, 7), Square.sq(7, 8)),
                    Move.mv(Square.sq(5, 4), Square.sq(8, 7), Square.sq(6, 9)),
                    Move.mv(Square.sq(5, 4), Square.sq(6, 4), Square.sq(6, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(6, 4), Square.sq(7, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(6, 4), Square.sq(8, 6)),
                    Move.mv(Square.sq(5, 4), Square.sq(6, 4), Square.sq(7, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(6, 4), Square.sq(5, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(6, 4), Square.sq(4, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(6, 4), Square.sq(5, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(7, 4), Square.sq(7, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(7, 4), Square.sq(7, 6)),
                    Move.mv(Square.sq(5, 4), Square.sq(7, 4), Square.sq(6, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(7, 4), Square.sq(6, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(7, 4), Square.sq(5, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(7, 4), Square.sq(4, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(4, 4), Square.sq(4, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(4, 4), Square.sq(5, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(4, 4), Square.sq(5, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(4, 4), Square.sq(6, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(4, 4), Square.sq(7, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(4, 5), Square.sq(5, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(4, 5), Square.sq(6, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(4, 5), Square.sq(7, 5)),
                    Move.mv(Square.sq(5, 4), Square.sq(4, 5), Square.sq(5, 4)),
                    Move.mv(Square.sq(5, 4), Square.sq(4, 5), Square.sq(4, 4)),
                    Move.mv(Square.sq(9, 8), Square.sq(9, 9), Square.sq(9, 8)),
                    Move.mv(Square.sq(9, 8), Square.sq(9, 9), Square.sq(8, 9)),
                    Move.mv(Square.sq(9, 8), Square.sq(8, 9), Square.sq(9, 8)),
                    Move.mv(Square.sq(9, 8), Square.sq(8, 9), Square.sq(9, 9)),
                    Move.mv(Square.sq(9, 8), Square.sq(8, 9), Square.sq(7, 8)),
                    Move.mv(Square.sq(9, 8), Square.sq(8, 9), Square.sq(6, 7)),
                    Move.mv(Square.sq(9, 8), Square.sq(8, 7), Square.sq(9, 8)),
                    Move.mv(Square.sq(9, 8), Square.sq(8, 7), Square.sq(7, 6)),
                    Move.mv(Square.sq(9, 8), Square.sq(8, 7), Square.sq(6, 5)),
                    Move.mv(Square.sq(9, 8), Square.sq(8, 7), Square.sq(8, 6)),
                    Move.mv(Square.sq(9, 8), Square.sq(8, 7), Square.sq(7, 8)),
                    Move.mv(Square.sq(9, 8), Square.sq(8, 7), Square.sq(6, 9)),
                    Move.mv(Square.sq(9, 8), Square.sq(8, 7), Square.sq(8, 6)),
                    Move.mv(Square.sq(9, 8), Square.sq(7, 6), Square.sq(9, 8)),
                    Move.mv(Square.sq(9, 8), Square.sq(7, 6), Square.sq(8, 7)),
                    Move.mv(Square.sq(9, 8), Square.sq(7, 6), Square.sq(6, 5)),
                    Move.mv(Square.sq(9, 8), Square.sq(7, 6), Square.sq(8, 6)),
                    Move.mv(Square.sq(9, 8), Square.sq(7, 6), Square.sq(7, 5)),
                    Move.mv(Square.sq(9, 8), Square.sq(7, 6), Square.sq(7, 4)),
                    Move.mv(Square.sq(9, 8), Square.sq(7, 6), Square.sq(6, 7)),
                    Move.mv(Square.sq(9, 8), Square.sq(7, 6), Square.sq(5, 8)),
                    Move.mv(Square.sq(9, 8), Square.sq(6, 5), Square.sq(9, 8)),
                    Move.mv(Square.sq(9, 8), Square.sq(6, 5), Square.sq(8, 7)),
                    Move.mv(Square.sq(9, 8), Square.sq(6, 5), Square.sq(7, 6)),
                    Move.mv(Square.sq(9, 8), Square.sq(6, 5), Square.sq(5, 5)),
                    Move.mv(Square.sq(9, 8), Square.sq(6, 5), Square.sq(4, 5)),
                    Move.mv(Square.sq(9, 8), Square.sq(6, 5), Square.sq(7, 5)),
                    Move.mv(Square.sq(9, 8), Square.sq(6, 5), Square.sq(7, 4)),
                    Move.mv(Square.sq(9, 8), Square.sq(6, 5), Square.sq(6, 4))
            ));
}
