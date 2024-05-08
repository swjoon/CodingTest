package 네트워크;

public class Network {
    public static void main(String[] args) {
        solution(3, new int[][] { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } });
        solution(3, new int[][] { { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 } });
    }

    // (DFS/BFS)
    public static int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[computers.length];

        // 1번 노드부터 순차적으로 탐색
        for(int i = 0; i < computers.length; i++){
            if(DFS(i, visited, computers)) answer++;
        }

        return answer;
    }

    // 재귀를 활용한 DFS 탐색
    public static boolean DFS(int now, boolean[] visited, int[][] computers){
        if(visited[now]) return false;
        visited[now] = true;

        for(int i = 0; i < computers.length; i++){
            if(computers[now][i]==1){
                DFS(i, visited, computers);
            }
        }
        return true;
    }
}
