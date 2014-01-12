package main.graph.algorithms.huffman;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class Huffman {

    private LinkedList<BinaryTree> treeList;
    private HashMap<Character, String> codeMap;
    private HashMap<String, Character> decodeMap;

    public Huffman(Histogram h) {
        codeMap = new HashMap<Character, String>();
        decodeMap = new HashMap<String, Character>();
        treeList = new LinkedList<BinaryTree>();
        for (Entry<Character, Integer> es : h.getHistogram().entrySet()) {
            BinaryTree bt = new BinaryTree();
            bt.setRoot(new Node(es.getKey(), es.getValue()));
            treeList.add(bt);
        }
        Collections.sort(treeList, new ValueComparator());
    }

    public BinaryTree peekLowestOccurenceTree() {
        return treeList.poll();
    }

    public void prepareCodeMap(Node node, String code) {
        if (node.getC() != '\0') {
            codeMap.put(node.getC(), code);
            decodeMap.put(code, node.getC());
        }
        if (node.getRightChild() != null) {
            prepareCodeMap(node.getRightChild(), code.concat("1"));
        }
        if (node.getLeftChild() != null) {
            prepareCodeMap(node.getLeftChild(), code.concat("0"));
        }
    }

    public void computeCodeMaps(BinaryTree bt) {
        Node root = bt.getRoot();
        this.prepareCodeMap(root, "");
    }

    public BinaryTree getHuffmanTree() {
        while (treeList.size() > 1) {
            BinaryTree abt1 = this.peekLowestOccurenceTree();
            BinaryTree abt2 = this.peekLowestOccurenceTree();
            BinaryTree newBt = new BinaryTree();
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

    public LinkedList<BinaryTree> getTreeList() {
        return treeList;
    }

    public void setTreeList(LinkedList<BinaryTree> treeList) {
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

    class ValueComparator implements Comparator<BinaryTree> {
        public int compare(BinaryTree a, BinaryTree b) {
            if (a.getRoot().getOccurrences() > b.getRoot().getOccurrences()) {
                return 1;
            } else if (a.getRoot().getOccurrences() < b.getRoot().getOccurrences()) {
                return -1;
            }
            return 0;
        }
    }
}