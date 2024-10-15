package baekjoon.gold.ABCDE;

import java.io.*;
import java.util.*;

public class ABCDE {
    static int N, M;
    static boolean check;
    static boolean[] visited;
    static List<Integer>[] rel;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        rel = new List[N];

        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            rel[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int friendA = Integer.parseInt(st.nextToken());
            int friendB = Integer.parseInt(st.nextToken());
            rel[friendA].add(friendB);
            rel[friendB].add(friendA);
        }

        for(int i = 0; i < N; i++){
            DFS(0, 1);
            if(check){
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);

    }

    public static void DFS(int now , int dep) {
        if(check) return;

        if( dep == 5){
            check = true;
            return;
        }

        for (int list : rel[now]) {
            if (!visited[list]) {
                visited[list] = true;
                DFS(list , dep + 1);
                visited[list] = false;
            }
        }
    }
}
