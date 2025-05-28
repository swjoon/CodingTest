package baekjoon.platinum.오일러회로;

import java.util.*;
import java.io.*;

public class Euler {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Map<Integer, Integer>[] routeMaps = new HashMap[N + 1];

        for (int i = 1; i <= N; i++) {
            routeMaps[i] = new HashMap<>();
        }

        int[] lineInfo = new int[N + 1];

        StringTokenizer st;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < i; j++) {
                int line = Integer.parseInt(st.nextToken());

                if (line == 0)
                    continue;

                lineInfo[i] += line;
                lineInfo[j] += line;

                routeMaps[i].put(j, line);
                routeMaps[j].put(i, line);
            }
        }

        if (!checkEuler(lineInfo)) {
            System.out.println(-1);

            br.close();
            return;
        }

        List<Integer> route = findRoute(lineInfo, routeMaps);

        StringBuilder sb = new StringBuilder();

        for (int line : route) {
            sb.append(line).append(" ");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static List<Integer> findRoute(int[] lineInfo, Map<Integer, Integer>[] routeMaps) {
        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> circuit = new ArrayList<>();
        stack.push(1);

        while (!stack.isEmpty()) {
            int now = stack.peek();
            Map<Integer, Integer> route = routeMaps[now];

            if (route.isEmpty()) {
                circuit.add(now);
                stack.pop();
            } else {
                Iterator<Map.Entry<Integer, Integer>> routeInfo = route.entrySet().iterator();

                Map.Entry<Integer, Integer> next = routeInfo.next();

                int nextLine = next.getKey();
                int remain = next.getValue();

                if (remain == 1) {
                    routeInfo.remove();
                } else {
                    next.setValue(remain - 1);
                }

                Map<Integer, Integer> r = routeMaps[nextLine];

                if (remain == 1) {
                    r.remove(now);
                } else {
                    r.put(now, remain - 1);
                }

                stack.push(nextLine);
            }
        }

        Collections.reverse(circuit);

        return circuit;
    }

    private static boolean checkEuler(int[] lineInfo) {
        int size = lineInfo.length;

        for (int i = 1; i < size; i++) {
            if (lineInfo[i] % 2 == 1) {
                return false;
            }
        }

        return true;
    }
}