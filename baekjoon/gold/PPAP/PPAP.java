package baekjoon.gold.PPAP;

import java.util.*;
import java.io.*;

public class PPAP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        Deque<Character> ppap = new ArrayDeque<>();

        for (int i = 0; i < input.length; i++) {
            if (input[i] == 'P') {
                ppap.add(input[i]);
                continue;
            }

            if (ppap.size() < 2 || i == input.length - 1 || input[++i] == 'A') {
                System.out.println("NP");
                return;
            }

            char second = ppap.pollFirst();
            char first = ppap.peekFirst();

            if (second != 'P' || first != 'P') {
                System.out.println("NP");
                return;
            }
        }

        if (ppap.size() > 1) {
            System.out.println("NP");
            return;
        }

        System.out.println("PPAP");
    }
}
