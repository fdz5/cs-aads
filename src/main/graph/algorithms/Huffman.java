package main.graph.algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Huffman {

	private final Map<Character, Integer> chars;

	public Huffman() {
		chars = new HashMap<>();
	}

	public void encodeFile(String file, String output) {
		readFile(file);
	}

	public byte[] encode(String txt) {
		return null;
	}

	public String decode(byte[] bytes) {
		return null;
	}

	public void parse(String txt) {

	}
	
	public Map<Character, Integer> readFile(String file) {
		File f = new File(file);
		handleFile(f);
		return chars;
	}

	private void handleFile(File file) {
		try (InputStream in = new FileInputStream(file);
				Reader reader = new InputStreamReader(in);
				Reader buffer = new BufferedReader(reader)) {
			handleCharacters(buffer);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void handleCharacters(Reader reader) throws IOException {
		int r;
		while ((r = reader.read()) != -1) {
			char ch = (char) r;
			if (chars.get(ch) != null) {
				chars.put(ch, chars.get(ch) + 1);
			} else {
				chars.put(ch, 1);
			}
		}
	}

}
