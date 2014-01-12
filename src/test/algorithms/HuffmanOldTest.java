package test.algorithms;

import java.util.Map;

import main.graph.algorithms.HuffmanOld;

import org.junit.Assert;
import org.junit.Test;

public class HuffmanOldTest {
	
	@Test
	public void parseTest() {
		HuffmanOld h = new HuffmanOld();
		Map<Character, Integer> chars = h.readFile("big.txt");
		Assert.assertEquals(128457, chars.get('\n').intValue());
	}

}
