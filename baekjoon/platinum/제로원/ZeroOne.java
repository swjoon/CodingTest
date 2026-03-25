package baekjoon.platinum.제로원;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class ZeroOne {

    private static final int MAXLENGTH = 100;
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
        Queue<Info> queue = new LinkedList<>();

        queue.add(new Info(N, ""));

        while (!queue.isEmpty()) {
            Info info = queue.poll();

            for (int i = 0; i < 9; i++) {
                int mN = (info.n + N * i);

                int first = mN % 10;

                if (first == 1 || first == 0) {
                    StringBuilder sb = new StringBuilder(info.sb);

                    int nextN = mN / 10;
                    sb.append(first);

                    if (sb.length() > MAXLENGTH) {
                        continue;
                    }

                    if (nextN == 0 && sb.length() <= MAXLENGTH) {
                        return sb.reverse().toString();
                    }

                    queue.add(new Info(nextN, sb.toString()));
                }
            }
        }

        return ANSWER;
    }
}

class Info {

    int n;
    StringBuilder sb = new StringBuilder();

    public Info(int n, String s) {
        this.n = n;
        sb.append(s);
    }
}
