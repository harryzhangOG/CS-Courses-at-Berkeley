package galaxy;
import java.util.Collections;
import java.util.Collection;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Formatter;
import java.util.Arrays;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import static galaxy.Place.pl;
/** The state of a Galaxies Puzzle.  Each cell, cell edge, and intersection of
 *  edges has coordinates (x, y). For cells, x and y are positive and odd.
 *  For intersections, x and y are even.  For horizontal edges, x is odd and
 *  y is even.  For vertical edges, x is even and y is odd.  On a board
 *  with w columns and h rows of cells, (0, 0) indicates the bottom left
 *  corner of the board, and (2w, 2h) indicates the upper right corner.
 *  If (x, y) are the coordinates of a cell, then (x-1, y) is its left edge,
 *  (x+1, y) its right edge, (x, y-1) its bottom edge, and (x, y+1) its
 *  top edge.  The four cells (x, y), (x+2, y), (x, y+2), and (x+2, y+2)
 *  meet at intersection (x+1, y+1).  Cells contain nonnegative integer
 *  values, or "marks". A cell containing 0 is said to be unmarked.
 *  @author Harry Zhang
 */
class Model {

    /** The default number of squares on a side of the board. */
    static final int DEFAULT_SIZE = 7;
    /** Instance variable that represents the size of the board. */
    private int _size;
    /** Instance variable that represents number of columns of the board. */
    private int _cols;
    /** Instance variable that represents number of rows of the board. */
    private int _rows;
    /** Instance variable that keeps track of the boundaries on the board. */
    private ArrayList<Place> _boundary = new ArrayList<Place>();
    /** Instance variable that keeps track of the centers on the board. */
    private ArrayList<Place> _center = new ArrayList<Place>();
    /** Instance variable that keeps track of cells on the board. */
    private int[][] _cells;
    /** Getter function of size of the board.
     * @return size of the board. */
    public int size() {
        return _size;
    }
    /** Getter function of columns of the board.
     * @return number of columns of the board. */
    public int col() {
        return _cols;
    }
    /** Getter function of rows of the board.
     * @return the number of rows of the board. */
    public int row() {
        return _rows;
    }
    /** Getter function of cells of the board,
     * which keeps track of the placed cells.
     * @return a 2d array that keeps track of all the cells. */
    public int[][] cell() {
        return _cells;
    }
    /** Getter function of boundary of the board,
     * which keeps track of the boundaries.
     * @return an ArrayList of Place that stores boundaries. */
    public ArrayList<Place> boundary() {
        return _boundary;
    }
    /** Getter function of centers of the board,
     * which keeps track of the centers.
     * @return an ArrayList of Place that stores centers. */
    public ArrayList<Place> center() {
        return _center;
    }

    /** Initializes an empty puzzle board of size DEFAULT_SIZE x DEFAULT_SIZE,
     *  with a boundary around the periphery. */
    Model() {
        init(DEFAULT_SIZE, DEFAULT_SIZE);
    }

    /** Initializes an empty puzzle board of size COLS x ROWS, with a boundary
     *  around the periphery. */
    Model(int cols, int rows) {
        init(cols, rows);
    }

    /** Initializes a copy of MODEL. */
    Model(Model model) {
        copy(model);
    }

    /** Copies MODEL into me. */
    void copy(Model model) {
        if (model == this) {
            return;
        }
        this._size = model.size();
        this._cols = model.col();
        this._rows = model.row();
        for (Place b : model.boundary()) {
            this._boundary.add(b);
        }
        for (Place c : model.center()) {
            this._center.add(c);
        }
        if (model.cell() == null) {
            this._cells = model.cell();
        }
        if (model.cell() != null) {
            this._cells = new int[model.cell().length][];
            for (int i = 0; i < model.cell().length; i += 1) {
                this._cells[i] = Arrays.copyOf(model.cell()[i],
                        model.cell()[i].length);
            }
        }
    }

    /** Sets the puzzle board size to COLS x ROWS, and clears it. */
    void init(int cols, int rows) {
        this._cols = cols;
        this._rows = rows;
        this._size = cols * rows;
        _boundary = new ArrayList<Place>();
        _center = new ArrayList<Place>();
        for (int i = 1; i < xlim() + 1; i = i + 2) {
            _boundary.add(pl(i, ylim() - 1));
            _boundary.add(pl(i, 0));
        }
        for (int j = 1; j < ylim() + 1; j = j + 2) {
            _boundary.add(pl(0, j));
            _boundary.add(pl(xlim() - 1, j));
        }
        this._cells = new int[cols][rows];
    }

    /** Clears the board (removes centers, boundaries that are not on the
     *  periphery, and marked cells) without resizing. */
    void clear() {
        init(cols(), rows());
    }

    /** Returns the number of columns of cells in the board. */
    int cols() {
        return xlim() / 2;
    }

    /** Returns the number of rows of cells in the board. */
    int rows() {
        return ylim() / 2;
    }

    /** Returns the number of vertical edges and cells in a row. */
    int xlim() {
        return this._cols * 2 + 1;

    }

    /** Returns the number of horizontal edges and cells in a column. */
    int ylim() {
        return this._rows * 2 + 1;
    }

    /** Returns true iff (X, Y) is a valid cell. */
    boolean isCell(int x, int y) {
        return 0 <= x && x < xlim() && 0 <= y && y < ylim()
            && x % 2 == 1 && y % 2 == 1;
    }

    /** Returns true iff P is a valid cell. */
    boolean isCell(Place p) {
        return isCell(p.x, p.y);
    }

    /** Returns true iff (X, Y) is a valid edge. */
    boolean isEdge(int x, int y) {
        return 0 <= x && x < xlim() && 0 <= y && y < ylim() && x % 2 != y % 2;
    }

    /** Returns true iff P is a valid edge. */
    boolean isEdge(Place p) {
        return isEdge(p.x, p.y);
    }

    /** Returns true iff (X, Y) is a vertical edge. */
    boolean isVert(int x, int y) {
        return isEdge(x, y) && x % 2 == 0;
    }

    /** Returns true iff P is a vertical edge. */
    boolean isVert(Place p) {
        return isVert(p.x, p.y);
    }

    /** Returns true iff (X, Y) is a horizontal edge. */
    boolean isHoriz(int x, int y) {
        return isEdge(x, y) && y % 2 == 0;
    }

    /** Returns true iff P is a horizontal edge. */
    boolean isHoriz(Place p) {
        return isHoriz(p.x, p.y);
    }

    /** Returns true iff (X, Y) is a valid intersection. */
    boolean isIntersection(int x, int y) {
        return x % 2 == 0 && y % 2 == 0
                && x >= 0 && y >= 0 && x < xlim() && y < ylim();
    }

    /** Returns true iff P is a valid intersection. */
    boolean isIntersection(Place p) {
        return isIntersection(p.x, p.y);
    }

    /** Returns true iff (X, Y) is a center. */
    boolean isCenter(int x, int y) {
        return (isCell(x, y) || (isIntersection(x, y) && isCell(x + 1, y + 1)
                && isCell(x - 1, y + 1)
                && isCell(x - 1, y - 1) && isCell(x + 1, y - 1))
                || (isHoriz(x, y) && isCell(x, y + 1) && isCell(x, y - 1))
                || (isVert(x, y) && isCell(x + 1, y) && isCell(x - 1, y)))
                && this.center().contains(pl(x, y));
    }

    /** Returns true iff P is a center. */
    boolean isCenter(Place p) {
        return isCenter(p.x, p.y);
    }

    /** Returns true iff (X, Y) is a boundary. */
    boolean isBoundary(int x, int y) {
        /**return Arrays.asList(boundary).contains(pl(x, y));*/
        return this.boundary().contains(pl(x, y));
    }

    /** Returns true iff P is a boundary. */
    boolean isBoundary(Place p) {
        return isBoundary(p.x, p.y);
    }

    /** Returns true iff the puzzle board is solved, given the centers and
     *  boundaries that are currently on the board. */
    boolean solved() {
        int total;
        total = 0;
        for (Place c : centers()) {
            HashSet<Place> r = findGalaxy(c);
            if (r == null) {
                return false;
            } else {
                total += r.size();
            }
        }
        return total == rows() * cols();
    }

    /** Finds cells reachable from CELL and adds them to REGION.  Specifically,
     *  it finds cells that are reachable using only vertical and horizontal
     *  moves starting from CELL that do not cross any boundaries and
     *  do not touch any cells that were initially in REGION. Requires
     *  that CELL is a valid cell. */
    private void accreteRegion(Place cell, HashSet<Place> region) {
        assert isCell(cell);
        if (region.contains(cell)) {
            return;
        }
        region.add(cell);
        for (int i = 0; i < 4; i += 1) {
            int dx = (i % 2) * (2 * (i / 2) - 1),
                dy = ((i + 1) % 2) * (2 * (i / 2) - 1);
            if (isCell(cell.x + 2 * dx, cell.y + 2 * dy)
                    && !isBoundary(cell.x + dx, cell.y + dy)) {
                accreteRegion(cell.move(2 * dx, 2 * dy), region);
            }
        }
    }

    /** Returns true iff REGION is a correctly formed galaxy. A correctly formed
     *  galaxy has the following characteristics:
     *      - is symmetric about CENTER,
     *      - contains no interior boundaries, and
     *      - contains no other centers.
     * Assumes that REGION is connected. */
    private boolean isGalaxy(Place center, HashSet<Place> region) {
        for (Place cell : region) {
            if (!region.contains(opposing(center, cell))) {
                return false;
            }
            for (int i = 0; i < 4; i += 1) {
                int dx = (i % 2) * (2 * (i / 2) - 1),
                    dy = ((i + 1) % 2) * (2 * (i / 2) - 1);
                Place boundary = cell.move(dx, dy),
                    nextCell = cell.move(2 * dx, 2 * dy);

                if (!isCell(nextCell) && region.contains(nextCell)) {
                    return false;
                }
                if (isCenter(boundary) && !boundary.equals(center)) {
                    return false;
                }
                if (region.contains(nextCell) && isBoundary(boundary)) {
                    return false;
                }
                if (region.contains(nextCell) && isCenter(nextCell)
                        && !nextCell.equals(center)) {
                    return false;
                }
                if (boundary.equals(center) && !region.contains(nextCell)) {
                    return false;
                }
            }
            for (int i = 0; i < 4; i += 1) {
                int dx = 2 * (i / 2) - 1,
                    dy = 2 * (i % 2) - 1;
                Place intersection = cell.move(dx, dy);
                if (isCenter(intersection) && !intersection.equals(center)) {
                    return false;
                }
            }
        }
        return true;
    }


    /** Returns the galaxy containing CENTER that has the following
     *  characteristics:
     *      - encloses CENTER completely,
     *      - is symmetric about CENTER,
     *      - is connected,
     *      - contains no stray boundary edges, and
     *      - contains no other centers aside from CENTER.
     *  Otherwise, returns null. Requires that CENTER is not on the
     *  periphery. */
    HashSet<Place> findGalaxy(Place center) {
        HashSet<Place> galaxy = new HashSet<>();
        if (isHoriz(center)) {
            accreteRegion(center.move(0, 1), galaxy);
            accreteRegion(center.move(0, -1), galaxy);
        }
        if (isVert(center)) {
            accreteRegion(center.move(1, 0), galaxy);
            accreteRegion(center.move(-1, 0), galaxy);
        }
        if (isIntersection(center)) {
            for (int i = 0; i < 4; i += 1) {
                int dx = 2 * (i / 2) - 1,
                        dy = 2 * (i % 2) - 1;
                accreteRegion(center.move(dx, dy), galaxy);
            }
        }
        if (isCell(center)) {
            accreteRegion(center, galaxy);
        }
        if (isGalaxy(center, galaxy)) {
            return galaxy;
        } else {
            return null;
        }
    }


    /** Returns the largest, unmarked region around CENTER with the
     *  following characteristics:
     *      - contains all cells touching CENTER,
     *      - consists only of unmarked cells,
     *      - is symmetric about CENTER, and
     *      - is contiguous.
     *  The method ignores boundaries and other centers on the current board.
     *  If there is no such region, returns the empty set. */
    Set<Place> maxUnmarkedRegion(Place center) {
        HashSet<Place> region = new HashSet<>();
        region.addAll(unmarkedContaining(center));
        markAll(region, 1);
        List<Place> temp = new ArrayList<>(region);
        region.addAll(unmarkedSymAdjacent(center, temp));
        List<Place> symTemp = new ArrayList<Place>(region);
        while (!temp.equals(symTemp)) {
            temp = symTemp;
            region.addAll(unmarkedSymAdjacent(center, temp));
            symTemp = new ArrayList<>(region);
        }
        markAll(region, 0);
        return region;
    }

    /** Marks all properly formed galaxies with value V. Unmarks all cells that
     *  are not contained in any of these galaxies. Requires that V is greater
     *  than or equal to 0. */
    void markGalaxies(int v) {
        assert v >= 0;
        markAll(0);
        for (Place c : centers()) {
            HashSet<Place> region = findGalaxy(c);
            if (region != null) {
                markAll(region, v);
            }
        }
    }

    /** Toggles the presence of a boundary at the edge (X, Y). That is, negates
     *  the value of isBoundary(X, Y) (from true to false or vice-versa).
     *  Requires that (X, Y) is an edge. */
    void toggleBoundary(int x, int y) {
        /**if (Arrays.asList(boundary).contains(pl(x, y))){
            Arrays.asList(boundary).remove(pl(x,y));
        }*/
        if (this.boundary().contains(pl(x, y))) {
            this.boundary().remove(pl(x, y));
        } else {
            this.boundary().add(pl(x, y));
        }
    }

    /** Places a center at (X, Y). Requires that X and Y are within bounds of
     *  the board. */
    void placeCenter(int x, int y) {
        placeCenter(pl(x, y));
    }

    /** Places center at P. */
    void placeCenter(Place p) {
        /**center[p.x][p.y] = p;*/
        this.center().add(p);
    }

    /** Returns the current mark on cell (X, Y), or -1 if (X, Y) is not a valid
     *  cell address. */
    int mark(int x, int y) {
        if (!isCell(x, y)) {
            return -1;
        }
        return this.cell()[(x - 1) / 2][(y - 1) / 2];
    }

    /** Returns the current mark on cell P, or -1 if P is not a valid cell
     *  address. */
    int mark(Place p) {
        return mark(p.x, p.y);
    }

    /** Marks the cell at (X, Y) with value V. Requires that V must be greater
     *  than or equal to 0, and that (X, Y) is a valid cell address. */
    void mark(int x, int y, int v) {
        if (!isCell(x, y)) {
            throw new IllegalArgumentException("bad cell coordinates");
        }
        if (v < 0) {
            throw new IllegalArgumentException("bad mark value");
        } else {
            this.cell()[(x - 1) / 2][(y - 1) / 2] = v;
        }
    }

    /** Marks the cell at P with value V. Requires that V must be greater
     *  than or equal to 0, and that P is a valid cell address. */
    void mark(Place p, int v) {
        mark(p.x, p.y, v);
    }

    /** Sets the marks of all cells in CELLS to V. Requires that V must be
     *  greater than or equal to 0. */
    void markAll(Collection<Place> cells, int v) {
        assert v >= 0;
        for (Place cell : cells) {
            mark(cell, v);
        }
    }

    /** Sets the marks of all cells to V. Requires that V must be greater than
     *  or equal to 0. */
    void markAll(int v) {
        assert v >= 0;
        for (int i = 0; i < this.cell().length; i += 1) {
            for (int j = 0; j < this.cell()[i].length; j += 1) {
                this.cell()[i][j] = v;
            }
        }
    }

    /** Returns the position of the cell that is opposite P using P0 as the
     *  center, or null if that is not a valid cell address. */
    Place opposing(Place p0, Place p) {

        int dx = p.x - p0.x;
        int dy = p.y - p0.y;
        int rx, ry;
        rx = p0.x - dx;
        ry = p0.y - dy;
        if (isCell(rx, ry)) {
            return pl(rx, ry);
        }
        return null;

    }

    /** Returns a list of all cells "containing" PLACE if all of the cells are
     *  unmarked. A cell, c, "contains" PLACE if
     *      - c is PLACE itself,
     *      - PLACE is a corner of c, or
     *      - PLACE is an edge of c.
     *  Otherwise, returns an empty list. */
    List<Place> unmarkedContaining(Place place) {
        if (isCell(place)) {
            if (mark(place) == 0) {
                return asList(place);
            }
        } else if (isVert(place)) {
            if (mark(place.move(-1, 0)) == 0
                    && mark(place.move(1, 0)) == 0) {
                return asList(place.move(-1, 0), place.move(1, 0));
            }
        } else if (isHoriz(place)) {
            if (mark(place.move(0, -1)) == 0
                    && mark(place.move(0, 1)) == 0) {
                return asList(place.move(0, -1), place.move(0, 1));
            }
        } else {
            for (int i = 0; i < 4; i += 1) {
                int dx = 2 * (i / 2) - 1,
                        dy = 2 * (i % 2) - 1;
                if (mark(place.move(dx, dy)) != 0) {
                    return Collections.emptyList();
                }
            }
            return asList(place.move(1, 1), place.move(-1, -1),
                    place.move(1, -1), place.move(-1, 1));
        }
        return Collections.emptyList();
    }

    /** Returns a list of all cells, c, such that:
     *      - c is unmarked,
     *      - The opposite cell from c relative to CENTER exists and
     *        is unmarked, and
     *      - c is vertically or horizontally adjacent to a cell in REGION.
     *  CENTER and all cells in REGION must be valid cell positions.
     *  Each cell appears at most once in the resulting list. */
    List<Place> unmarkedSymAdjacent(Place center, List<Place> region) {
        ArrayList<Place> result = new ArrayList<>();
        for (Place r : region) {
            assert isCell(r);
            for (int i = 0; i < 4; i += 1) {
                int dx = (i % 2) * (2 * (i / 2) - 1),
                        dy = ((i + 1) % 2) * (2 * (i / 2) - 1);
                if (!isBoundary(r.move(dx, dy))) {
                    Place p = r.move(2 * dx, 2 * dy);
                    if (mark(p) == 0 && isCell(p) && !result.contains(p)) {
                        Place opp = opposing(center, p);
                        if (opp != null && mark(opp) == 0) {
                            result.add(p);
                            result.add(opp);
                        }
                    }
                }
            }
        }


        return result;
    }

    /** Returns an unmodifiable view of the list of all centers. */
    List<Place> centers() {
        return Collections.unmodifiableList(this.center());
    }

    @Override
    public String toString() {
        Formatter out = new Formatter();
        int w = xlim(), h = ylim();
        for (int y = h - 1; y >= 0; y -= 1) {
            for (int x = 0; x < w; x += 1) {
                boolean cent = isCenter(x, y);
                boolean bound = isBoundary(x, y);
                if (isIntersection(x, y)) {
                    out.format(cent ? "o" : " ");
                } else if (isCell(x, y)) {
                    if (cent) {
                        out.format(mark(x, y) > 0 ? "O" : "o");
                    } else {
                        out.format(mark(x, y) > 0 ? "*" : " ");
                    }
                } else if (y % 2 == 0) {
                    if (cent) {
                        out.format(bound ? "O" : "o");
                    } else {
                        out.format(bound ? "=" : "-");
                    }
                } else if (cent) {
                    out.format(bound ? "O" : "o");
                } else {
                    out.format(bound ? "I" : "|");
                }
            }
            out.format("%n");
        }
        return out.toString();
    }

}
