package main.graph.algorithms.huffman;

import java.io.*;
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

    public static int encodeBits(HashMap<Character, String> codeMap, String from, String to) throws IOException {
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
        byte[] bytes = toByteArray(bs);
        writer.write(bytes);
        br.close();
        writer.close();
        int offset = bytes.length * 8 - bs.length() - 1;
        if (offset > 0)
            return offset;
        else
            return 0;
    }

    public static void decodeBits(HashMap<String, Character> codeMap, String from, String to, int offset) throws IOException {
        PrintWriter writer = new PrintWriter(to, "UTF-8");

        Path path = Paths.get(from);
        byte[] fileData = Files.readAllBytes(path);


        StringBuilder all = new StringBuilder();

        for (byte b : fileData) {
            String tmp = byte2Str(b);
            all.append(tmp);
        }

        String temp = "";
        String read = new StringBuilder(all.substring(offset)).reverse().toString();
        for (char c : read.toCharArray()) {
            temp = temp.concat(String.valueOf(c));
            if (codeMap.containsKey(temp)) {
                writer.print(codeMap.get(temp));
                temp = "";
            }
        }

        writer.close();
    }

    public static byte[] toByteArray(BitSet bits) {
        byte[] bytes = new byte[(bits.length() + 7) / 8];
        for (int i = 0; i < bits.length(); i++) {
            if (bits.get(i)) {
                bytes[bytes.length - i / 8 - 1] |= 1 << (i % 8);
            }
        }
        return bytes;
    }

    private static BitSet fromString(String s) {
        BitSet bs = new BitSet();
        int i = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                bs.clear(i);
            }
            if (c == '1') {
                bs.set(i);
            }
            i++;
        }
        return bs;
    }

    private static String byte2Str(byte b) {
        StringBuilder sb = new StringBuilder();
        byte c = b;
        int[] temp = new int[8];
        for (int j = 0; j < 8; j++) {
            int x = c & 1;
            temp[j] = x;
            c = (byte) (c >> 1);
        }
        for (int i = 7; i >= 0; i--) {
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
