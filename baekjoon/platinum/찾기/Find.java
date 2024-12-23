package baekjoon.platinum.찾기;

import java.io.*;

public class Find {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        String pattern = br.readLine();

        int[] pi = kmp(pattern);

        int j = 0;

        int total = 0;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
                continue;
            }

            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            if (j == (pattern.length())) {
                j = pi[j - 1];
                total++;
                sb.append("\n").append(i - pattern.length() + 2);
            }
        }

        System.out.print(total);
        System.out.println(sb.toString());
    }

    private static int[] kmp(String S) {
        int L = S.length();
        int[] pi = new int[L];
        int j = 0;

        for (int i = 1; i < L; i++) {

            while (j > 0 && S.charAt(i) != S.charAt(j)) {
                j = pi[j - 1];
            }

            if (S.charAt(i) == S.charAt(j)) {
                j++;
            }

            pi[i] = j;
        }

        return pi;
    }
}
