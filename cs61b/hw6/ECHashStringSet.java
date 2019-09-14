// REPLACE THIS STUB WITH THE CORRECT SOLUTION.
// The current contents of this file are merely to allow things to compile
// out of the box.
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
/** A set of String values.
 *  @author
 */
class ECHashStringSet implements StringSet {
    private final double MIN_LOAD = 0.2;
    private final double MAX_LOAD = 5;
    int _size;
    private List<String> _asList = new ArrayList<>();
    LinkedList<String>[] _cache;
    public ECHashStringSet() {
        _size = 0;
        _cache = new LinkedList[(int)(MAX_LOAD)];
    }
    public double load(){
        return ((double) _size) / ((double)_cache.length);
    }
    public int size() {
        return _size;
    }

    private int position(String s){
        int hashCode = s.hashCode();
        if (hashCode < 0) {
            hashCode = (hashCode & 0x7fffffff) % _cache.length;
        }
        else {
            hashCode = hashCode % _cache.length;
        }
        return hashCode;
    }
    private void resize(){
        LinkedList<String>[] temp = _cache;
        _cache = new LinkedList[2 * temp.length];
        _size = 0;
        for (LinkedList<String> l : temp) {
            if (l != null) {
                for (String s : l) {
                    this.put(s);
                }
            }
        }

    }
    @Override
    public void put(String s) {
        if (s == null) {
            throw new NullPointerException();
        } else {
            if (load() > MAX_LOAD) {
                resize();
            } else {
                int position = position(s);
                if (_cache[position] == null) {
                    _cache[position] = new LinkedList<>();
                }
                _cache[position].add(s);
                _size += 1;
            }
        }
    }
    @Override
    public boolean contains(String s) {
        if (s != null) {
            int position = position(s);
            if (_cache[position] == null) {
                return false;
            } else {
                return _cache[position].contains(s);
            }
        }
        return false;
    }
    @Override
    public List<String> asList() {
        for (LinkedList<String> l : _cache) {
            if (l != null) {
                for (String a : l) {
                    _asList.add(a);
                }
            }
        }
        return _asList;
    }



}
