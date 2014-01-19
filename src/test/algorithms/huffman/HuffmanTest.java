package test.algorithms.huffman;

import main.graph.algorithms.huffman.FileManager;
import main.graph.algorithms.huffman.Histogram;
import main.graph.algorithms.huffman.Huffman;
import main.graph.algorithms.huffman.HuffmanTree;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * User: filip Date: 12.01.14 Time: 19:21
 */
public class HuffmanTest {

    @Test
    public void histogramTest() throws IOException {
        Histogram h = new Histogram();
        FileManager.read(h, "big.txt");
        h.sort();
        TreeMap<Character, Integer> tm = h.getHistogram();
        System.out.println("Tree map size: " + tm.size());
        System.out.println("Base size: " + h.getBase().size());
        h.printHistogram();
    }

    @Test
    public void encodeTest() throws IOException {
        Histogram h = new Histogram();
        FileManager.read(h, "big.txt");
        h.sort();
        Huffman huffman = new Huffman(h);

        HuffmanTree huffmanTree = huffman.getHuffmanTree();
        huffman.computeCodeMaps(huffmanTree);
        huffmanTree.printTree();
        HashMap<Character, String> codeMap = huffman.getCodeMap();
        FileManager.encodeBits(codeMap, "big.txt", "encoded.txt");
    }

    @Test
    public void decodeTest() throws IOException {
        String file = "big.txt";
        Histogram h = new Histogram();
        FileManager.read(h, file);
        h.sort();
        Huffman huffman = new Huffman(h);

        HuffmanTree huffmanTree = huffman.getHuffmanTree();
        huffman.computeCodeMaps(huffmanTree);
        HashMap<Character, String> codeMap = huffman.getCodeMap();
        HashMap<String, Character> decodeMap = huffman.getDecodeMap();

        int offset = FileManager.encodeBits(codeMap, file, "encoded.txt");
        FileManager.decodeBits(decodeMap, "encoded.txt", "decoded.txt", offset);
    }

}
