import net.sf.saxon.expr.accum.Accumulator;

/** Functions to increment and sum the elements of a WeirdList. */
class WeirdListClient {

    /** Return the result of adding N to each element of L. */
    static WeirdList add(WeirdList L, int n) {
        return L.map(x -> x + n); // lambda expression lolol
    }
    public class SumFunc implements IntUnaryFunction {
        private int _sum;
        public SumFunc() {
            _sum = 0;
        }
        public int get_sum() {
            return _sum;
        }

        @Override
        public int apply(int x) {
            _sum += x;
            return _sum;
        }
    }
    /** Return the sum of the elements in L. */
    static int sum(WeirdList L) {
        WeirdListClient outer = new WeirdListClient();
        SumFunc sumF = outer.new SumFunc();
        L.map(sumF);
        return sumF.get_sum();

    }

    /* As with WeirdList, you'll need to add an additional class or
     * perhaps more for WeirdListClient to work. Again, you may put
     * those classes either inside WeirdListClient as private static
     * classes, or in their own separate files.

     * You are still forbidden to use any of the following:
     *       if, switch, while, for, do, try, or the ?: operator.
     */
}
