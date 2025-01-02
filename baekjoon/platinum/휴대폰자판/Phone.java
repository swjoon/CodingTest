package baekjoon.platinum.휴대폰자판;

import java.util.*;
import java.io.*;

public class Phone {
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String in = null;

        while ((in = br.readLine()) != null) {
            int N = Integer.parseInt(in);

            HashMap<String, Set<String>> map = new HashMap<>();
            Set<String> start = new HashSet<>();

            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split("");

                start.add(input[0]);

                StringBuilder sb = new StringBuilder();

                sb.append(input[0]);

                map.putIfAbsent(sb.toString(), new HashSet<>());

                for (int index = 1; index < input.length; index++) {
                    String now = sb.toString();
                    sb.append(input[index]);
                    map.putIfAbsent(now, new HashSet<>());
                    map.get(now).add(sb.toString());
                }
                map.putIfAbsent(sb.toString(), new HashSet<>());
                map.get(sb.toString()).add(".");
            }

            for (String s : start) {
                dfs(s, 1, map);
            }

            System.out.printf("%.2f", (float) ans / N);

            ans = 0;
        }

    }

    private static void dfs(String now, int count, HashMap<String, Set<String>> map) {

        if (map.get(now) == null) {
            ans += count;
            return;
        }

        int size = map.get(now).size();

        for (String s : map.get(now)) {
            if (size > 1) {
                if (s.equals(".")) {
                    ans += count;
                    continue;
                }
                count++;
                dfs(s, count, map);
                count--;
                continue;
            }
            dfs(s, count, map);
        }
    }
}
