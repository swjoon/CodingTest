package baekjoon.platinum.영화수집;

import java.util.*;
import java.io.*;

// segment tree
public class Movie {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < T; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int movieC = Integer.parseInt(st.nextToken());
            int movieW = Integer.parseInt(st.nextToken());

            int totalC = movieC + movieW;

            int size = 1;

            while (size < totalC) {
                size <<= 1;
            }

            int[] movieL = new int[totalC + 1];
            int[] tree = new int[size << 1];

            for (int i = 0; i < movieC; i++) {
                movieL[i + 1] = movieW + i;
                tree[size + movieW + i] = 1;
            }

            for (int i = size - 1; i > 0; i--) {
                tree[i] = tree[i << 1] + tree[i << 1 | 1];
            }

            st = new StringTokenizer(br.readLine());

            for (int num = movieW - 1; num >= 0; num--) {
                int movieN = Integer.parseInt(st.nextToken());

                int count = find(size, size + movieL[movieN] - 1, tree);

                update(size + movieL[movieN], 0, tree);
                update(size + num, 1, tree);

                movieL[movieN] = num;

                sb.append(count).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int find(int l, int r, int[] tree) {
        int count = 0;

        while (l <= r) {
            if ((l & 1) == 1) {
                count += tree[l];
                l++;
            }

            if ((r & 1) == 0) {
                count += tree[r];
                r--;
            }

            l >>= 1;
            r >>= 1;
        }

        return count;
    }

    private static void update(int l, int v, int[] tree) {
        tree[l] = v;

        int n = l >> 1;

        while (0 < n) {
            tree[n] = tree[n << 1] + tree[n << 1 | 1];

            n >>= 1;
        }
    }
}
