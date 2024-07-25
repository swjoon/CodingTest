package baekjoon.gold.계단수;

import java.io.*;
import java.util.*;

public class Stairs {
    static HashMap<Integer, List<Integer>> map = new HashMap<>();
    static int N;
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < 10; i++) {
            map.put(i, new ArrayList<>());
            if (i == 0)
                map.get(i).add(i + 1);
            else if (i == 9)
                map.get(i).add(i - 1);
            else {
                map.get(i).add((i + 1) % 10);
                map.get(i).add((i - 1) % 10);
            }
        }

        if (N < 10) {
            System.out.println(0);
            return;
        }

        dp = new long[10][N + 1][1 << 10];

        for (long[][] arr2D : dp) {
            for (long[] arr1D : arr2D) {
                Arrays.fill(arr1D, -1);
            }
        }

        long answer = 0;

        for (int i = 1; i <= 9; i++) {
            answer += DP(i, 1 << i, 1);
        }
        answer %= 1000000000;
        System.out.println(answer);
    }

    static long DP(int now, int visited, int seq) {
        long answer = 0;

        if (seq == N)
            return visited == (1 << 10) - 1 ? 1 : 0;

        if (dp[now][seq][visited] != -1) {
            return dp[now][seq][visited];
        }

        for (int list : map.get(now)) {
            answer += DP(list, visited | (1 << list), seq + 1);
            answer %= 1000000000;
        }

        dp[now][seq][visited] = answer;

        return answer;
    }
}
