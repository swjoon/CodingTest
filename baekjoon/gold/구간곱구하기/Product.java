package baekjoon.gold.구간곱구하기;

import java.util.*;
import java.io.*;

public class Product {
    private static final long MOD = 1000000007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] number = new long[N];

        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(br.readLine());
        }

        int size = 1;

        while (size < N) {
            size <<= 1;
        }

        long[] productNode = new long[size * 2];

        for (int i = 0; i < N; i++) {
            productNode[size + i] = number[i];
        }

        for (int i = size + N; i < size * 2; i++) {
            productNode[i] = 1;
        }

        for (int i = size - 1; i > 0; i--) {
            productNode[i] = (productNode[i * 2] * productNode[i * 2 + 1]) % MOD;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                changeNumber(b - 1, c, size, productNode);
            } else {
                long product = findProductNumber(b - 1, c - 1, size, productNode);
                sb.append(product).append("\n");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void changeNumber(int node, int number, int size, long[] productNode) {
        int n = size + node;

        productNode[n] = number;

        n >>= 1;

        while (n > 0) {

            productNode[n] = (productNode[n * 2] * productNode[n * 2 + 1]) % MOD;

            n >>= 1;
        }
    }

    private static long findProductNumber(int left, int right, int size, long[] productNode) {
        long ans = 1;
        int l = size + left;
        int r = size + right;

        while (l <= r) {
            if ((l & 1) == 1) ans = (ans * productNode[l++]) % MOD;

            if ((r & 1) == 0)  ans = (ans * productNode[r--]) % MOD;

            l >>= 1;
            r >>= 1;
        }

        return ans;
    }
}