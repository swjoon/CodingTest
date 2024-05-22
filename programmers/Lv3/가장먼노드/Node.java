package 가장먼노드;

import java.util.*;

public class Node {
    public static void main(String[] args) {
        solution(6, new int[][] { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } });
    }

    public static int solution(int n, int[][] edge) {
        int answer = 0;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < edge.length; i++) {
            int first = edge[i][0];
            int last = edge[i][1];
            if (map.get(first) == null)
                map.put(first, new ArrayList<>());
            if (map.get(last) == null)
                map.put(last, new ArrayList<>());
            map.get(first).add(last);
            map.get(last).add(first);
        }
        int max = BFS(n, map, count);

        for (int i = 1; i < count.size()+1; i++){
            if(max == count.get(i)) answer++;
        }

        return answer;
    }

    public static int BFS(int n, Map<Integer, ArrayList<Integer>> map, Map<Integer, Integer> count) {
        boolean[] vistied = new boolean[n];
        Queue<Point> queue = new LinkedList<>();
        int max = 1;
        vistied[0] = true;
        queue.add(new Point(1, 1));

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int now = point.now;
            int cnt = point.cnt;
            max = (cnt > max) ? cnt : max;
            count.put(now, cnt);
            ArrayList<Integer> list = new ArrayList<>(map.get(now));
            for (int i = 0; i < list.size(); i++) {
                if (vistied[list.get(i) - 1])
                    continue;
                queue.add(new Point(list.get(i), cnt + 1));
                vistied[list.get(i) - 1] = true;
            }
        }
        return max;
    }
}

class Point {
    int now;
    int cnt;

    Point(int now, int cnt) {
        this.now = now;
        this.cnt = cnt;
    }
}
