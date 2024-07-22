package baekjoon.gold.사회망서비스;

import java.io.*;
import java.util.*;

public class SNS {

    static int[] visited;
    static int[] EA;
    static int[][] DP;
    static HashMap<Integer, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;

        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<Integer>());
        }

        visited = new int[N + 1];
        EA = new int[N + 1];
        DP = new int[N + 1][2];

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map.get(start).add(end);
            map.get(end).add(start);
        }

        DFS(1);

        answer = Math.min(DP[1][0], DP[1][1]);

        System.out.println("답: " + answer);
    }

    public static void DFS(int now) {
        visited[now] = 1;
        DP[now][0] = 0;
        DP[now][1] = 1;

        for(int next: map.get(now)){
            if(visited[next] == 0 ){
                DFS(next);
                DP[now][0] += DP[next][1];
                DP[now][1] += Math.min(DP[next][0], DP[next][1]);
            }
        }
    }
}
