package baekjoon.platinum.구간합구하기2;

import java.util.*;
import java.io.*;

public class Sum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int caseCount = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());

        long[] number = new long[N];

        for (int i = 0; i < N; i++) {
            number[i] = Long.parseLong(br.readLine());
        }

        int size = 1;

        while (size < N) {
            size <<= 1;
        }

        long[] sum = new long[size * 2];
        long[] len = new long[size * 2];
        long[] lazy = new long[size * 2];

        for (int i = 0; i < N; i++) {
            sum[size + i] = number[i];
            len[size + i] = 1;
        }

        for (int i = size + N; i < size * 2; i++) {
            sum[i] = 0;
            len[i] = 0;
        }

        for (int i = size - 1; i > 0; i--) {
            sum[i] = sum[i * 2] + sum[i * 2 | 1];
            len[i] = len[i * 2] + len[i * 2 | 1];
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < caseCount; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            int left = Integer.parseInt(st.nextToken()) - 1;
            int right = Integer.parseInt(st.nextToken()) - 1;

            if (type == 1) {
                long value = Long.parseLong(st.nextToken());
                rangeAdd(left, right, value, size, sum, len, lazy);
                continue;
            }

            long ans = rangeSum(left, right, size, sum, len, lazy);

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void rangeAdd(int left, int right, long value, int size, long[] sum, long[] len, long[] lazy) {
        int sL = size + left;
        int sR = size + right;

        int l = sL;
        int r = sR;

        while (l <= r) {
            if ((l & 1) == 1)
                apply(l++, value, sum, len, lazy);
            if ((r & 1) == 0)
                apply(r--, value, sum, len, lazy);

            l >>= 1;
            r >>= 1;
        }

        build(sL, sum, len, lazy);
        build(sR, sum, len, lazy);
    }

    private static long rangeSum(int left, int right, int size, long[] sum, long[] len, long[] lazy) {
        long ans = 0;

        int sL = size + left;
        int sR = size + right;

        int l = sL;
        int r = sR;

        pushTopToBottom(sL, sum, len, lazy);
        pushTopToBottom(sR, sum, len, lazy);

        while (l <= r) {
            if ((l & 1) == 1)
                ans += sum[l++];
            if ((r & 1) == 0)
                ans += sum[r--];

            l >>= 1;
            r >>= 1;
        }

        return ans;
    }

    private static void apply(int idx, long value, long[] sum, long[] len, long[] lazy) {
        sum[idx] += value * len[idx];
        lazy[idx] += value;
    }

    private static void build(int idx, long[] sum, long[] len, long[] lazy) {
        while ((idx >>= 1) > 0) {
            sum[idx] = sum[idx << 1] + sum[idx << 1 | 1] + lazy[idx] * len[idx];
        }
    }

    private static void push(int idx, long[] sum, long[] len, long[] lazy) {
        if (lazy[idx] != 0) {
            apply(idx << 1, lazy[idx], sum, len, lazy);
            apply(idx << 1 | 1, lazy[idx], sum, len, lazy);

            lazy[idx] = 0;
        }
    }

    private static void pushTopToBottom(int idx, long[] sum, long[] len, long[] lazy) {
        int h = Integer.numberOfTrailingZeros(Integer.highestOneBit(idx));

        for (int s = h; s > 0; --s) {
            push(idx >> s, sum, len, lazy);
        }
    }
}