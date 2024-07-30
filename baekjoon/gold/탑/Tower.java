package baekjoon.gold.탑;

import java.util.*;
import java.io.*;

public class Tower {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<int[]> stack = new Stack<>();
        int N = Integer.parseInt(br.readLine());
        int[] tower = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            tower[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            while (!stack.isEmpty() && stack.peek()[0] < tower[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                stack.push(new int[] { tower[i], i });
                sb.append(0).append(" ");
            }else{
                sb.append(stack.peek()[1]).append(" ");
                stack.push(new int[] { tower[i], i });
            }
        }
        System.out.println(sb.toString());
    }
}
