package baekjoon.gold.행렬곱셈순서;

import java.util.*;
import java.io.*;

public class Matrix {
    static int N;
    static int[][] dp;
    static int[] value;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N][N];
        value = new int[N + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            value[i] = Integer.parseInt(st.nextToken());
            value[i + 1] = Integer.parseInt(st.nextToken());
        }

        // length == 구간의 길이 -> 구간별 최소계산수 찾기.
        for (int length = 2; length <= N; length++) {
            // 시작점
            for (int i = 0; i < N - length + 1; i++) {
                // 구간 끝
                int j = i + length - 1; 

                dp[i][j] = Integer.MAX_VALUE;

                // 최적의 곱셈 순서 찾기
                for (int k = i; k < j; k++) {
                    // i ~ k 구간의 최소값 + k ~ j 구간의 최소값 + (i~k) 행렬 * (k~j) 행렬
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + value[i] * value[k + 1] * value[j + 1]);
                }
            }
        }
        // 0 ~ N - 1 구간의 최소값 
        System.out.println(dp[0][N - 1]);
    }
}
