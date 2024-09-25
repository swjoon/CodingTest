package baekjoon.gold.벽부수고이동하기4;

import java.util.*;
import java.io.*;

public class Wall {
    static int N, M;
    static int count;
    static char[][] map;
    static int[][] newMap;
    static int[] dirX = { 1, -1, 0, 0 };
    static int[] dirY = { 0, 0, 1, -1 };
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        newMap = new int[N][M];
        visited = new boolean[N][M];

        for (int y = 0; y < N; y++) {
            map[y] = br.readLine().toCharArray();
        }

        // 출력을 위한 맵
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                switch (map[y][x]) {
                    case '1':
                        newMap[y][x] = 1;
                        break;
                    default:
                        newMap[y][x] = 0;
                        break;
                }
            }
        }

        // 이동가능한 구역별 탐색
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (visited[y][x] || map[y][x] == '1') {
                    continue;
                }
                visited[y][x] = true;
                solution(x, y);
            }
        }

        for (int y = 0; y < N; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < M; x++) {
                sb.append(newMap[y][x] % 10);
            }
            System.out.println(sb.toString());
        }

    }

    static void solution(int x, int y) {
        // 구역별 인접 벽 중복 저장 방지
        Set<List<Integer>> set = new HashSet<>();
        count = 1;
        DFS(x, y, set);
        for (List<Integer> wall : set) {
            newMap[wall.get(1)][wall.get(0)] += count;
        }
    }

    static void DFS(int x, int y, Set<List<Integer>> set) {

        for (int i = 0; i < 4; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];

            if (nextX < 0 || nextY < 0 || nextX >= M || nextY >= N || visited[nextY][nextX]) {
                continue;
            }

            if (map[nextY][nextX] == '1') {
                set.add(Arrays.asList(nextX, nextY));
                continue;
            }

            visited[nextY][nextX] = true;
            ++count;
            DFS(nextX, nextY, set);
        }
    }
}
