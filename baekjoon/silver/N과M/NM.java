package baekjoon.silver.N과M;

import java.util.*;
import java.io.*;

public class NM {
    static int N, M;
    static int[] number;
    static int[] list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        number = new int[N];
        list = new int[M];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            number[i] = i + 1;
        }
        DFS(0);
    }

    static void DFS(int cnt) {

        if (cnt == M) {
            print();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            list[cnt] = number[i];
            visited[i] = true;
            DFS(cnt + 1);
            visited[i] = false;
        }
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i : list) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
