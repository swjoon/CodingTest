package baekjoon.gold.소형기관차;

import java.util.*;
import java.io.*;

public class Train {
    static int T, N;
    static int[] peoplesum;
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        DP = new int[4][T + 1];
        peoplesum = new int[T + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= T; i++) {
            peoplesum[i] = peoplesum[i - 1] + Integer.parseInt(st.nextToken());
        }

        N = Integer.parseInt(br.readLine());

        for (int i = 1; i < 4; i++) {
            for (int j = i * N; j <= T; j++) {
                DP[i][j] = Math.max(DP[i][j - 1], DP[i - 1][j - N] + peoplesum[j] - peoplesum[j - N]);
            }
        }

        System.out.println(DP[3][T]);
    }

}
