package 순위;

import java.util.*;

public class Rank {
    public static void main(String[] args) {
        solution(5, new int[][] { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } });
    }

    public static int solution(int n, int[][] results) {
        int answer = 0;

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int[][] vistied = new int[n][n]; // 중복제거를 위해 방문확인.
        int[][] graph = new int[n][n]; // 순위를 확정하려면 모든선수와 관련이 있어야함.

        for (int i = 1; i < n + 1; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int[] result : results) { // 선수별 대진 결과 저장(승리)
            map.get(result[0]).add(result[1]);
        }

        for (int i = 1; i < n+1; i++) { // 선수별 관련 여부파악
            if (map.get(i) != null)
                DFS(i, i, graph, map, vistied);
        }

        for (int i = 0; i < n; i++) { // 순위 특정 가능 여부 파악.
            if (Arrays.stream(graph[i]).sum() == n - 1) {
                answer++;
            }
        }

        System.out.println(answer);
        return answer;
    }

    public static void DFS(int now, int i, int[][] graph, HashMap<Integer, List<Integer>> map, int[][] vistied ) {
        for (int list : map.get(i)) {
            if(vistied[now-1][list-1] == 0){
                vistied[now-1][list-1] = 1;
                graph[now-1][list-1] = 1;
                graph[list-1][now-1] = 1;
                DFS(now, list, graph, map, vistied);
            }
        }
    }
}