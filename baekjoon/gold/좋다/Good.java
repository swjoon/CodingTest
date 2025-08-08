package baekjoon.gold.좋다;

import java.io.*;
import java.util.*;

public class Good {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numList = new int[N];

        int goodNumber = 0;

        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numList);

        for (int i = 0; i < N; i++) {
            goodNumber = search(numList, i, N) ? goodNumber + 1 : goodNumber;
        }

        System.out.println(goodNumber);

        br.close();
    }

    private static boolean search(int[] numList, int numL, int N) {
        int l = 0;
        int r = N - 1;

        long num = numList[numL];

        while (l < r) {
            long sum = (long) numList[l] + (long) numList[r];

            if (l == numL) {
                l++;
                continue;
            }

            if (r == numL) {
                r--;
                continue;
            }

            if (sum > num) {
                r--;
                continue;
            }

            if (sum < num) {
                l++;
                continue;
            }

            return true;

        }

        return false;
    }
}