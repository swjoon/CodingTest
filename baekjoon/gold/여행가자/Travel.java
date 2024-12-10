package baekjoon.gold.여행가자;

import java.util.*;
import java.io.*;

public class Travel {
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, List<Integer>> route = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            route.put(i, new ArrayList<>());
            for (int city = 1; city <= N; city++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    route.get(i).add(city);
                }
            }
        }

        boolean[] visited = new boolean[N + 1];

        List<Integer> possibleRoute = new ArrayList<>();

        st = new StringTokenizer(br.readLine());


        int startPoint = Integer.parseInt(st.nextToken());

        visited[startPoint] = true;
        possibleRoute.add(startPoint);

        DFS(startPoint, visited, possibleRoute, route);

        for(int i = 0; i < M-1; i++){
            int point = Integer.parseInt(st.nextToken());
            if(!check(point, possibleRoute)){
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    private static void DFS(int Point, boolean[] visited, List<Integer> possibleRoute,
            Map<Integer, List<Integer>> route) {

        for (int newPoint : route.get(Point)) {
            if (!visited[newPoint]) {
                visited[newPoint] = true;
                possibleRoute.add(newPoint);
                DFS(newPoint, visited, possibleRoute, route);
            }
        }
    }

    private static boolean check(int point, List<Integer> possibleRoute) {
        for (int possiblePoint : possibleRoute) {
            if (point == possiblePoint) {
                return true;
            }
        }
        return false;
    }
}
