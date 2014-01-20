package test.algorithms.huffman;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

import main.graph.algorithms.huffman.FileManager;
import main.graph.algorithms.huffman.Histogram;
import main.graph.algorithms.huffman.Huffman;
import main.graph.algorithms.huffman.HuffmanTree;

import org.junit.Test;

/**
 * User: filip Date: 12.01.14 Time: 19:21
 */
public class HuffmanTest {

	private String fn = "big.txt";

	@Test
	public void histogramTest() throws IOException {
		Histogram h = new Histogram();
		FileManager.read(h, fn);
		h.sort();
		h.printHistogram();
	}

	@Test
	public void decodeTest() throws IOException {
		Histogram h = new Histogram();
		FileManager.read(h, fn);
		h.sort();
		Huffman huffman = new Huffman(h);

		HuffmanTree huffmanTree = huffman.getHuffmanTree();
		writeTree(huffmanTree);
		HuffmanTree huffmanTree2 = readTree();
		
		huffman.computeCodeMaps(huffmanTree2);
		HashMap<Character, String> codeMap = huffman.getCodeMap();
		HashMap<String, Character> decodeMap = huffman.getDecodeMap();

		int offset = FileManager.encodeBits(codeMap, fn, "encoded.txt");
		FileManager.decodeBits(decodeMap, "encoded.txt", "decoded.txt", offset);
	}

	private void writeTree(HuffmanTree tree) throws IOException {
		OutputStream file = new FileOutputStream("tree.bin");
		OutputStream buffer = new BufferedOutputStream(file);
		ObjectOutput output = new ObjectOutputStream(buffer);

		output.writeObject(tree);
		output.close();
	}
	
	private HuffmanTree readTree() throws IOException {
		InputStream file = new FileInputStream( "tree.bin" );
		InputStream buffer = new BufferedInputStream( file );
		ObjectInput input = new ObjectInputStream ( buffer );

		HuffmanTree tree = null;
		try {
			tree = (HuffmanTree)input.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		input.close();
		return tree;
	}

}
