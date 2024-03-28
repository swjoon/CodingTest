package 미로탈출;

import java.util.*;

public class maze {
    static int Lx;
    static int Ly;
    public static void main(String[] args) {

        System.out.println(solution(new String[] { "SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE" }));
        System.out.println(solution(new String[] { "LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO" }));

    }

    public static int solution(String[] maps) {
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length();
        String[][] newmap = new String[n][m];

        for (int i = 0; i < maps.length; i++) {
            newmap[i] = maps[i].split("");
        }

        int startX = 0;
        int startY = 0;

        // 시작지점 위치 찾기
        outerLoop: for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (newmap[i][j].equals("S")) {
                    startX = j;
                    startY = i;
                    break outerLoop;
                }
            }
        }
        System.out.println(startX + " " + startY);

        //레버까지 bfs
        answer = bfs(startX, startY, n, m, newmap, "L");
        if(answer != -1){
            // 레버에 도착한 이후 출구까지 bfs
            int second = bfs(Lx, Ly, n, m, newmap, "E");
            if(second != -1){
                answer += second;
            }else{
                answer = -1;
            }
        }
        return answer;
    }

    // 최단거리를 위한 bfs 알고리즘
    public static int bfs(int x, int y, int n, int m, String[][] newmaps, String finish) {
        Queue<Point> queue = new LinkedList<Point>();
        boolean[][] visited = new boolean[n][m];
        int[] dx = { 0, 0, -1, 1 };
        int[] dy = { -1, 1, 0, 0 };

        queue.add(new Point(y, x, 0));

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            if(newmaps[now.y][now.x].equals(finish)){
                Lx = now.x;
                Ly = now.y;              
                return now.cnt;
            }
            for(int i = 0; i < 4; i++){
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if( nextX < 0 || nextY < 0 || nextX >= m || nextY >= n || visited[nextY][nextX]) continue;
                if( newmaps[nextY][nextX].equals("X")) continue;

                visited[nextY][nextX] = true;
                queue.add(new Point(nextY, nextX, now.cnt+1));
            }
        }
        return -1;
    }
}

// 현재위치
class Point {

    int y;
    int x;
    int cnt;

    Point(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}
