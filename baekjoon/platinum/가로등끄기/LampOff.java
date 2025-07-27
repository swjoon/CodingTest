package baekjoon.platinum.가로등끄기;

import java.io.*;
import java.util.*;

public class LampOff {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int[] point = new int[N + 1];
        int[] energy = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            point[i] = Integer.parseInt(st.nextToken());
            energy[i] = Integer.parseInt(st.nextToken());
        }

        int[][][] DP = new int[N + 1][N + 1][2];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                Arrays.fill(DP[i][j], INF);
            }
        }

        DP[P][P][0] = DP[P][P][1] = 0;

        int[] sum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + energy[i];
        }

        for (int len = 1; len <= N; len++) {
            for (int l = 1; l + len - 1 <= N; l++) {
                int r = l + len - 1;
                
                for (int cur = 0; cur < 2; cur++) {

                    if (DP[l][r][cur] == INF)
                        continue;

                    int totalEnergy = sum[N] - (sum[r] - sum[l - 1]);

                    if (l > 1) {
                        int dist = cur == 0 ? point[l] - point[l - 1] : point[r] - point[l - 1];

                        DP[l - 1][r][0] = Math.min(DP[l - 1][r][0], DP[l][r][cur] + dist * totalEnergy);
                    }

                    if (r < N) {
                        int dist = cur == 0 ? point[r + 1] - point[l] : point[r + 1] - point[r];
                        
                        DP[l][r + 1][1] = Math.min(DP[l][r + 1][1], DP[l][r][cur] + dist * totalEnergy);
                    }
                }
            }
        }

        int minE = Math.min(DP[1][N][0], DP[1][N][1]);

        System.out.println(minE);

        br.close();
    }
}