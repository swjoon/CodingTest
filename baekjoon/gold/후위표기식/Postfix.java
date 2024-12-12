package baekjoon.gold.후위표기식;

import java.util.*;
import java.io.*;

public class Postfix {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Queue<String> inputData = new LinkedList<>(Arrays.asList(br.readLine().split("")));

        System.out.println(check(inputData));
    }

    private static String check(Queue<String> input) {
        StringBuilder sb = new StringBuilder();
        Stack<String> stack = new Stack<>();

        while (!input.isEmpty()) {
            String nowData = input.poll();

            if (nowData.equals("(")) {
                sb.append(check(input));

                if (input.isEmpty())
                    break;

                String nextData = input.peek();

                if (nextData.equals("+") || nextData.equals("-")) {
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                }
                continue;
            }

            if (nowData.equals(")")) {
                break;
            }

            if (nowData.equals("+") || nowData.equals("-")) {
                if (stack.isEmpty()) {
                    stack.add(nowData);
                    continue;
                }

                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }

                stack.add(nowData);
                continue;
            }

            if (nowData.equals("*") || nowData.equals("/")) {
                if (stack.isEmpty()) {
                    stack.add(nowData);
                    continue;
                }

                String beforeData = stack.peek();

                if (beforeData.equals("*") || beforeData.equals("/")) {
                    sb.append(stack.pop());
                }

                stack.add(nowData);
                continue;
            }

            sb.append(nowData);
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}