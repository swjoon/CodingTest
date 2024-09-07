package baekjoon.gold.사전;

import java.util.*;
import java.io.*;

public class Dictionary {
    static int N, M;
    static long K;
    static long[][] Combnum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        Combnum = new long[N + 1][M + 1];

        comb(N, M);

        System.out.println((Combnum[N][M] < K) ? -1 : build(N, M));

    }

    static void comb(int n, int m) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                Combnum[i][j] = (i == 0 || j == 0) ? 1
                        : Combnum[i - 1][j] + Combnum[i][j - 1];
            }
            // overflow를 방지하기위함. K값을 넘어가는 조합을 찾은 이후의 모든 값들은 K+1로 값을 대체해도 문제없음.
            if (Combnum[i][m] >= K) {
                for (int j = i + 1; j <= n; j++) {
                    Arrays.fill(Combnum[j], K + 1);
                }
                return;
            }
        }
    }

    static String build(int n, int m) {
        StringBuilder sb = new StringBuilder();

        // n과 m을 하나씩 사용해서 순서 찾음.
        while (n > 0 && m > 0) {
            if (Combnum[n - 1][m] >= K) {
                sb.append("a");
                n--;
            } else {
                sb.append("z");
                K -= Combnum[n - 1][m];
                m--;
            }
        }

        while (n-- > 0) {
            sb.append("a");
        }
        while (m-- > 0) {
            sb.append("z");
        }

        return sb.toString();
    }

}
