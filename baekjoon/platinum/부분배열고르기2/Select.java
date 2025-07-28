package baekjoon.platinum.부분배열고르기2;

import java.io.*;
import java.util.*;

public class Select {

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            int size = 1;

            while (size < N) {
                size <<= 1;
            }

            long[] sum = new long[size << 1];
            int[] minIdx = new int[size << 1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] num = new int[N];

            for (int i = 0; i < N; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                sum[size + i] = num[i];
                minIdx[size + i] = i;
            }

            for (int i = size - 1; i > 0; i--) {
                sum[i] = sum[i << 1] + sum[i << 1 | 1];
                minIdx[i] = num[minIdx[i << 1]] < num[minIdx[i << 1 | 1]] ? minIdx[i << 1] : minIdx[i << 1 | 1];
            }

            Answer ans = findMaxValue(0, N - 1, size, num, sum, minIdx);

            StringBuilder sb = new StringBuilder();

            sb.append(ans.value).append("\n")
                    .append(ans.left + 1)
                    .append(" ")
                    .append(ans.right + 1);

            System.out.println(sb.toString());
        }
    }

    private static Answer findMaxValue(int l, int r, int size, int[] num, long[] sum, int[] minIdx) {
        if (l > r) {
            return new Answer(0, 0, 0);
        }

        int mid = findMin(size + l, size + r, num, minIdx);

        Answer total = new Answer(l, r, findSum(size + l, size + r, sum) * num[mid]);

        if (l == r) {
            return total;
        }

        Answer left = findMaxValue(l, mid - 1, size, num, sum, minIdx);
        Answer right = findMaxValue(mid + 1, r, size, num, sum, minIdx);

        Answer maxV = left.value > right.value ? left : right;

        maxV = total.value > maxV.value ? total : maxV;

        return maxV;
    }

    private static long findSum(int l, int r, long[] sum) {
        long ans = 0;

        while (l <= r) {
            if ((l & 1) == 1) {
                ans += sum[l++];
            }

            if ((r & 1) == 0) {
                ans += sum[r--];
            }

            l >>= 1;
            r >>= 1;
        }

        return ans;
    }

    private static int findMin(int l, int r, int[] num, int[] minIdx) {
        int minV = INF;
        int idx = 0;

        while (l <= r) {
            if ((l & 1) == 1) {
                if (num[minIdx[l]] < minV) {
                    minV = num[minIdx[l]];
                    idx = minIdx[l];
                }
                l++;
            }

            if ((r & 1) == 0) {
                if (num[minIdx[r]] < minV) {
                    minV = num[minIdx[r]];
                    idx = minIdx[r];
                }
                r--;
            }

            l >>= 1;
            r >>= 1;
        }

        return idx;
    }
}

class Answer {

    int left;
    int right;
    long value;

    public Answer(int left, int right, long value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }
}