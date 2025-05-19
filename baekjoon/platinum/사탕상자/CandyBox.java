package baekjoon.platinum.사탕상자;

import java.util.*;
import java.io.*;

public class CandyBox {
    private static final int CANDYVALUE = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int size = 1;

        while (size < CANDYVALUE) {
            size <<= 1;
        }

        int[] candyBox = new int[size * 2];

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 2) {
                int candy = Integer.parseInt(st.nextToken()) - 1;
                int num = Integer.parseInt(st.nextToken());
                changeCandy(candy, num, size, candyBox);
            } else {
                int rank = Integer.parseInt(st.nextToken());
                int candy = getCandy(rank, size, candyBox);
                changeCandy(candy, -1, size, candyBox);

                candy += 1;

                sb.append(candy).append("\n");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void changeCandy(int candy, int num, int size, int[] candyBox) {
        int candyNum = size + candy;

        candyBox[candyNum] += num;

        candyNum >>= 1;

        while (candyNum > 0) {
            candyBox[candyNum] = candyBox[candyNum * 2] + candyBox[candyNum * 2 + 1];

            candyNum >>= 1;
        }
    }

    private static int getCandy(int rank, int size, int[] candyBox) {
        int idx = 1;

        while (idx < size) {
            int l = idx * 2;
            int r = idx * 2 + 1;
            int left = candyBox[l];

            if (left >= rank) {
                idx = l;
            } else {
                rank -= left;
                idx = r;
            }
        }

        return idx - size;
    }
}