package main.graph.algorithms.huffman;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Histogram {

    private TreeMap<Character, Integer> hist;
    private HashMap<Character, Integer> base;

    public Histogram() {
        base = new HashMap<Character, Integer>();
    }

    public void put(Character c) {
        if (base.containsKey(c)) {
            Integer i = base.get(c) + 1;
            base.put(c, i);
        } else base.put(c, 1);
    }
    public void sort(){
        hist = new TreeMap<Character, Integer>(new ValueComparator(base));
        hist.putAll(base);
    }

    public TreeMap<Character, Integer> getHistogram() {
        return hist;
    }

    public HashMap<Character, Integer> getBase() {
        return base;
    }

    public void printHistogram() {
        sort();
        for (Entry<Character, Integer> c : hist.entrySet()) {
            System.out.println("Znak: " + c.getKey() + "  ilość wystąpień: " + c.getValue());
        }
    }

    class ValueComparator implements Comparator<Character> {

        Map<Character, Integer> base;

        public ValueComparator(Map<Character, Integer> base) {
            this.base = base;
        }

        public int compare(Character a, Character b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            }
        }

    }
}
