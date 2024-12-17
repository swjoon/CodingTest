package baekjoon.gold.CountCircleGroups;

import java.util.*;
import java.io.*;

public class CCG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < T; testcase++) {
            int N = Integer.parseInt(br.readLine());

            int[] parent = new int[N + 1];
            Circle[] list = new Circle[N + 1];

            for (int c = 1; c <= N; c++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                list[c] = new Circle(x, y, r);
                parent[c] = c;
            }

            System.out.println(solution(parent, list));
        }
    }

    private static int solution(int[] parent, Circle[] list) {
        int N = parent.length - 1;

        int count = N;

        for (int cA = 1; cA < N; cA++) {
            Circle circleA = list[cA];
            for (int cB = cA + 1; cB <= N; cB++) {
                Circle circleB = list[cB];

                if (check(circleA, circleB) && union(parent, cA, cB)) {
                    count--;
                }
            }
        }

        return count;
    }

    private static boolean check(Circle a, Circle b) {
        int distX = a.x - b.x;
        int distY = a.y - b.y;
        int rSum = a.r + b.r;

        return distX * distX + distY * distY <= rSum * rSum;
    }

    private static boolean union(int[] parent, int a, int b) {
        int parentA = findParent(parent, a);
        int parentB = findParent(parent, b);

        if (parentA == parentB) {
            return false;
        }

        if (parentA < parentB) {
            parent[parentB] = parent[parentA];
        } else {
            parent[parentA] = parent[parentB];
        }
        
        return true;
    }

    private static int findParent(int[] parent, int a) {
        if (parent[a] != a) {
            parent[a] = findParent(parent, parent[a]);
        }
        return parent[a];
    }

    static class Circle {
        int x, y, r;

        public Circle(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }
}
