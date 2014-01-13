package main.graph.algorithms.huffman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;

public class FileManager {

    public static void read(Histogram h, String path) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        char c;
        int i;
        while ((i = br.read()) != -1) {
            c = (char) i;
            h.put(c);
        }
        br.close();
    }

    public static void encode(HashMap<Character, String> codeMap, String from, String to) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(from)));
        PrintWriter writer = new PrintWriter(to, "UTF-8");
        char c;
        int i;
        while ((i = br.read()) != -1) {
            c = (char) i;
            writer.print(codeMap.get(c));
        }
        br.close();
        writer.close();
    }
    
    public static void encodeBits(HashMap<Character, String> codeMap, String from, String to) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(from)));
        FileOutputStream writer = new FileOutputStream(to);
        char c;
        int i;
        StringBuilder sb = new StringBuilder();
        while ((i = br.read()) != -1) {
            c = (char) i;
            sb.append(codeMap.get(c));
        }
        BitSet bs = fromString(sb.toString());
        writer.write(toByteArray(bs));
        br.close();
        writer.close();
    }

    public static void decode(HashMap<String, Character> codeMap, String from, String to) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(from)));
        PrintWriter writer = new PrintWriter(to, "UTF-8");
        char c;
        int i;
        String temp = "";
        while ((i = br.read()) != -1) {
            c = (char) i;
            temp = temp.concat(String.valueOf(c));
            if (codeMap.containsKey(temp)) {
                writer.print(codeMap.get(temp));
                temp = "";
            }
        }
        br.close();
        writer.close();
    }
    
    public static void decodeBits(HashMap<String, Character> codeMap, String from, String to) throws IOException {
        PrintWriter writer = new PrintWriter(to, "UTF-8");
       
        Path path = Paths.get(from);
        byte[] fileData = Files.readAllBytes(path);
        
        
        StringBuilder all = new StringBuilder();
        
        for (byte b : fileData) {
        	String tmp = byte2Str(b);
//        	System.out.println(tmp);
        	all.append(tmp);
        }
//        System.out.println(all.toString());
        
        String temp = "";
        for (char c : all.toString().toCharArray()) {
        	temp = temp.concat(String.valueOf(c));
            if (codeMap.containsKey(temp)) {
                writer.print(codeMap.get(temp));
                temp = "";
            }
        }

        writer.close();
    }

    public static byte[] toByteArray(BitSet bits) {
        byte[] bytes = new byte[bits.length()/8+1];
        for (int i=0; i<bits.length(); i++) {
            if (bits.get(i)) {
                bytes[bytes.length-i/8-1] |= 1<<(i%8);
            }
        }
        return bytes;
    }

//    private static BitSet fromString(final String s) {
//        return BitSet.valueOf(new long[] { Long.parseLong(s, 2) });
//    }

    private static BitSet fromString(String s) {
    	BitSet bs = new BitSet();
    	int i =0;
    	for (char c: s.toCharArray()) {
//    		System.out.println(c);
    		if (c == '0') {
    			bs.clear(i);
    		}
    		if (c == '1') {
    			bs.set(i);
    		}
    		i++;    			
    	}
//    	System.out.println(i);
//    	System.out.println(bs.length());
    	return bs;
    }

    private static String byte2Str(byte b) {
    	StringBuilder sb = new StringBuilder();
    	byte c = b;
    	int[] temp = new int[8];
    	for (int j = 0; j<8; j++) {
//    		System.out.println(c);
        	int x  = c & 1;
        	temp[j] = x;
        	c = (byte)(c >> 1);
    	}
    	for (int i =7; i>=0; i--) {
//    		System.out.println(temp[i]);
    		if (temp[i] == 1)
    			sb.append("1");
    		else 
    			sb.append("0");
    	}
    	return sb.toString();
    }
    
    private static String toString(BitSet bs) {
        return Long.toString(bs.toLongArray()[0], 2);
    }

}
