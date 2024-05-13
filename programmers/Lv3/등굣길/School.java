package 등굣길;

public class School {
    public static void main(String[] args) {
        solution(4, 3, new int[][] { { 1, 2 } });
    }

    static int sum = 0;

    public static int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] map = new int[n][m];
        for (int i = 0; i < puddles.length; i++) {
            map[puddles[i][0] - 1][puddles[i][1] - 1] = 1;
        }
        dfs(0, 0, map);
        answer = sum;
        return answer;
    }

    public static void dfs(int nowX, int nowY, int[][] map) {
        int[] x = new int[] { 0, 1 };
        int[] y = new int[] { 1, 0 };

        if (map[nowY][nowX] == 1)
            return;

        if (nowX == map[0].length - 1 && nowY == map.length - 1) {
            sum++;
            return;
        } else {
            for (int i = 0; i < 2; i++) {
                if (nowX + x[i] < map[0].length && nowY + y[i] < map.length) {
                    dfs(nowX + x[i], nowY + y[i], map);
                }
            }
        }
    }
}
