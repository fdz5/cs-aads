package main.graph.algorithms.huffman;

import java.io.Serializable;

public class HuffmanTree implements Serializable {

	private static final long serialVersionUID = 1L;
	private Node root;

    public void printTree() {
        this.printTree(0, root);
    }

    private void printTree(int level, Node n) {
        System.out.println("Level : " + level + ", Character : " + n.getC() + ", Occurences :" + n.getOccurrences());
        if (n.getRightChild() != null) {
            printTree(level + 1, n.getRightChild());
        }
        if (n.getLeftChild() != null) {
            printTree(level + 1, n.getLeftChild());
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}

