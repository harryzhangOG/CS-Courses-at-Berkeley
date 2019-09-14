/**
 * Created by joshuazeitsoff on 9/2/17.
 */

public class BuggyIntDListSolution extends IntDList {

    public BuggyIntDListSolution(Integer... values) {
        super(values);
    }

    /**
     *
     * @param d value to be inserted in the back
     */
    public void insertBack(int d) {
        _back = new DNode(_back, d, null);
        if (_front == null){
            _front = _back;
        }
        else {
            _back._prev._next = _back;
        }
    }

    public String getException() {
        //hint : this is what comes after the "java.lang" at the top of the stack trace
        return "NullPointerException";
    }

    public String getErrorFunction() {
        // hint: this is the first function name that you see
        // when reading the stack trace from top to bottom
        return "insertBack";
    }

    public int getErrorLineNumber() {
        // PUT ERROR LINE NUMBER HERE
        // hint: this is the number that comes after whichever function the
        // error is occurring in.
        return 17;
    }
}
