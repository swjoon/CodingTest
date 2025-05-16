package baekjoon.gold.최솟값;

import java.util.*;
import java.io.*;

public class Min {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] number = new int[N];

        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(br.readLine());
        }

        int size = 1;

        while (size < N) {
            size <<= 1;
        }

        int[] minNum = new int[size * 2];

        for (int i = 0; i < N; i++) {
            minNum[i + size] = number[i];
        }

        for (int i = size + N; i < size * 2; i++) {
            minNum[i] = Integer.MAX_VALUE;
        }

        for (int i = size - 1; i > 0; i--) {
            minNum[i] = Math.min(minNum[i * 2], minNum[i * 2 + 1]);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            int min = findMin(start, end, size, minNum);

            sb.append(min).append("\n");
        }

        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb.toString());

        br.close();
    }

    private static int findMin(int start, int end, int size, int[] minNum) {
        int ans = Integer.MAX_VALUE;
        int left = size + start;
        int right = size + end;

        while (left <= right) {
            if ((left & 1) == 1) ans = Math.min(ans, minNum[left++]);

            if ((right & 1) == 0) ans = Math.min(ans, minNum[right--]);

            left >>= 1;
            right >>= 1;
        }

        return ans;
    }
}
