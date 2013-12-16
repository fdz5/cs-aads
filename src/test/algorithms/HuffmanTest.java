package test.algorithms;

import java.util.Map;

import main.graph.algorithms.Huffman;

import org.junit.Assert;
import org.junit.Test;

public class HuffmanTest {
	
	@Test
	public void parseTest() {
		Huffman h = new Huffman();
		Map<Character, Integer> chars = h.readFile("big.txt");
		Assert.assertEquals(128457, chars.get('\n').intValue());
	}

}
