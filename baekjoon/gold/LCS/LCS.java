package baekjoon.gold.LCS;

import java.io.*;

public class LCS {
    static int DP[][];
    static int len1, len2;
    static String[] s1, s2;
    static String string1, string2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        string1 = br.readLine();
        string2 = br.readLine();

        len1 = string1.length();
        len2 = string2.length();

        s1 = new String[len1];
        s2 = new String[len2];
        s1 = string1.split("");
        s2 = string2.split("");

        DP = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s1[i - 1].equals(s2[j - 1])) {
                    DP[i][j] = DP[i - 1][j - 1] + 1;
                } else {
                    DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]);
                }
            }
        }

        System.out.println(DP[len1][len2]);
    }
}
