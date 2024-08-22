package baekjoon.gold.아기상어;

import java.util.*;
import java.io.*;

public class Shark {
    static int N;
    static int fish;
    static babyshark now;
    static int[][] map;
    static int[] X = { 0, 0, -1, 1 };
    static int[] Y = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        fish = 0;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 9) {
                    map[y][x] = 0;
                    now = new babyshark(x, y, 0, 2);
                }
            }
        }

        int eat = 0;

        while (BFS(now)) {
            if (++eat == now.size) {
                eat = 0;
                now.size++;
            }
        }

        System.out.println(now.cnt);

    }

    static boolean BFS(babyshark shark) {
        boolean[][] visited = new boolean[N][N];
        Queue<babyshark> q = new LinkedList<>();

        PriorityQueue<babyshark> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.cnt != o2.cnt) {
                return o1.cnt - o2.cnt;
            }
            if (o1.y != o2.y) {
                return o1.y - o2.y;
            }
            return o1.x - o2.x;
        });

        visited[shark.y][shark.x] = true;
        q.add(shark);

        while (!q.isEmpty()) {
            babyshark s = q.poll();

            if (!pq.isEmpty() && pq.peek().cnt < s.cnt) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextx = s.x + X[i];
                int nexty = s.y + Y[i];
                int nextcnt = s.cnt + 1;
                if (nextx < 0 || nexty < 0 || nextx >= N || nexty >= N || visited[nexty][nextx]
                        || map[nexty][nextx] > s.size) {
                    continue;
                }

                visited[nexty][nextx] = true;

                if (map[nexty][nextx] > 0 && map[nexty][nextx] < shark.size) {
                    pq.add(new babyshark(nextx, nexty, nextcnt, s.size));
                } else {
                    q.add(new babyshark(nextx, nexty, nextcnt, s.size));
                }
            }
        }

        if (!pq.isEmpty()) {
            babyshark target = pq.poll();
            now = new babyshark(target.x, target.y, target.cnt, target.size);
            map[now.y][now.x] = 0;
            return true;
        }

        return false;
    }
}

class babyshark {
    int x, y;
    int cnt;
    int size;

    babyshark(int x, int y, int cnt, int size) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.size = size;
    }
}