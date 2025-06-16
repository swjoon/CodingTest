package baekjoon.platinum.히스토그램6549;

import java.util.*;
import java.io.*;

public class Histogram {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());

            int width = Integer.parseInt(st.nextToken());

            if (width == 0) {
                break;
            }

            int[] height = new int[width];

            for (int i = 0; i < width; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }

            int size = 1;

            while (size < width) {
                size <<= 1;
            }

            int[][] minH = new int[size * 2][2];

            for (int i = 0; i < width; i++) {
                minH[i + size][0] = height[i];
                minH[i + size][1] = i;
            }

            for (int i = size + width; i < size * 2; i++) {
                minH[i][0] = INF;
                minH[i][1] = INF;
            }

            for (int i = size - 1; i > 0; i--) {
                int min = Math.min(minH[i << 1][0], minH[(i << 1) | 1][0]);

                int idx = min == minH[i << 1][0] ? minH[i << 1][1] : minH[(i << 1) | 1][1];

                minH[i][0] = min;
                minH[i][1] = idx;
            }

            long maxArea = getMaxArea(0, width - 1, size, minH, height);

            sb.append(maxArea).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static long getMaxArea(int l, int r, int size, int[][] minH, int[] height) {
        if (l > r)
            return 0;

        int minIdx = findMinHeightIdx(l, r, size, minH, height);

        long maxArea = (long) height[minIdx] * (r - l + 1);

        long leftMaxArea = getMaxArea(l, minIdx - 1, size, minH, height);

        long rightMaxArea = getMaxArea(minIdx + 1, r, size, minH, height);

        return Math.max(maxArea, Math.max(leftMaxArea, rightMaxArea));
    }

    private static int findMinHeightIdx(int left, int right, int size, int[][] minH, int[] height) {
        int l = size + left;
        int r = size + right;

        int minHeight = minH[l][0];
        int minIdx = minH[l][1];

        while (l <= r) {

            if ((l & 1) == 1) {
                if (minH[l][0] < minHeight) {

                    minHeight = minH[l][0];
                    minIdx = minH[l][1];
                }
                l++;
            }

            if ((r & 1) == 0) {
                if (minH[r][0] < minHeight) {

                    minHeight = minH[r][0];
                    minIdx = minH[r][1];
                }

                r--;
            }

            r >>= 1;
            l >>= 1;
        }

        return minIdx;
    }
}