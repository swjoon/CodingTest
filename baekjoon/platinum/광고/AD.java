package baekjoon.platinum.광고;

import java.util.*;
import java.io.*;

public class AD {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split("");
        Queue<String> queue = new LinkedList<>(Arrays.asList(input));
        StringBuilder ad = new StringBuilder();

        int dup = 1;

        for (int i = 0; i < size; i++) {
            if (!input[i].equals(input[i + 1])) {
                break;
            }
            dup++;
        }

        String start = queue.poll();
        ad.append(start);

        int count = 0;

        while (!queue.isEmpty()) {
            // 현재까지 인정된 광고문자열 시작과 동일하다면 중복되는지 체크
            if (queue.peek().equals(ad.substring(0, 1))) {
                String str = check(queue, ad, dup);

                // 1. 문자열이 완벽히 일치 2. 문자열이 일치하지만 광고판 사이즈가 초과되어서 중간에 끊기는 경우
                if (str.length() == ad.length()) {
                    count++;
                } else {
                    String add = ad.toString();
                    for (int i = 0; i < count; i++) {
                        ad.append(add);
                    }
                    ad.append(str);
                    count = 0;
                }
                continue;
            }

            String st = queue.poll();
            String add = ad.toString();

            for (int i = 0; i < count; i++) {
                ad.append(add);
            }

            ad.append(st);
        }

        System.out.println(ad.length());
    }

    private static String check(Queue<String> queue, StringBuilder ad, int dup) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ad.length(); i++) {
            // 전부 일치하고 광고판의 사이즈를 초과
            if (queue.isEmpty()) {
                return ad.toString();
            }

            // 문자열 일치 확인
            if (queue.peek().equals(ad.substring(i, i + 1))) {
                sb.append(queue.poll());
                continue;
            }

            // 시작문자가 여러개 중복해서 나열되는 경우
            if (i == dup && queue.peek().equals(ad.substring(0, 1))) {
                String add = queue.poll();
                ad.append(add);
                sb.delete(0, 1);
                sb.append(add);
                i--;
                continue;
            }

            break;
        }

        return sb.toString();
    }
}