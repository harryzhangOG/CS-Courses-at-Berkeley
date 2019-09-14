/** Some extra tests for Enigma.
 *  @author
 */
public class MoreEnigmaTests {
	
    @Test
    public void testInvertChar() {
        Permutation p = new Permutation("(PNH) (ABDFIKLZYXW) (JC)", new CharacterRange('A', 'Z'));
        assertEquals(p.invert('B'), 'A');
        assertEquals(p.invert('G'), 'G');
        /* TODO: Add additional assert statements here! */
    }

    @Test
    public void testPermuteChar() {
        //Permutation p = new Permutation(...);
        //assertEquals(...) ;
        /* TODO: Add additional assert statements here! */

    }

    @Test
    public void testDerangement() {
        //Permutation p = new Permutation(...);
        //assertEquals(...) ;
        /* TODO: Add additional assert statements here! */
    }

    @Test
    public void testDoubleStep() {
        Alphabet ac = new CharacterRange('A', 'D');
        Rotor one = new Reflector("R1", new Permutation("(AC) (BD)", ac));
        Rotor two = new MovingRotor("R2", new Permutation("(ABCD)", ac), "C");
        Rotor three = new MovingRotor("R3", new Permutation("(ABCD)", ac), "C");
        Rotor four = new MovingRotor("R4", new Permutation("(ABCD)", ac), "C");
        String setting = "AAA";
        Rotor[] machineRotors = {one, two, three, four};
        String[] rotors = {"R1", "R2", "R3", "R4"};
        Machine mach = new Machine(ac, 4, 3, new ArrayList<>(Arrays.asList(machineRotors)));
        mach.insertRotors(rotors);
        mach.setRotors(setting);

        assertEquals("AAAA", getSetting(ac, machineRotors));
        mach.convert('a');
        assertEquals("AAAB", getSetting(ac, machineRotors));
        /* TODO: Add additional assert statements here! */
    }

    /** Helper method to get the String representation of the current Rotor settings */
    private String getSetting(Alphabet alph, Rotor[] machineRotors) {
        String currSetting = "";
        for (Rotor r : machineRotors) {
            currSetting += alph.toChar(r.setting());
        }
        return currSetting;
    }
}