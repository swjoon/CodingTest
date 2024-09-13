package baekjoon.gold.DanceRevolution;

import java.io.*;

public class DDR {
    static int N;
    static String[] seq;
    static int[][][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        seq = br.readLine().split(" ");
        N = seq.length;
        DP = new int[N + 1][5][5];

        System.out.println(DFS(0, 0, 0));

    }

    static int DFS(int L, int R, int dep) {
        int now = Integer.parseInt(seq[dep]);

        if (now == 0) {
            return DP[dep][L][R];
        }

        if (DP[dep][L][R] != 0) {
            return DP[dep][L][R];
        }

        DP[dep][L][R] = Math.min(check(L, now) + DFS(now, R, dep + 1), check(R, now) + DFS(L, now, dep + 1));

        return DP[dep][L][R];
    }

    static int check(int before, int after) {
        int abs = Math.abs(after - before);

        if (before == 0) {
            return 2;
        } else if (abs == 2) {
            return 4;
        } else if (abs == 0) {
            return 1;
        }

        return 3;
    }
}
