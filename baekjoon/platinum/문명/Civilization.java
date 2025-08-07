package baekjoon.platinum.문명;

import java.io.*;
import java.util.*;

public class Civilization {

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        int cNum = Integer.parseInt(st.nextToken());

        int[][] map = new int[size][size];
        int[][] type = new int[size][size];

        Deque<Point> queue = new ArrayDeque<>();

        for (int i = 1; i <= cNum; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            type[y][x] = i;

            queue.add(new Point(x, y, i, 0));
        }

        Map<Integer, Integer>[] nodeGraph = bfs(queue, map, type, size, cNum);

        int result = getMergeTime(cNum, size, nodeGraph);

        System.out.println(result);

        br.close();
    }

    private static Map<Integer, Integer>[] bfs(Deque<Point> queue, int[][] map, int[][] type, int size, int cNum) {
        // 문명간 연결 정보 그래프
        Map<Integer, Integer>[] graph = new HashMap[cNum + 1];

        for (int i = 1; i <= cNum; i++) {
            graph[i] = new HashMap<>();
        }

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= size || ny < 0 || ny >= size || type[ny][nx] == now.type) {
                    continue;
                }

                if (type[ny][nx] != 0) {
                    if (graph[now.type].containsKey(type[ny][nx])) {
                        continue;
                    }

                    graph[now.type].put(type[ny][nx], map[ny][nx]);
                    graph[type[ny][nx]].put(now.type, map[ny][nx]);
                    continue;
                }

                map[ny][nx] = now.cnt + 1;
                type[ny][nx] = now.type;

                queue.add(new Point(nx, ny, now.type, now.cnt + 1));
            }
        }

        return graph;
    }

    private static int getMergeTime(int cNum, int size, Map<Integer, Integer>[] graph) {
        PriorityQueue<GraphNode> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[cNum + 1];

        queue.add(new GraphNode(1, 0));

        int maxTime = 0;

        while (!queue.isEmpty()) {
            GraphNode now = queue.poll();

            if (visited[now.now]) {
                continue;
            }

            visited[now.now] = true;

            maxTime = Math.max(maxTime, now.time);

            for (Map.Entry<Integer, Integer> entry : graph[now.now].entrySet()) {
                int next = entry.getKey();
                int time = entry.getValue();

                queue.add(new GraphNode(next, time));
            }
        }

        return maxTime;
    }
}

class Point {

    int x, y, type, cnt;

    public Point(int x, int y, int type, int cnt) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.cnt = cnt;
    }
}

class GraphNode implements Comparable<GraphNode> {

    int now;
    int time;

    public GraphNode(int now, int time) {
        this.now = now;
        this.time = time;
    }

    @Override
    public int compareTo(GraphNode o) {
        return Integer.compare(this.time, o.time);
    }
}
