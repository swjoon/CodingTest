package baekjoon.platinum.오일러회로;

import java.util.*;
import java.io.*;

public class EulerDFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int totalLine = 0;

        Map<Integer, Map<Integer, Integer>> routeMap = new HashMap<>();

        StringTokenizer st;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < i; j++) {
                int routeNum = Integer.parseInt(st.nextToken());

                totalLine += routeNum;

                routeMap.putIfAbsent(i, new HashMap<>());
                routeMap.putIfAbsent(j, new HashMap<>());

                routeMap.get(i).put(j, routeNum);
                routeMap.get(j).put(i, routeNum);
            }
        }

        StringBuilder route = new StringBuilder();

        route.append(1).append(" ");

        if (dfs(1, 1, totalLine, route, routeMap)) {
            System.out.println(route.toString());
        } else {
            System.out.println(-1);
        }

        br.close();
    }

    private static boolean dfs(int start, int now, int totalLine, StringBuilder route,
            Map<Integer, Map<Integer, Integer>> routeMap) {

        if (totalLine == 0 && start == now) {
            return true;
        }

        for (int next : routeMap.get(now).keySet()) {
            int remainLine = routeMap.get(now).get(next);

            if (remainLine == 0) {
                continue;
            }

            route.append(next).append(" ");

            routeMap.get(now).replace(next, remainLine - 1);
            routeMap.get(next).replace(now, remainLine - 1);

            if (dfs(start, next, totalLine - 1, route, routeMap)) {
                return true;
            }

            route.delete(route.length() - 2, route.length() - 1);

            routeMap.get(now).replace(next, remainLine + 1);
            routeMap.get(next).replace(now, remainLine + 1);
        }

        return false;
    }
}