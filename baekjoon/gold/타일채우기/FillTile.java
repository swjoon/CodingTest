package baekjoon.gold.타일채우기;

import java.io.*;

public class FillTile {
    static int N;
    static int[] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        DP = new int[N + 1];
        
        if (N >= 2)
            DP[2] = 3;

        for (int i = 4; i <= N; i += 2) {
            DP[i] += 3 * DP[i - 2];
            for (int j = 4; j <= i; j += 2) {
                DP[i] += 2 * DP[i - j];
            }
            DP[i] += 2;
        }

        System.out.println(DP[N]);
    }
}
