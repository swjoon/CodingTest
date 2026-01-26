package baekjoon.platinum.MT;

import java.io.*;
import java.util.*;

public class MT {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int pC = Integer.parseInt(st.nextToken());
        int bC = Integer.parseInt(st.nextToken());

        int[] parent = new int[pC + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= pC; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
        }

        int ans = solution(parent, pC, bC);

        System.out.println(Math.min(ans, bC));

        br.close();
    }

    static int solution(int[] parent, int pC, int bC) {
        int ans = 0;

        List<Integer>[] connection = new List[pC + 1];

        List<int[]> graphInfo = new ArrayList<>();

        for (int i = 1; i <= pC; i++) {
            connection[i] = new ArrayList<>();
        }

        for (int i = 1; i <= pC; i++) {
            if (parent[i] != i) {
                connection[parent[i]].add(i);
            }
        }

        int[] maxC = new int[pC + 1];

        boolean[] visitedMax = new boolean[pC + 1];

        for (int i = 1; i <= pC; i++) {
            if (parent[i] == i && !visitedMax[i]) {
                graphInfo.add(new int[]{1, dfsMaxSize(i, connection, visitedMax, maxC)});
            }
        }

        int[] cnt = new int[pC + 1];

        for (int i = 1; i <= pC; i++) {
            if (parent[i] != i && !visitedMax[i]) {
                cnt[i] = 1;
                int[] info = dfsMinSize(i, cnt, parent);
                graphInfo.add(new int[]{info[1], dfsMaxSize(info[0], connection, visitedMax, maxC)});
            }
        }

        graphInfo.sort((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        return dp(graphInfo, bC);
    }

    static int dfsMaxSize(int now, List<Integer>[] connection, boolean[] visited, int[] maxC) {
        int ans = 1;

        visited[now] = true;

        for (int next : connection[now]) {
            if (!visited[next]) {
                ans += dfsMaxSize(next, connection, visited, maxC);
            }
        }

        maxC[now] = ans;

        return ans;
    }

    static int[] dfsMinSize(int now, int[] cnt, int[] parent) {
        int next = parent[now];

        if (cnt[next] != 0) {
            return new int[]{now, cnt[now] - cnt[next] + 1};
        } else {
            cnt[next] = cnt[now] + 1;
            return dfsMinSize(next, cnt, parent);
        }

    }

    static int dp(List<int[]> graphInfo, int bC) {
        boolean[] dp = new boolean[bC + 1];
        dp[0] = true;

        for (int[] g : graphInfo) {
            int L = g[0], R = g[1];

            int[] pref = new int[bC + 1];

            for (int i = 0; i <= bC; i++) {
                pref[i] = (i == 0 ? 0 : pref[i - 1]) + (dp[i] ? 1 : 0);
            }

            boolean[] nx = dp.clone();

            for (int x = L; x <= bC; x++) {

                int lo = Math.max(0, x - R);
                int hi = x - L;

                int cnt = pref[hi] - (lo == 0 ? 0 : pref[lo - 1]);

                if (cnt > 0) {
                    nx[x] = true;
                }
            }

            dp = nx;
        }

        for (int i = bC; i >= 0; i--) {
            if (dp[i]) {
                return i;
            }
        }

        return 0;
    }

}
