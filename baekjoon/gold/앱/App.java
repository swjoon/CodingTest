package baekjoon.gold.앱;

import java.util.*;
import java.io.*;

public class App {

    static int[] c;
    static int[] m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        m = new int[N];
        c = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }

        int total = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
            total += c[i];
        }

        int[][] DP = new int[N][total + 1];

        for (int i = 0; i <= total; i++) {
            if (i < c[0])
                DP[0][i] = 0;
            else
                DP[0][i] = m[0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= total; j++) {
                DP[i][j] = DP[i - 1][j];
                if (j - c[i] >= 0)
                    DP[i][j] = Math.max(DP[i][j], DP[i - 1][j - c[i]] + m[i]);
            }
        }

        for (int i = 0; i <= total; i++) {
            if (DP[N - 1][i] >= M) {
                System.out.println(i);
                break;
            }
        }

    }
}
