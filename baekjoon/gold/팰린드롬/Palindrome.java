package baekjoon.gold.팰린드롬;

import java.util.*;
import java.io.*;

public class Palindrome {
    static int N, M;
    static int[][] DP;
    static int[][] Q;
    static int[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        list = new int[N + 1];
        DP = new int[N + 1][N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        Q = new int[M][2];

        for (int i = 1; i <= N; i++) {
            DP[i][i] = 1;
        }

        for (int i = 1; i < N; i++) {
            if (list[i] == list[i + 1]) {
                DP[i][i + 1] = 1;
            }
        }

        for (int len = 3; len <= N; len++) {
            for (int i = 1; i <= N - len + 1; i++) {
                if (list[i] == list[i + len - 1] && DP[i + 1][i + len - 2] == 1) {
                    DP[i][i + len - 1] = 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Q[i][0] = Integer.parseInt(st.nextToken());
            Q[i][1] = Integer.parseInt(st.nextToken());
            sb.append(DP[Q[i][0]][Q[i][1]]).append("\n");
        }

        System.out.println(sb);
    }
}
