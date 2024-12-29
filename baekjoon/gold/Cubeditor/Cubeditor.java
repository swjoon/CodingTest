package baekjoon.gold.Cubeditor;

import java.io.*;

public class Cubeditor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(br.readLine());

        int maxLength = 0;

        while (sb.length() != 0) {

            maxLength = Math.max(maxLength, kmp(sb));

            sb.deleteCharAt(0);
        }

        System.out.println(maxLength);
    }

    private static int kmp(StringBuilder sb) {
        int L = sb.length();
        int[] pi = new int[L];
        int j = 0;
        int maxValue = 0;

        for (int i = 1; i < L; i++) {
            while (j > 0 && sb.charAt(i) != sb.charAt(j)) {
                j = pi[j - 1];
            }

            if (sb.charAt(i) == sb.charAt(j)) {
                j++;
            }

            pi[i] = j;

            maxValue = Math.max(maxValue, pi[i]);
        }

        return maxValue;
    }
}
