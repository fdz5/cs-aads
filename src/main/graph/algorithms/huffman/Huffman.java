package main.graph.algorithms.huffman;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class Huffman {

    private LinkedList<HuffmanTree> treeList;
    private HashMap<Character, String> codeMap;
    private HashMap<String, Character> decodeMap;

    public Huffman(Histogram h) {
        codeMap = new HashMap<>();
        decodeMap = new HashMap<>();
        treeList = new LinkedList<>();
        for (Entry<Character, Integer> es : h.getHistogram().entrySet()) {
            HuffmanTree bt = new HuffmanTree();
            bt.setRoot(new Node(es.getKey(), es.getValue()));
            treeList.add(bt);
        }
        Collections.sort(treeList, new ValueComparator());
    }

    public HuffmanTree peekLowestOccurenceTree() {
        return treeList.poll();
    }

    public void computeCodeMaps(HuffmanTree bt) {
        this.prepareCodeMap(bt.getRoot(), "");
    }

    public void prepareCodeMap(Node node, String code) {
        if (node.getC() != '\0') {
            codeMap.put(node.getC(), code);
            decodeMap.put(code, node.getC());
        }
        if (node.getRightChild() != null) {
            prepareCodeMap(node.getRightChild(), code + "1");
        }
        if (node.getLeftChild() != null) {
            prepareCodeMap(node.getLeftChild(), code + "0");
        }
    }

    public HuffmanTree getHuffmanTree() {
        while (treeList.size() > 1) {
            HuffmanTree abt1 = this.peekLowestOccurenceTree();
            HuffmanTree abt2 = this.peekLowestOccurenceTree();
            HuffmanTree newBt = new HuffmanTree();
            Node newNode = new Node();
            newNode.setOccurrences(abt1.getRoot().getOccurrences() + abt2.getRoot().getOccurrences());
            newNode.setRightChild(abt1.getRoot());
            newNode.setLeftChild(abt2.getRoot());
            newBt.setRoot(newNode);
            treeList.add(newBt);
            Collections.sort(treeList, new ValueComparator());
        }
        return treeList.getFirst();
    }

    public LinkedList<HuffmanTree> getTreeList() {
        return treeList;
    }

    public void setTreeList(LinkedList<HuffmanTree> treeList) {
        this.treeList = treeList;
    }

    public HashMap<Character, String> getCodeMap() {
        return codeMap;
    }

    public void setCodeMap(HashMap<Character, String> codeMap) {
        this.codeMap = codeMap;
    }

    public HashMap<String, Character> getDecodeMap() {
        return decodeMap;
    }

    public void setDecodeMap(HashMap<String, Character> decodeMap) {
        this.decodeMap = decodeMap;
    }

    class ValueComparator implements Comparator<HuffmanTree> {
        public int compare(HuffmanTree a, HuffmanTree b) {
            if (a.getRoot().getOccurrences() > b.getRoot().getOccurrences()) {
                return 1;
            } else if (a.getRoot().getOccurrences() < b.getRoot().getOccurrences()) {
                return -1;
            }
            return 0;
        }
    }
}