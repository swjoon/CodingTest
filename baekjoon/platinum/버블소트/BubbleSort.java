package baekjoon.platinum.버블소트;

import java.util.*;
import java.io.*;

public class BubbleSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] A = new int[N];
        int[] tmp = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        long count = find(A, tmp, 0, N - 1);

        System.out.println(count);

        br.close();
    }

    public static long find(int[] A, int[] tmp, int start, int end) {
        long count = 0;

        if (start >= end) {
            return count;
        }

        int mid = (start + end) / 2;

        count += find(A, tmp, start, mid);
        count += find(A, tmp, mid + 1, end);

        int i = start;
        int j = mid + 1;
        int k = start;

        while (i <= mid && j <= end) {
            if (A[i] <= A[j]) {
                tmp[k++] = A[i++];
            } else {
                count += (mid + 1 - i);
                tmp[k++] = A[j++];
            }
        }

        while (i <= mid) {
            tmp[k++] = A[i++];
        }
        while (j <= end) {
            tmp[k++] = A[j++];
        }

        for (int s = start; s <= end; s++) {
            A[s] = tmp[s];
        }

        return count;
    }
}