package baekjoon.platinum.구간합구하기3;

import java.util.*;
import java.io.*;

public class Sum3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int caseC = Integer.parseInt(st.nextToken());

        int size = 1;

        while (size < N) {
            size <<= 1;
        }

        int[][] tree = new int[size * 2][size * 2];

        // 2D - segment tree setting
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                tree[size + y][size + x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = size - 1; x > 0; x--) {
                tree[size + y][x] = tree[size + y][x << 1] + tree[size + y][x << 1 | 1];
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = size - 1; y > 0; y--) {
                tree[y][size + x] = tree[y << 1][size + x] + tree[y << 1 | 1][size + x];
            }
        }

        for (int x = 1; x < size; x++) {
            for (int y = size - 1; y > 0; y--) {
                tree[y][x] = tree[y << 1][x] + tree[y << 1 | 1][x];
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int testcase = 0; testcase < caseC; testcase++) {
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());

            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x1 = Integer.parseInt(st.nextToken()) - 1;

            if (type == 1) {
                int y2 = Integer.parseInt(st.nextToken()) - 1;
                int x2 = Integer.parseInt(st.nextToken()) - 1;

                int sum = find(x1, y1, x2, y2, size, tree);
                sb.append(sum).append("\n");
            } else {
                int v = Integer.parseInt(st.nextToken());

                update(x1, y1, v, size, tree);
            }
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int find(int x1, int y1, int x2, int y2, int size, int[][] tree) {
        int ans = 0;

        int l1 = size + x1;
        int r1 = size + x2;

        while (l1 <= r1) {
            if ((l1 & 1) == 1) {
                int l2 = size + y1;
                int r2 = size + y2;

                while (l2 <= r2) {
                    if ((l2 & 1) == 1) {
                        ans += tree[l2++][l1];
                    }
                    if ((r2 & 1) == 0) {
                        ans += tree[r2--][l1];
                    }

                    l2 >>= 1;
                    r2 >>= 1;
                }

                l1++;
            }

            if ((r1 & 1) == 0) {
                int l2 = size + y1;
                int r2 = size + y2;

                while (l2 <= r2) {
                    if ((l2 & 1) == 1) {
                        ans += tree[l2++][r1];
                    }
                    if ((r2 & 1) == 0) {
                        ans += tree[r2--][r1];
                    }

                    l2 >>= 1;
                    r2 >>= 1;
                }

                r1--;
            }

            l1 >>= 1;
            r1 >>= 1;
        }

        return ans;
    }

    private static void update(int x, int y, int v, int size, int[][] tree) {
        int nx = size + x;
        int ny = size + y;

        tree[ny][nx] = v;

        while (nx > 0) {
            int yy = ny;
            tree[yy][nx] = nx == size + x ? v : tree[yy][nx << 1] + tree[yy][nx << 1 | 1];

            yy >>= 1;

            while (yy > 0) {
                tree[yy][nx] = tree[yy << 1][nx] + tree[yy << 1 | 1][nx];
                yy >>= 1;
            }

            nx >>= 1;
        }
    }
}