import java.util.*;

public class change {

    public static void main(String[] args) {
        System.out.println(solution(10, 40, 5));
        System.out.println(solution(10, 40, 30));
        System.out.println(solution(2, 5, 4));
    }

    public static int solution(int x, int y, int n) {
        return bfs(x, y, n);
    }

    public static int bfs(int x, int y, int n) {
        Queue<Point> queue = new LinkedList<Point>();
        boolean[] visited = new boolean[y+1]; // 중복체크로 효율성 ↑
        queue.add(new Point(x, 0));

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int nowx = now.x;
            if (nowx == y)
                return now.cnt;

            if (nowx + n <= y && !visited[nowx + n]){
                queue.add(new Point(nowx + n, now.cnt + 1));
                visited[nowx + n] = true;
            }
            if (nowx * 2 <= y && !visited[nowx * 2]){
                queue.add(new Point(nowx * 2, now.cnt + 1));
                visited[nowx * 2] = true;
            }
            if (nowx * 3 <= y && !visited[nowx * 3]){
                queue.add(new Point(nowx * 3, now.cnt + 1));
                visited[nowx * 3] = true;
            }
        }

        return -1;
    }

}

// 값 , 횟수
class Point {
    int x;
    int cnt;

    Point(int x, int cnt) {
        this.x = x;
        this.cnt = cnt;
    }
}

// 시간초과
// public static int bfs(int x, int y, int n){
// Queue<Integer> queue = new LinkedList<Integer>();
// int result = 0;

// queue.add(x);

// while(!queue.isEmpty()){
// int[] now = new int[queue.size()];
// for(int i = 0; i < now.length; i++){
// now[i] = queue.poll();
// if(now[i] == y) return result;
// }
// for(int i = 0; i < now.length; i++){
// int testnow = now[i];
// if(testnow + n <= y) queue.add(testnow + n);
// if(testnow * 2 <= y) queue.add(testnow * 2);
// if(testnow * 3 <= y) queue.add(testnow * 3);
// }
// result++;
// }

// return -1;
// }
// }
