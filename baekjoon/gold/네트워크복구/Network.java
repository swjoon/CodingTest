package baekjoon.gold.네트워크복구;

import java.util.*;
import java.io.*;

public class Network {

    static int N, M;
    static int[] parent;
    static int[][] root;
    static int cnt = 0;
    static List<int[]> list = new ArrayList<>();

    // map으로 양뱡향 값 넣기
    // 각 노드에서 1까지 가는 최소치의 값 구함
    // HashSet으로 중복제거
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        root = new int[M][3];

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                root[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(root, ((o1, o2) -> o1[2] - o2[2]));

        for (int i = 0; i < M; i++) {
            int x = root[i][0];
            int y = root[i][1];

            if (parent[x] != parent[y]) {
                union(x, y);
                list.add(new int[] { x, y });
            }

            if (check()) {
                break;
            }
        }

        System.out.println(list.size());
        for (int[] l : list) {
            System.out.println(l[0] + " " + l[1]);
        }

    }

    static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if (p1 > p2) {
            parent[n1] = p2;
        } else {
            parent[n2] = p1;
        }
    }

    static int find(int n) {
        if (parent[n] != n) {
            parent[n] = find(parent[n]);
        }
        return parent[n];
    }

    static boolean check() {
        for (int i = 1; i <= N; i++) {
            if (parent[i] != 1)
                return false;
        }
        return true;
    }
}
