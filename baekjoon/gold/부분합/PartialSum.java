package baekjoon.gold.부분합;

import java.util.*;
import java.io.*;

public class PartialSum {
    static int N, S;
    static int[] list;
    static long[] sumlist;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        list = new int[N + 1];
        sumlist = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
            sumlist[i] = sumlist[i - 1] + list[i];
        }

        int s = 0, e = 1;

        while (s < N && e <= N) {
            long sum = sumlist[e] - sumlist[s];
            int len = e - s;
            
            if (sum < S) {
                e++;
                continue;
            }

            if (sum >= S) {
                ans = Math.min(ans, len);
                s++;
            }

            if (s == e) {
                e++;
            }
        }

        System.out.println((ans == Integer.MAX_VALUE) ? 0 : ans);
    }
}
