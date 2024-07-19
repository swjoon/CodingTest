package baekjoon.gold.외판원순회;

import java.util.*;
import java.io.*;

public class Cycle {

    static int n;
    static int start;
    static int map[][];
    static int dp[][];
    static int max = 16000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        dp = new int[n][1 << n];
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        start = 0;

        System.out.println(TSP(start, 1 << start));

    }

    public static int TSP(int now, int visited) {
        int answer = max;
        // 모든 구간 순회 완료시 
        if (visited == (1 << n) - 1) {
            // 원점으로 돌아갈 방법이 없으면 max 
            if (map[now][start] == 0)
                return max;
            else
                return map[now][start];
        }
        // 이미 같은 방법으로 해당 지점으로 왔으면 저장된 값 반환
        if (dp[now][visited] != 0)
            return dp[now][visited];

        for (int i = 0; i < n; i++) {
            // 방문한곳이거나 이동할 방법이 없으면 넘어감
            if ((visited & (1 << i)) > 0 || map[now][i] == 0)
                continue;
            // 각 구간마다 최소치 구함
            answer = Math.min(answer, map[now][i] + TSP(i, visited | (1 << i)));
        }
        // 각 구간의 최소값 저장및 반환
        return dp[now][visited] = answer;
    }
}