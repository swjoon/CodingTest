package 등굣길;

public class School {
    public static void main(String[] args) {
        solution(4, 3, new int[][] { { 2, 2 } });
    }

    public static int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] water = new int[n][m];
        int[][] map = new int[n][m];
        map[0][0] = 1;
        for (int i = 0; i < puddles.length; i++) {
            water[puddles[i][1] - 1][puddles[i][0] - 1] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (water[i][0] == 1)
                break;
            map[i][0] = 1;
        }
        for (int i = 0; i < m; i++) {
            if (water[0][i] == 1)
                break;
            map[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (water[i][j] != 1)
                    map[i][j] = (map[i - 1][j] + map[i][j - 1]) % 1000000007;
            }
        }
        answer = map[n - 1][m - 1];

        return answer;
    }
}
