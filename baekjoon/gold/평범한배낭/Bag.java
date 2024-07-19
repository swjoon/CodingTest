package baekjoon.gold.평범한배낭;

import java.io.*;
import java.util.*;

public class Bag {

    static int n;
    static int k;
    static int[][] bag;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sti = new StringTokenizer(br.readLine());

        n = Integer.parseInt(sti.nextToken());
        k = Integer.parseInt(sti.nextToken());

        bag = new int[n][2];
        dp = new int[k + 1][1 << n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                bag[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        dp[0][0] = 0;

        dpdfs(0, 0);

        int maxValue = 0;

        for (int i = 0; i <= k; i++) {
            maxValue = Math.max(maxValue, Arrays.stream(dp[i]).max().orElse(0));
        }

        System.out.println(maxValue);
    }

    public static void dpdfs(int sum, int visited) {

        for (int i = 0; i < n; i++) {
            if ((visited & (1 << i)) > 0 || sum + bag[i][0] > k)
                continue;
            if (dp[sum + bag[i][0]][visited | (1 << i)] < dp[sum][visited] + bag[i][1]) {
                dp[sum + bag[i][0]][visited | (1 << i)] = dp[sum][visited] + bag[i][1];
                dpdfs(sum + bag[i][0], visited | (1 << i));
            }
        }
    }
}
