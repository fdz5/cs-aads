package test.algorithms.huffman;

import main.graph.algorithms.huffman.HuffmanTree;
import main.graph.algorithms.huffman.FileManager;
import main.graph.algorithms.huffman.Histogram;
import main.graph.algorithms.huffman.Huffman;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: filip
 * Date: 12.01.14
 * Time: 19:21
 */
public class HuffmanTest {

    public static void main(String[] args) throws IOException {

        Histogram h = new Histogram();
        FileManager.read(h, "big.txt");
        h.sort();
        Huffman huffman = new Huffman(h);

//        TreeMap<Character, Integer> tm = h.getHistogram();
//        System.out.println("Tree map size: " + tm.size());
//        System.out.println("Base size: " + h.getBase().size());
//        h.printHistogram();

        HuffmanTree huffmanTree = huffman.getHuffmanTree();
//        huffmanTree.printTree();
        huffman.computeCodeMaps(huffmanTree);

        HashMap<Character, String> codeMap = huffman.getCodeMap();
        HashMap<String, Character> decodeMap = huffman.getDecodeMap();

        System.out.println("Encode map");
        for (Map.Entry<Character, String> entry : codeMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println("Decode map");
        for (Map.Entry<String, Character> entry : decodeMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        FileManager.encode(codeMap, "big.txt", "encoded.txt");
        FileManager.decode(decodeMap, "encoded.txt", "decoded.txt");
    }

}
