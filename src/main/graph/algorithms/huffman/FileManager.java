package main.graph.algorithms.huffman;

import java.io.*;
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

    private byte[] toByteArray(BitSet bits) {
        byte[] bytes = new byte[(bits.length() + 7) / 8];
        for (int i=0; i<bits.length(); i++) {
            if (bits.get(i)) {
                bytes[bytes.length-i/8-1] |= 1<<(i%8);
            }
        }
        return bytes;
    }

}
