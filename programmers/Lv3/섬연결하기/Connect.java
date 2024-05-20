package 섬연결하기;

import java.util.*;

public class Connect {
    public static void main(String[] args){
        solution(4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}});
    }

    static int[] parent;

    // Kruskal Algorithm (Minimum Spaning Tree)
    public static int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
        Arrays.sort(costs, (o1,o2) -> o1[2] - o2[2]);

        for(int i = 0; i < costs.length; i++){
            int start = costs[i][0];
            int end = costs[i][1];
            int cost = costs[i][2];
            if(find(start) != find(end)){
                union(start, end);
                answer += cost;
            }
        }
        return answer;
    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a > b) parent[a] = b;
        else parent[b] = a;
    }
    // 부모노드 겹치는지 파악
    public static int find(int x){
        if(parent[x] == x) return x;
        else return find(parent[x]);
    }
}
