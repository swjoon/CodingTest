package baekjoon.gold.텀프로젝트;

import java.util.*;
import java.io.*;

public class Project {
    static int T, n;
    static int cnt;
    static int[] member;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());

            member = new int[n + 1];
            visited = new boolean[n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                member[j] = Integer.parseInt(st.nextToken());
                if (member[j] == j) {
                    visited[j] = true;
                }
            }

            cnt = 0;

            for (int j = 1; j <= n; j++) {
                if (!visited[j]) {
                    DFS(j, j);
                }
            }

            System.out.println(cnt);
        }

    }

    static void DFS(int i, int target) {
        List<Integer> list = new ArrayList<>();
        int now = i;
        while (!visited[now]) {
            visited[now] = true;
            list.add(now);
            now = member[now];
        }

        list.add(now);

        cnt += list.indexOf(now);
    }
}
