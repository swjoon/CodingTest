package baekjoon.gold.감시;

import java.util.*;
import java.io.*;

public class Monitor {
    static int N, M;
    static int[][] map;
    static int ans = 64;
    static int[] dirX = { 0, 1, 0, -1 };
    static int[] dirY = { -1, 0, 1, 0 };
    static List<Point> cctv = new ArrayList<>();
    static List<Point> list = new ArrayList<>();

    // cctv 정보값 클래스 정의
    static class Point {
        int x, y;
        int dir;
        int type;

        Point(int x, int y, int type, int dir) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] != 6 && map[y][x] > 0) {
                    cctv.add(new Point(x, y, map[y][x], 0));
                }
            }
        }

        // 감시 면적이 넓은 타입부터 탐색
        cctv.sort((o1, o2) -> o2.type - o1.type);

        DFS(0);

        System.out.println(ans);
    }

    // 백트래킹을 위한 DFS
    static void DFS(int seq) {

        // 모든 CCTV를 탐색했을시 사각지대 확인
        if (seq >= cctv.size()) {
            int[][] newMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                newMap[i] = Arrays.copyOf(map[i], M);
            }

            for (Point p : list) {
                BFS(p, newMap);
            }

            ans = Math.min(ans, check(newMap));
            return;
        }

        Point now = cctv.get(seq);

        // 5타입은 방향이 따로 없으므로 list에 추가안함
        if (now.type == 5) {
            BFS(now, map);
            DFS(seq + 1);
        } else {
            // 백트래킹
            for (int i = 0; i < 4; i++) {
                list.add(new Point(now.x, now.y, now.type, i));
                DFS(seq + 1);
                list.remove(list.size() - 1);
            }
        }
        return;
    }

    static void BFS(Point point, int[][] newMap) {
        int type = point.type;
        int dir = point.dir;

        for (int i = 0; i < 4; i++) {
            int nextx = point.x;
            int nexty = point.y;

            // 각 타입별 감시구역
            if (type == 1 && dir != i) {
                continue;
            }
            if (type == 2 && dir != i && dir != (i + 2) % 4) {
                continue;
            }
            if (type == 3 && dir != i && dir != (i + 1) % 4) {
                continue;
            }
            if (type == 4 && dir == i) {
                continue;
            }

            // 맵에 감시구역 입력
            while (true) {
                nextx += dirX[i];
                nexty += dirY[i];
                if (nextx < 0 || nexty < 0 || nextx >= M || nexty >= N || newMap[nexty][nextx] == 6) {
                    break;
                }
                newMap[nexty][nextx] = 1;
            }
        }
    }

    // 사각지대 개수 파악
    static int check(int[][] newMap) {
        int count = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (newMap[y][x] == 0)
                    count++;
            }
        }
        return count;
    }
}
