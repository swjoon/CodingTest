package baekjoon.gold.파일합치기;

import java.io.*;
import java.util.StringTokenizer;

public class FileMerge {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {

            int pageC = Integer.parseInt(br.readLine());

            int[] sizes = new int[pageC];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < pageC; i++) {
                sizes[i] = Integer.parseInt(st.nextToken());
            }

            long ans = solution(pageC, sizes);

            sb.append(ans).append("\n");
        }

        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb.toString());
    }

    private static long solution(int pageC, int[] sizes) {
        long[][] dp = new long[pageC + 1][pageC + 1];
        int[][] opt = new int[pageC + 1][pageC + 1];

        long[] prefixSum = prefixSum(sizes);

        for (int i = 1; i <= pageC; i++) {
            dp[i][i] = 0;
            opt[i][i] = i;
        }

        for (int len = 2; len <= pageC; len++) {
            for (int l = 1; l + len - 1 <= pageC; l++) {
                int r = l + len - 1;

                long w = prefixSum[r] - prefixSum[l - 1];

                dp[l][r] = Long.MAX_VALUE;

                int s = opt[l][r - 1] < l ? l : opt[l][r - 1];
                int e = opt[l + 1][r] > r - 1 ? r - 1 : opt[l + 1][r];

                for (int k = s; k <= e; k++) {
                    long v = dp[l][k] + dp[k + 1][r] + w;

                    if (v < dp[l][r]) {
                        dp[l][r] = v;
                        opt[l][r] = k;
                    }
                }

            }
        }

        return dp[1][pageC];
    }

    private static long[] prefixSum(int[] sizes) {
        int size = sizes.length + 1;

        long[] prefix = new long[size];

        for (int i = 1; i < size; i++) {
            prefix[i] = prefix[i - 1] + sizes[i - 1];
        }

        return prefix;
    }

}
