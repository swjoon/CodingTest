package baekjoon.gold.LCS3;

import java.io.*;

public class LCS3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = new String[3];

        for (int i = 0; i < 3; i++) {
            input[i] = br.readLine();
        }

        int fL = input[0].length();
        int sL = input[1].length();
        int tL = input[2].length();

        int[][][] DP = new int[fL + 1][sL + 1][tL + 1];

        for (int i = 1; i <= fL; i++) {
            for (int j = 1; j <= sL; j++) {
                for (int k = 1; k <= tL; k++) {
                    if (input[0].charAt(i - 1) == input[1].charAt(j - 1)
                            && input[0].charAt(i - 1) == input[2].charAt(k - 1)) {
                        DP[i][j][k] = DP[i-1][j-1][k-1] + 1;
                    } else {
                        DP[i][j][k] = Math.max(DP[i-1][j][k], Math.max(DP[i][j][k-1], DP[i][j-1][k]));
                    }
                }
            }
        }

        System.out.println(DP[fL][sL][tL]);
    }
}
