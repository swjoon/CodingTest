package baekjoon.gold.Guess;

import java.util.*;
import java.io.*;

public class Guess {
    private static int N;
    private static boolean isEnd;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        char[][] signArray = new char[N][N];

        int[] number = new int[N];
        int[] sum = new int[N + 1];

        char[] input = br.readLine().toCharArray();

        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                signArray[i][j] = input[count++];
            }
        }

        dfs(1, number, sum, signArray);

        StringBuffer sb = new StringBuffer();

        for(int num : number){
            sb.append(num).append(" ");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void dfs(int layer, int[] number, int[] sum, char[][] signArray) {

        if (layer > N) {
            isEnd = true;
            return;
        }

        int add = checkSign(signArray[layer - 1][layer - 1]);

        int now = 0;

        boolean con = false;

        for (int i = 1; i <= (add == 0 ? 1 : 10); i++) {
            now += add;
            number[layer - 1] = now;

            sum[layer] = sum[layer - 1] + number[layer - 1];

            for (int j = 0; j < layer; j++) {
                if (!check(sum[layer] - sum[j], checkSign(signArray[j][layer - 1]))) {
                    con = true;
                    break;
                }
            }

            if (con) {
                con = false;
                continue;
            }

            dfs(layer + 1, number, sum, signArray);

            if (isEnd) {
                return;
            }
        }
    }

    private static boolean check(int num, int sign) {
        return (sign == 0 && num == 0) || (sign > 0 && num > 0) || (sign < 0 && num < 0);
    }

    private static int checkSign(final char sign) {
        switch (sign) {
            case '+':
                return 1;
            case '-':
                return -1;
            default:
                return 0;
        }

    }
}
