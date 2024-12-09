package baekjoon.gold.문자열폭발;

import java.io.*;

public class Explosion {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String trigger = br.readLine();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));

            if (sb.length() >= trigger.length() && checkTrigger(sb, trigger)) {
                explosion(sb, trigger);
            }
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }

    private static boolean checkTrigger(StringBuilder sb, String trigger) {
        for (int i = 0; i < trigger.length(); i++) {
            if (sb.charAt(sb.length() - trigger.length() + i) != trigger.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static void explosion(StringBuilder sb, String trigger) {
        sb.delete(sb.length()-trigger.length(), sb.length());
    }
}