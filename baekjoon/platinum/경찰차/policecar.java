package baekjoon.platinum.경찰차;

import java.util.*;
import java.io.*;

public class policecar {
    static int N;
    static int W;
    static int[][] event;
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        event = new int[W][2];

        for (int i = 0; i < W; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            event[i][0] = Integer.parseInt(st.nextToken());
            event[i][1] = Integer.parseInt(st.nextToken());
        }

        // 현재위치 저장

        DP = new int[W+1][2];

        dp(W);

    }

    static int dp(int now){
        DP[now][0] = 0;
        DP[now][1] = 0;


        return 0;
    }
}
