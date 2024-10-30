package baekjoon.gold.Easy2048;

import java.util.*;
import java.io.*;

public class Easy {
    static int N;
    static int Max;
    static int[][][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N][6];

        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x][0] = Integer.parseInt(st.nextToken());
            }
        }
        
        DFS(0);

        System.out.println(Max);
    }

    public static void DFS(int dep) {
        if (dep == 5) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    Max = Math.max(Max, map[y][x][dep]);
                }
            }
            return;
        }
        // 상하좌우
        for (int i = 1; i <= 4; i++) {
            move(dep, i);
            DFS(dep + 1);
        }
    }

    public static void move(int dep, int type) {
        // 이전 회차값은 보존
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                map[y][x][dep + 1] = map[y][x][dep];
            }
        }

        Queue<Integer> q = new LinkedList<>();
        // 상 하 좌 우 순서
        switch (type) {
            case 1:
                for (int x = 0; x < N; x++) {
                    // 순서대로 합쳐지므로 queue를 사용
                    for (int y = 0; y < N; y++) {
                        if (map[y][x][dep + 1] != 0) {
                            q.add(map[y][x][dep + 1]);
                            map[y][x][dep + 1] = 0;
                        }
                    }
                    // 채워야하는 칸 
                    int i = 0;
    
                    while (!q.isEmpty()) {
                        int num = q.poll();
                        // 빈 공간일 경우
                        if (map[i][x][dep + 1] == 0) {
                            map[i][x][dep + 1] = num;
                            continue;
                        }
                        // 같은 숫자를 만났을 경우
                        if (map[i][x][dep + 1] == num) {
                            map[i][x][dep + 1] *= 2;
                            i++;
                            continue;
                        }
                        // 둘다 아닌 경우
                        map[++i][x][dep + 1] = num;
                    }
                }
                break;
            case 2:
                for (int x = 0; x < N; x++) {
                    for (int y = N - 1; y >= 0; y--) {
                        if (map[y][x][dep + 1] != 0) {
                            q.add(map[y][x][dep + 1]);
                            map[y][x][dep + 1] = 0;
                        }
                    }
                    int i = N - 1;
                    while (!q.isEmpty()) {
                        int num = q.poll();
                        if (map[i][x][dep + 1] == 0) {
                            map[i][x][dep + 1] = num;
                            continue;
                        }
                        if (map[i][x][dep + 1] == num) {
                            map[i][x][dep + 1] *= 2;
                            i--;
                            continue;
                        }
                        map[--i][x][dep + 1] = num;
                    }
                }
                break;
            case 3:
                for (int y = 0; y < N; y++) {
                    for (int x = 0; x < N; x++) {
                        if (map[y][x][dep + 1] != 0) {
                            q.add(map[y][x][dep + 1]);
                            map[y][x][dep + 1] = 0;
                        }
                    }
                    int i = 0;
                    while (!q.isEmpty()) {
                        int num = q.poll();
                        if (map[y][i][dep + 1] == 0) {
                            map[y][i][dep + 1] = num;
                            continue;
                        }
                        if (map[y][i][dep + 1] == num) {
                            map[y][i][dep + 1] *= 2;
                            i++;
                            continue;
                        }
                        map[y][++i][dep + 1] = num;
                    }
                }
                break;
            default:
                for (int y = 0; y < N; y++) {
                    for (int x = N - 1; x >= 0; x--) {
                        if (map[y][x][dep + 1] != 0) {
                            q.add(map[y][x][dep + 1]);
                            map[y][x][dep + 1] = 0;
                        }
                    }
                    int i = N-1;
                    while (!q.isEmpty()) {
                        int num = q.poll();
                        if (map[y][i][dep + 1] == 0) {
                            map[y][i][dep + 1] = num;
                            continue;
                        }
                        if (map[y][i][dep + 1] == num) {
                            map[y][i][dep + 1] *= 2;
                            i--;
                            continue;
                        }
                        map[y][--i][dep + 1] = num;
                    }
                }
                break;
        }
    }
}