package baekjoon.gold.트리의지름;

import java.io.*;
import java.util.*;

public class Tree {
    static int ans;
    static int V, end;
    static List<int[]>[] tree;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());

        tree = new List[V + 1];

        for (int i = 1; i <= V; i++) {
            tree[i] = new ArrayList<>();
        }
        // 정점 순서대로 제공안함
        for (int i = 0; i < V; i++) {
            String[] array = br.readLine().split(" ");
            int num = 1;
            while(!array[num].equals("-1")){
                // tree[i] 가 아닌 tree[array[0]]으로 입력해주어야함
                tree[Integer.parseInt(array[0])].add(new int[]{Integer.parseInt(array[num++]),Integer.parseInt(array[num++])});
            }
        }

        ans = 0;

        visited = new boolean[V + 1];

        DFS(1,0);

        Arrays.fill(visited, false);

        DFS(end,0);

        System.out.println(ans);
    }

    public static void DFS(int node, int sum) {
        if(ans < sum){
            ans = sum;
            end = node;
        }

        visited[node] = true;

        for (int i = 0; i < tree[node].size(); i++) {
            if (!visited[tree[node].get(i)[0]]) {
                DFS(tree[node].get(i)[0], sum + tree[node].get(i)[1]);
            }
        }
    }
}
