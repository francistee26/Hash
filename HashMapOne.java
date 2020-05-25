import java.util.Arrays;

public class HashMapOne {
    public class Entry {
        private int key;
        private String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }

    }

    private Entry[] map;
    private int size;

    public HashMapOne(int capacity) {
        this.map = new Entry[capacity];
        this.size = 0;
    }

    public void put(int key, String value) {
        var entry = getEntry(key);
        if (entry != null) {
            entry.value = value;
            return;
        }
        if (isFull())
            throw new IllegalStateException("Map is Full");
        map[getIndex(key)] = new Entry(key, value);
        size++;
    }

    public String get(int key) {
        var entry = getEntry(key);
        return entry != null ? entry.value : null;

    }

    public void remove(int key) {
        var index = getIndex(key);
        if (index == -1 || map[index] == null)
            return;

        map[index] = null;
        size--;
    }

    public Entry getEntry(int key) {
        var index = getIndex(key);
        return index >= 0 ? map[index] : null;
    }

    public int getIndex(int key) {
        int steps = 0;
        while (steps < map.length) {
            int index = index(key, steps++);
            var entry = map[index];
            if (entry == null || entry.key == key)
                return index;
        }
        return -1;
    }

    public int size() {
        return size;
    }

    private int hash(int key) {
        return key % map.length;
    }

    private boolean isFull() {
        return size == map.length;
    }

    private int index(int key, int i) {
        return (hash(key) + i) % map.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(map);
    }

}