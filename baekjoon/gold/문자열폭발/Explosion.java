package baekjoon.gold.문자열폭발;

import java.util.*;
import java.io.*;

public class Explosion {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        Deque<Character> charList = new ArrayDeque<>();

        for (char c : input) {
            charList.add(c);
        }

        String trigger = br.readLine();
        char[] triggerList = trigger.toCharArray();

        StringBuilder sb = new StringBuilder();

        while (!charList.isEmpty()) {
            char character = charList.peek();
            if (character == triggerList[0]) {
                sb.append(triggerCheck(trigger, triggerList, charList));
            } else {
                sb.append(charList.poll());
            }
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }

    static String triggerCheck(String trigger, char[] triggerList, Deque<Character> charList) {
        StringBuilder sb = new StringBuilder();
        sb.append(charList.poll());

        int now = 1;

        while (!charList.isEmpty()) {
            char character = charList.peek();

            if (now >= trigger.length()) {
                return "";
            }

            if (character == triggerList[0]) {
                String check = triggerCheck(trigger, triggerList, charList);
                if (check.length() != 0) {
                    sb.append(check);
                    return sb.toString();
                }
                continue;
            }

            if (character == triggerList[now++]) {
                sb.append(charList.poll());
                continue;
            }

            sb.append(charList.poll());
            break;
        }

        if (trigger.equals(sb.toString())) {
            return "";
        }

        return sb.toString();
    }
}