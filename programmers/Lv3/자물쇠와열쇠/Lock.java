package 자물쇠와열쇠;

public class Lock {
    public static void main(String[] args) {
        solution(new int[][] { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } },
                new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } });
    }

    static int N, M;

    public static boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;

        M = key.length;
        N = lock.length;

        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {

            }
        }

        return answer;
    }
}
