package baekjoon.platinum.다이아몬드광산;

import java.util.*;
import java.io.*;

public class Diamond {
    static int X, Y;
    static int[][] map;
    static int[][][] DP;
    static int[] dirX = { -1, 1 };
    static int[] dirY = { -1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new int[Y][X];
        DP = new int[Y][X][2];

        for (int y = 0; y < Y; y++) {
            String[] input = br.readLine().split("");
            for (int x = 0; x < X; x++) {
                map[y][x] = Integer.parseInt(input[x]);
            }
        }

        for (int i = 0; i < 2; i++) {
            sideLength(i);
        }

        System.out.println(findMaxDiamond());
    }

    // 변의 길이 구하기 ( 왼쪽 사선 type = 1, 우측 사선 type = 2 )
    // 각 point는 다이아의 하단 꼭지점으로 가정.
    public static void sideLength(int type) {
        for (int x = 0; x < X; x++) {
            DP[0][x][type] = map[0][x];
        }

        for (int y = 1; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                if (map[y][x] == 0) {
                    DP[y][x][type] = 0;
                    continue;
                }
                int nextX = x + dirX[type];
                int nextY = y + dirY[type];
                if (nextX < 0 || nextY < 0 || nextX >= X || nextY >= Y) {
                    DP[y][x][type] = map[y][x];
                    continue;
                }

                DP[y][x][type] = map[y][x] + DP[nextY][nextX][type];
            }
        }
    }

    // 가장 큰 다이아 찾기
    public static int findMaxDiamond() {
        int maxValue = 0;
        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                int leftSide = DP[y][x][0];
                int rightSide = DP[y][x][1];
                int min = Math.min(leftSide, rightSide);

                // "\/" 다이아 하단
                if (min == 0 || min <= maxValue) {
                    continue;
                } else if (min == 1) {
                    maxValue = min;
                    continue;
                }

                for (int i = maxValue; i < min; i++) {
                    // "/" 다이아 왼쪽 상단
                    int leftToRight = DP[y + dirY[0] * (i)][x + dirX[0] * (i)][1];
                    // "\" 다이아 오른쪾 상단
                    int rightToLeft = DP[y + dirY[1] * (i)][x + dirX[1] * (i)][0];

                    // 다이아 완성 가능 여부 판단
                    if (Math.min(leftToRight, rightToLeft) >= i + 1) {
                        maxValue = i + 1;
                    }
                }
            }
        }
        return maxValue;
    }
}

// INPUT
// 0 1 1 0 0
// 0 1 0 1 1
// 1 1 1 1 1
// 0 1 1 1 1
// 1 1 1 1 1

// LEFT RIGHT
// 0 1 1 0 0 0 1 1 0 0
// 0 1 0 2 1 0 2 0 1 1
// 1 1 2 1 3 3 1 2 2 1
// 0 2 2 3 2 0 3 3 2 1
// 1 1 3 3 4 4 4 3 2 1

// OUTPUT
// 0 1 1 0 0
// 0 1 0 1 1
// 1 1 2 1 1
// 0 2 1 2 1
// 1 1 3 2 1

// 알고리즘 좌측 변 우측 변 PREFIX 2 * N
// 내려오면서 계산 N & 제일 큰 수 저장
// 예상 복잡도 3 * N;
