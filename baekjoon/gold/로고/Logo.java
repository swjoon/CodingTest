package baekjoon.gold.로고;

import java.util.*;
import java.io.*;

public class Logo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        rectangles[] reg = new rectangles[N];

        boolean isIncludeZPoint = false;

        int count = N;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            reg[i] = new rectangles(x1, y1, x2, y2);

            if (!isIncludeZPoint && includeZeroPoint(x1, y1, x2, y2)) {
                count--;
                isIncludeZPoint = true;
            }
        }

        int[] parent = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int now = 0; now < N - 1; now++) {
            for (int next = now + 1; next < N; next++) {
                if (check(reg[now], reg[next]) && union(parent, now, next)) {
                    count--;
                }
            }
        }

        System.out.println(count);
    }

    private static boolean includeZeroPoint(int x1, int y1, int x2, int y2) {
        return (x1 <= 0 && x2 >= 0 && (y1 == 0 || y2 == 0)) ||
                (y1 <= 0 && y2 >= 0 && (x1 == 0 || x2 == 0));
    }

    private static boolean check(rectangles A, rectangles B) {
        return !(A.x1 > B.x2 || A.x2 < B.x1 || A.y2 < B.y1 || A.y1 > B.y2
                || (A.x1 > B.x1 && A.x2 < B.x2 && A.y1 > B.y1 && A.y2 < B.y2)
                || (B.x1 > A.x1 && B.x2 < A.x2 && B.y1 > A.y1 && B.y2 < A.y2));

    }

    private static int findParent(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = findParent(parent, parent[i]);
        }
        return parent[i];
    }

    private static boolean union(int[] parent, int A, int B) {
        int parentA = findParent(parent, A);
        int parentB = findParent(parent, B);

        if (parentA == parentB) {
            return false;
        }

        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }

        return true;
    }

    static class rectangles {
        int x1, y1, x2, y2;

        public rectangles(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}