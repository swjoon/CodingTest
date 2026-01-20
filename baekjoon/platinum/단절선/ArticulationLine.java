package baekjoon.platinum.단절선;

import java.io.*;
import java.util.*;

public class ArticulationLine {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int vCount = Integer.parseInt(st.nextToken());
        int eCount = Integer.parseInt(st.nextToken());

        List<Integer>[] connection = new List[vCount + 1];

        for (int i = 1; i <= vCount; i++) {
            connection[i] = new ArrayList<>();
        }

        for (int i = 1; i <= eCount; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            connection[a].add(b);
            connection[b].add(a);
        }

        List<int[]> ans = solution(vCount, connection);

        StringBuilder sb = new StringBuilder();

        sb.append(ans.size()).append("\n");

        for (int i = 0; i < ans.size(); i++) {
            sb.append(ans.get(i)[0]).append(" ").append(ans.get(i)[1]).append("\n");
        }

        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb.toString());

        br.close();
    }

    static List<int[]> solution(int vCount, List<Integer>[] connection) {
        List<int[]> ans = new ArrayList<>();

        boolean[] visited = new boolean[vCount + 1];
        int[] parent = new int[vCount + 1];
        int[] disc = new int[vCount + 1];
        int[] low = new int[vCount + 1];
        int[] cnt = new int[]{0};

        Arrays.fill(parent, -1);

        cnt[0] = 0;

        dfs(1, cnt, low, disc, parent, visited, connection, ans);

        ans.sort((a, b) -> {
            return a[0] - b[0] != 0 ? a[0] - b[0] : a[1] - b[1];
        });

        return ans;
    }

    static void dfs(int now, int[] cnt, int[] low, int[] disc, int[] parent, boolean[] visited, List<Integer>[] connection, List<int[]> ans) {
        visited[now] = true;

        disc[now] = low[now] = ++cnt[0];

        for (int next : connection[now]) {
            if (!visited[next]) {

                parent[next] = now;

                dfs(next, cnt, low, disc, parent, visited, connection, ans);

                low[now] = Math.min(low[now], low[next]);

                if (low[next] > disc[now]) {
                    ans.add(now > next ? new int[]{next, now} : new int[]{now, next});
                }
            } else if (parent[now] != next) {
                low[now] = Math.min(low[now], disc[next]);
            }
        }
    }
}
