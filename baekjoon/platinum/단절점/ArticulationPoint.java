package baekjoon.platinum.단절점;

import java.io.*;
import java.util.*;

public class ArticulationPoint {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int vCount = Integer.parseInt(st.nextToken());
        int eCount = Integer.parseInt(st.nextToken());

        List<Integer>[] connection = new List[vCount + 1];

        for (int i = 1; i <= vCount; i++) {
            connection[i] = new ArrayList<>();
        }

        for (int i = 0; i < eCount; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            connection[a].add(b);
            connection[b].add(a);
        }

        List<Integer> ans = solution(connection, vCount);

        StringBuilder sb = new StringBuilder();

        sb.append(ans.size()).append("\n");

        for (int i = 0; i < ans.size(); i++) {
            sb.append(ans.get(i)).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb.toString());

        br.close();
    }

    static List<Integer> solution(List<Integer>[] connection, int vCount) {
        List<Integer> ans = new ArrayList<>();

        boolean[] visited = new boolean[vCount + 1];
        int[] low = new int[vCount + 1];
        int[] disc = new int[vCount + 1];
        int[] parent = new int[vCount + 1];
        boolean[] isArt = new boolean[vCount + 1];

        Arrays.fill(parent, -1);

        int[] cnt = new int[]{0};

        for (int i = 1; i <= vCount; i++) {
            if (!visited[i]) {
                dfs(i, cnt, connection, visited, low, disc, parent, isArt);
            }
        }

        for (int i = 1; i <= vCount; i++) {
            if (isArt[i]) {
                ans.add(i);
            }
        }

        return ans;
    }

    static void dfs(int now, int[] cnt, List<Integer>[] connection, boolean[] visited, int[] low, int[] disc, int[] parent, boolean[] isArt) {
        visited[now] = true;

        disc[now] = low[now] = ++cnt[0];

        int childC = 0;

        for (int next : connection[now]) {
            if (!visited[next]) {
                childC++;
                parent[next] = now;

                dfs(next, cnt, connection, visited, low, disc, parent, isArt);
                low[now] = Math.min(low[now], low[next]);

                if (parent[now] != -1 && low[next] >= disc[now]) {
                    isArt[now] = true;
                }
            } else if (next != parent[now]) {

                low[now] = Math.min(low[now], disc[next]);
            }
        }

        if (parent[now] == -1 && childC >= 2) {
            isArt[now] = true;
        }

    }

}
