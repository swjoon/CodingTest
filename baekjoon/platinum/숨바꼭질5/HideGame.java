package baekjoon.platinum.숨바꼭질5;

import java.util.*;
import java.io.*;

public class HideGame {
    private static final int MAX_L = 500000;
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int ob = Integer.parseInt(st.nextToken());
        int yb = Integer.parseInt(st.nextToken());

        int ans = 0;

        if (yb != ob) {
            int[] ybPosition = new int[MAX_L + 1];

            Arrays.fill(ybPosition, -1);

            int maxtime = 0;

            for (int i = 0; i < 2000; i++) {
                yb += i;

                if (yb > MAX_L) {
                    break;
                }

                maxtime = i;

                ybPosition[yb] = i;
            }

            ans = findYB(ob, maxtime, ybPosition);
        }

        System.out.println(ans);

        br.close();
    }

    private static int findYB(int ob, int maxtime, int[] ybPosition) {
        Deque<Point> queue = new ArrayDeque<>();

        boolean[][] visited = new boolean[MAX_L + 1][2];

        queue.add(new Point(ob, 0));

        int time = INF;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            if (now.cnt > maxtime || now.cnt > time) {
                break;
            }

            for (int i = -1; i < 2; i++) {
                int next = i == 0 ? now.l * 2 : now.l + i;
                int nextcnt = now.cnt + 1;

                if (next > MAX_L || next < 0 || visited[next][nextcnt % 2]) {
                    continue;
                }

                visited[next][nextcnt % 2] = true;

                if (ybPosition[next] == nextcnt) {
                    return Math.min(time, nextcnt);
                }

                if (ybPosition[next] > nextcnt && (ybPosition[next] % 2) == (nextcnt % 2)) {
                    time = Math.min(ybPosition[next], time);
                }

                queue.add(new Point(next, nextcnt));
            }
        }

        return time == INF ? -1 : time;
    }
}

class Point {
    int l;
    int cnt;

    public Point(int l, int cnt) {
        this.l = l;
        this.cnt = cnt;
    }
}