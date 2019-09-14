import java.io.Reader;
import java.io.IOException;

/** Translating Reader: a stream that is a translation of an
 *  existing reader.
 *  @author HarryZhang
 */
public class TrReader extends Reader {
    /** A new TrReader that produces the stream of characters produced
     *  by STR, converting all characters that occur in FROM to the
     *  corresponding characters in TO.  That is, change occurrences of
     *  FROM.charAt(i) to TO.charAt(i), for all i, leaving other characters
     *  in STR unchanged.  FROM and TO must have the same length. */
    private Reader _reader;
    private String _from;
    private String _to;
    public TrReader(Reader str, String from, String to) {
        this._reader = str;
        this._from = from;
        this._to = to;
        // FILL IN
    }
    /** need to fill in abstract methods close and read
     */
    public void close() {
        try {
            _reader.close();
        } catch (IOException e) {
            return;
        }
    }
    public int read (char[] cbuf, int off, int len) throws IOException {
        int temp = _reader.read(cbuf, off, len);
        for (int i = off; i < off+len; i += 1) {
            int fromIndex = _from.indexOf(cbuf[i]);
            if (fromIndex != -1) {
                cbuf[i] = _to.charAt(fromIndex);
            }
        }
        return Math.min(len, temp);
    }

    // FILL IN
    // NOTE: Until you fill in the right methods, the compiler will
    //       reject this file, saying that you must declare TrReader
    //     abstract.  Don't do that; define the right methods instead!
}


