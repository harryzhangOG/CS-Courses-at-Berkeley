import static org.junit.Assert.*;
import org.junit.Test;

public class CompoundInterestTest {

    @Test
    public void testNumYears() {
        /** Sample assert statement for comparing integers.

        assertEquals(0, 0); */
        assertEquals(0, CompoundInterest.numYears(2018));
        assertEquals(3, CompoundInterest.numYears(2021));
        assertEquals(40, CompoundInterest.numYears(2058));
    }

    @Test
    public void testFutureValue() {
        double tolerance = 0.01;
        assertEquals(110, CompoundInterest.futureValue(100, 10, 2019), tolerance);
        assertEquals(121, CompoundInterest.futureValue(100, 10, 2020), tolerance);
    }

    @Test
    public void testFutureValueReal() {
        double tolerance = 0.01;
        assertEquals(11.802, CompoundInterest.futureValueReal(10, 12, 2020, 3), tolerance);
        assertEquals(1.067, CompoundInterest.futureValueReal(1, 10, 2019, 3), tolerance);
    }


    @Test
    public void testTotalSavings() {
        double tolerance = 0.01;
        assertEquals(16550, CompoundInterest.totalSavings(5000, 2020, 10), tolerance);
        assertEquals(23205, CompoundInterest.totalSavings(5000, 2021, 10), tolerance);
    }

    @Test
    public void testTotalSavingsReal() {
        double tolerance = 0.01;
        assertEquals(16550*0.97*0.97, CompoundInterest.totalSavingsReal(5000, 2020, 10, 3), tolerance);
        assertEquals(16550*0.94*0.94, CompoundInterest.totalSavingsReal(5000, 2020, 10, 6), tolerance);

    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        System.exit(ucb.junit.textui.runClasses(CompoundInterestTest.class));
    }
}
