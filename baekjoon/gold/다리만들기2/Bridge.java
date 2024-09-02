package baekjoon.gold.다리만들기2;

import java.util.*;
import java.io.*;

public class Bridge {
    static int N, M;
    static int[][] map;
    static int[][] newmap;
    static int[] X = { 0, 0, -1, 1 };
    static int[] Y = { -1, 1, 0, 0 };
    static int number = 1;
    static int[][] cost;
    static Map<Integer, Set<Integer>> list = new HashMap<>();
    static int nowx, nowy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 맵 크기 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        newmap = new int[N][M];

        // 맵 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 맵 분류하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && newmap[i][j] == 0) {
                    BFS(j, i);
                    number++;
                }
            }
        }

        for (int i = 1; i < number; i++) {
            list.put(i, new HashSet<>());
        }

        cost = new int[number][number];
        for (int i = 0; i < number; i++) {
            Arrays.fill(cost[i], 12);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (newmap[i][j] != 0) {
                    nowx = j;
                    nowy = i;
                    for (int k = 0; k < 4; k++) {
                        FindCost(j, i, newmap[i][j], k);
                    }
                }
            }
        }

        List<int[]> bridge = new ArrayList<>();
        int[] parent = new int[number];
        for (int i = 0; i < number; i++) {
            parent[i] = i;
        }

        for (int i = 1; i < number; i++) {
            for (int num : list.get(i)) {
                if (num > i) {
                    bridge.add(new int[] { i, num, cost[i][num] });
                }
            }
        }

        bridge.sort((o1, o2) -> o1[2] - o2[2]);

        int bridgecost = 0;

        // 크루스칼 알고리즘
        for (int[] b : bridge) {
            if (find(parent, b[0]) != find(parent, b[1])) {
                bridgecost += b[2];
                union(parent, b[0], b[1]);
            }
        }

        // 부모노드 재정리 및 모든 노드 연결가능 여부 체크
        for (int i = 1; i < number; i++) {
            parent[i] = find(parent, i);
            if (parent[i] != 1) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(bridgecost);
    }

    static void union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if (x < y)
            parent[y] = x;
        else
            parent[x] = y;
    }

    static int find(int[] parent, int i) {
        if (parent[i] == i)
            return i;
        else
            return find(parent, parent[i]);
    }

    static void FindCost(int x, int y, int now, int dir) {
        int nextX = x + X[dir];
        int nextY = y + Y[dir];

        if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N || newmap[nextY][nextX] == now)
            return;

        if (newmap[nextY][nextX] != 0) {
            int cnt = Math.abs(y - nowy) + Math.abs(x - nowx);
            if (cnt > 1) {
                list.get(now).add(newmap[nextY][nextX]);
                cost[now][newmap[nextY][nextX]] = Math.min(cost[now][newmap[nextY][nextX]], cnt);
            }
            return;
        }

        FindCost(nextX, nextY, now, dir);
    }

    static void BFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] { x, y });

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            newmap[xy[1]][xy[0]] = number;
            for (int j = 0; j < 4; j++) {
                int nextX = xy[0] + X[j];
                int nextY = xy[1] + Y[j];
                if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N || map[nextY][nextX] != 1
                        || newmap[nextY][nextX] != 0)
                    continue;
                queue.add(new int[] { nextX, nextY });
            }
        }

    }
}

class Point {

    int x;
    int y;
    int cnt;
    int dir;

    Point(int x, int y, int cnt, int dir) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.dir = dir;
    }
}