package 자물쇠와열쇠;

public class Lock2 {
    public static void main(String[] args) {
        solution(new int[][] { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } },
                new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } });
    }

    static int N, M;

    public static boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;

        for (int direction = 0; direction < 4; ++direction) {
            if (direction != 0) { // 90도씩 회전
                key = rotate(key);

                for (int y = -(N - 1); y <= (N - 1); ++y) { // 시작점
                    for (int x = -(N - 1); x <= (N - 1); ++x) { // 시작점

                        if (chk(y, x, lock, key))
                            return true;

                    }
                }
            }
        }

        return false;
    }

    static boolean chk(int y, int x, int[][] lock, int[][] key) {
        // lock 돌기
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (i >= y + M || i < y || j < x || j >= x + M) {
                    if (lock[i][j] == 0)
                        return false;

                    continue;
                }

                if (lock[i][j] == key[i - y][j - x])
                    return false;
            }
        }

        return true;
    }

    static int[][] rotate(int[][] key) {
        int[][] new_key = new int[M][M];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < M; ++j) {
                new_key[i][j] = key[M - 1 - j][i];
            }
        }

        return new_key;
    }

}
