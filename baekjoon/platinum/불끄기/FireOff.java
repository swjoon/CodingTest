package baekjoon.platinum.불끄기;

import java.io.*;
import java.util.Arrays;

public class FireOff {
    static int[] X = { 0, 0, 0, 1, -1 };
    static int[] Y = { 0, 1, -1, 0, 0 };
    static boolean[][] map = new boolean[10][10];
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            String line = br.readLine();
            for (int j = 0; j < 10; j++) {
                map[i][j] = line.charAt(j) == 'O';
            }
        }

        // 첫 번째 행의 모든 조합을 시도
        for (int i = 0; i < (1 << 10); i++) {
            boolean[][] tempMap = new boolean[10][10];
            for (int a = 0; a < 10; a++) {
                tempMap[a] = Arrays.copyOf(map[a], 10);
            }
            
            int clicks = 0;

            // 첫 번째 행 클릭 설정
            for (int j = 0; j < 10; j++) {
                if ((i & (1 << j)) != 0) {
                    toggle(tempMap, 0, j);
                    clicks++;
                }
            }

            // 나머지 행들에 대해 처리
            for (int y = 1; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    if (tempMap[y - 1][x]) {
                        toggle(tempMap, y, x);
                        clicks++;
                    }
                }
            }

            // 마지막 행이 모두 꺼졌는지 확인
            boolean allOff = true;
            for (int x = 0; x < 10; x++) {
                if (tempMap[9][x]) {
                    allOff = false;
                    break;
                }
            }

            if (allOff) {
                result = Math.min(result, clicks);
            }
        }

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    static void toggle(boolean[][] map, int y, int x) {
        for (int i = 0; i < 5; i++) {
            int newX = x + X[i];
            int newY = y + Y[i];
            if (newX >= 0 && newX < 10 && newY >= 0 && newY < 10) {
                map[newY][newX] = !map[newY][newX];
            }
        }
    }
}
