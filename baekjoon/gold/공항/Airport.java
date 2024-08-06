package baekjoon.gold.공항;

import java.io.*;

public class Airport {

    static int G, P;
    static int[] Gate;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        Gate = new int[G + 1];

        for (int i = 0; i <= G; i++) {
            Gate[i] = i;
        }

        int ans = 0;

        for (int i = 0; i < P; i++) {
            int gate = Integer.parseInt(br.readLine());
            int availableG = find(gate);

            // 사용가능한 게이트가 없음
            if (availableG == 0) {
                break;
            }
            
            union(availableG - 1, availableG);
            ans++;
        }

        System.out.println(ans);
    }

    // 사용가능한 게이트 찾기
    static int find(int x) {
        if (Gate[x] != x) {
            Gate[x] = find(Gate[x]);
        }
        return Gate[x];
    }

    // 사용한 게이트는 사용불가하게 만들기
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            Gate[rootY] = rootX;
        }
    }
}
