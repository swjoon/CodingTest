package baekjoon.gold.나머지합;

import java.util.*;
import java.io.*;

public class Remain {
    static int N, M;
    static long ans = 0;
    static long[] r;
    static long[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        r = new long[M];
        list = new long[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            list[i] = list[i - 1] + Long.parseLong(st.nextToken());
            check(list[i]);
        }

        // 나머지마다 조합을 해서 쌍을 구함. 
        for (int i = 0; i < M; i++) {
            if (r[i] > 0){
                ans += (r[i] * (r[i] - 1)) / 2;
            }
        }

        System.out.println(ans);
    }

    // 나머지 측정
    static void check(long num) {
        if (num % M == 0) {
            ans++;
        }
        r[(int)(num % M)]++;
    }
}
