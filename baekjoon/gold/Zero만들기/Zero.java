package baekjoon.gold.Zero만들기;

import java.io.*;
import java.util.*;

public class Zero {
    static char[] type = { ' ', '+', '-' };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < T; testcase++) {
            int N = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            dfs(1, N, sb, answer);

            answer.append("\n");
        }

        System.out.println(answer.toString());
    }

    private static void dfs(int now, int end, StringBuilder sb, StringBuilder answer) {
        sb.append(now);

        if (now == end) {
            if (isZero(sb.toString())) {
                answer.append(sb.toString()).append("\n");
            }
            sb.deleteCharAt(sb.length() - 1);
            return;
        }

        for (int i = 0; i < 3; i++) {
            sb.append(type[i]);
            dfs(now + 1, end, sb, answer);
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.deleteCharAt(sb.length() - 1);
    }

    private static boolean isZero(String sb) {
        Deque<Integer> number = new ArrayDeque<>();
        Deque<Character> op = new ArrayDeque<>();

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '+' || sb.charAt(i) == '-') {
                op.add(sb.charAt(i));
                continue;
            }

            if (sb.charAt(i) == ' ') {
                number.add(number.pollLast() * 10 + Integer.parseInt(String.valueOf(sb.charAt(++i))));
                continue;
            }

            number.add(Integer.parseInt(String.valueOf(sb.charAt(i))));
        }

        int value = number.poll();

        while(!op.isEmpty()){
            char oper = op.poll();

            if(oper == '+'){
                value += number.poll();
                continue;
            }

            if(oper == '-'){
                value -= number.poll();
                continue;
            }
        }

        return value == 0;
    }
}
