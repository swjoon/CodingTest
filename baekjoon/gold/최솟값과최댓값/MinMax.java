package baekjoon.gold.최솟값과최댓값;

import java.util.*;
import java.io.*;

public class MinMax {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int size = 1;

        int[] number = new int[N];

        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(br.readLine());
        }

        while (size < N) {
            size <<= 1;
        }

        int[] minNumber = new int[size * 2];
        int[] maxNumber = new int[size * 2];

        for (int i = 0; i < N; i++) {
            minNumber[size + i] = number[i];
            maxNumber[size + i] = number[i];
        }

        for (int i = size + N; i < size * 2; i++) {
            minNumber[i] = Integer.MAX_VALUE;
            maxNumber[i] = 0;
        }

        for (int i = size - 1; i > 0; i--) {
            minNumber[i] = Math.min(minNumber[i * 2], minNumber[i * 2 + 1]);
            maxNumber[i] = Math.max(maxNumber[i * 2], maxNumber[i * 2 + 1]);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            int min = findMin(start, end, size, minNumber);
            int max = findMax(start, end, size, maxNumber);

            sb.append(min).append(" ").append(max).append("\n");

        }

        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb.toString());

        br.close();
    }

    private static int findMin(int start, int end, int size, int[] minNumber) {
        int ans = Integer.MAX_VALUE;
        int left = size + start;
        int right = size + end;

        while (left <= right) {
            if ((left & 1) == 1)
                ans = Math.min(ans, minNumber[left++]);

            if ((right & 1) == 0)
                ans = Math.min(ans, minNumber[right--]);

            left >>= 1;
            right >>= 1;
        }

        return ans;
    }

    private static int findMax(int start, int end, int size, int[] maxNumber) {
        int ans = 0;
        int left = size + start;
        int right = size + end;

        while (left <= right) {
            if ((left & 1) == 1)
                ans = Math.max(ans, maxNumber[left++]);

            if ((right & 1) == 0)
                ans = Math.max(ans, maxNumber[right--]);

            left >>= 1;
            right >>= 1;
        }

        return ans;
    }
}