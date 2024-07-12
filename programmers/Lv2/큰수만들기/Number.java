package 큰수만들기;

import java.util.*;

public class Number {
    public static void main(String[] args) {
        solution("1924", 2);
        solution("1231234", 3);
        solution("4177252841", 4);
        solution("417725284111116", 8);
        solution("1199111", 6);
    }

    public static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        String[] list = number.split("");
        // List<String> list = new ArrayList<>(Arrays.asList(number.split("")));
        int now = 0;

        while (k > 0 || now < list.length - k) {
            int check = 0;
            if (now + k == list.length) {
                System.out.println(sb.toString());
                return sb.toString();
            }
            for (int i = now + 1; i <= now + k; i++) {
                if(list[now].equals("9")) break;
                if (Integer.parseInt(list[now]) < Integer.parseInt(list[i])) {
                    k-= i-now;
                    now = i;
                    check++;
                    break;
                }
            }

            if (check == 0){
                sb.append(list[now]);
                now++;
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
