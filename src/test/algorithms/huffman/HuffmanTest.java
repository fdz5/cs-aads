package test.algorithms.huffman;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import main.graph.algorithms.huffman.FileManager;
import main.graph.algorithms.huffman.Histogram;
import main.graph.algorithms.huffman.Huffman;
import main.graph.algorithms.huffman.HuffmanTree;

import org.junit.Test;

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
		Histogram h = new Histogram();
		FileManager.read(h, "big.txt");
		h.sort();
		Huffman huffman = new Huffman(h);

		HuffmanTree huffmanTree = huffman.getHuffmanTree();
		huffman.computeCodeMaps(huffmanTree);
		HashMap<Character, String> codeMap = huffman.getCodeMap();
		HashMap<String, Character> decodeMap = huffman.getDecodeMap();

		FileManager.encodeBits(codeMap, "big.txt", "encoded.txt");
		FileManager.decodeBits(decodeMap, "encoded.txt", "decoded.txt");
	}

	// public static void main(String[] args) throws IOException {
	//
	// Histogram h = new Histogram();
	// FileManager.read(h, "big.txt");
	// h.sort();
	// Huffman huffman = new Huffman(h);
	//
	// // TreeMap<Character, Integer> tm = h.getHistogram();
	// // System.out.println("Tree map size: " + tm.size());
	// // System.out.println("Base size: " + h.getBase().size());
	// // h.printHistogram();
	//
	// HuffmanTree huffmanTree = huffman.getHuffmanTree();
	// // huffmanTree.printTree();
	// huffman.computeCodeMaps(huffmanTree);
	//
	// HashMap<Character, String> codeMap = huffman.getCodeMap();
	// HashMap<String, Character> decodeMap = huffman.getDecodeMap();
	//
	// System.out.println("Encode map");
	// for (Map.Entry<Character, String> entry : codeMap.entrySet()) {
	// System.out.println(entry.getKey() + " - " + entry.getValue());
	// }
	// System.out.println("Decode map");
	// for (Map.Entry<String, Character> entry : decodeMap.entrySet()) {
	// System.out.println(entry.getKey() + " - " + entry.getValue());
	// }
	//
	//
	//
	// }

}
