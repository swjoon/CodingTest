package 아이템줍기;

import java.util.*;

public class Item {
    public static void main(String[] args) {
        solution(new int[][] { { 1, 1, 7, 4 }, { 3, 2, 5, 5 }, { 4, 3, 6, 9 }, { 2, 6, 8, 8 } }, 1, 3, 7, 8);
        // solution(new int[][] { { 1, 1, 8, 4 }, { 2, 2, 4, 9 }, { 3, 6, 9, 8 }, { 6,
        // 3, 7, 7 } }, 9, 7, 6, 1);
    }

    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        int[][] map = new int[101][101];
        boolean[][] visited = new boolean[101][101];
        // 맵작성
        Map(map, rectangle);
        // 정답추출
        answer = BFS(map, visited, characterX * 2, characterY * 2, itemX * 2, itemY * 2);

        return answer;
    }

    // 처음 방문한 지점인지, 해당 맵이 길이 맞는지 판단 후 진행. 아이템 위치에 도달시 진행 횟수 반환. 진행횟수 / 2를 해주는 이유 - > 이동 단위가 1이 아닌 0.5
    public static int BFS(int[][] map, boolean[][] visited, int characterX, int characterY, int itemX, int itemY) {
        int[] x = { 0, 0, 1, -1 };
        int[] y = { 1, -1, 0, 0 };

        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(characterX, characterY, 0));

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (visited[p.y][p.x] == true || map[p.y][p.x] != 1)
                continue;
            if (p.x == itemX && p.y == itemY)
                return p.cnt/2;
            visited[p.y][p.x] = true;
            for (int i = 0; i < 4; i++) {
                if(p.x + x[i] > 100 || p.x + x[i] < 0 || p.y + y[i] > 100 || p.y + y[i] < 0) continue;
                queue.add(new Point(p.x + x[i], p.y + y[i], p.cnt + 1));
            }
        }
        return 0;
    }

    // 맵만들기 -> 1 단위로하면 안보이는 부분이 생기므로 0.5단위로 측정하기 위해 2배 사이즈로 작성 -> 최대 4개의 사각형이 합쳐진 그림에서 테두리를 추출.
    public static void Map(int[][] map, int[][] rectangles) {
        for (int[] rectangle : rectangles) {
            for (int y = rectangle[1] * 2; y <= rectangle[3] * 2; y++) {
                for (int x = rectangle[0] * 2; x <= rectangle[2] * 2; x++) {
                    if (map[y][x] == 0)
                        map[y][x]++;
                }
            }
        }
        for (int[] rectangle : rectangles) {
            for (int y = rectangle[1] * 2 + 1; y < rectangle[3] * 2; y++) {
                for (int x = rectangle[0] * 2 + 1; x < rectangle[2] * 2; x++) {
                    if (map[y][x] == 1)
                        map[y][x]--;
                }
            }
        }
    }
}

// BFS 로 풀기 위해 현재 위치와 움직인 횟수를 저장.
class Point {
    int x;
    int y;
    int cnt;

    Point(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}
