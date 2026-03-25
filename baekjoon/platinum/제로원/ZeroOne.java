package baekjoon.platinum.제로원;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class ZeroOne {

    private static final String ANSWER = "BRAK";

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int T = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder();

            for (int testCase = 0; testCase < T; testCase++) {

                String answer = solution(Integer.parseInt(br.readLine()));

                sb.append(answer).append("\n");
            }

            System.out.println(sb.toString());

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    private static String solution(int N) {
        Queue<Integer> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[N + 1];

        int[][] info = new int[N + 1][2];

        queue.add(1);

        visited[1] = true;

        while (!queue.isEmpty()) {
            int num = queue.poll();

            if (num == 0) {
                return findAnswer(info);
            }

            for (int i = 0; i < 2; i++) {
                int nextNum = num * 10 + i;
                nextNum %= N;

                if (visited[nextNum]) {
                    continue;
                }

                visited[nextNum] = true;
                info[nextNum][0] = num;
                info[nextNum][1] = i;

                queue.add(nextNum);
            }
        }

        return ANSWER;
    }

    private static String findAnswer(int[][] info) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i != 1; i = info[i][0]) {
            sb.append(info[i][1]);
        }

        sb.append(1);

        return sb.reverse().toString();
    }
}
