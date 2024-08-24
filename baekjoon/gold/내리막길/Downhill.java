package baekjoon.gold.내리막길;

import java.util.*;
import java.io.*;

public class Downhill {
    static int M, N;
    static int[][] map;
    static int result;
    static int[] dirX = { 0, 0, -1, 1 };
    static int[] dirY = { -1, 1, 0, 0 };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];

        for (int y = 0; y < M; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;



        System.out.println(result);
    }

    static void DFS(){
       
    }
}
