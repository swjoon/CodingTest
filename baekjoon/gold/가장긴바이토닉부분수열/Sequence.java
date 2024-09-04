package baekjoon.gold.가장긴바이토닉부분수열;

import java.util.*;
import java.io.*;

public class Sequence {
    static int len;
    static int[] ascend;
    static int[] descend;
    static int[] number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        len = Integer.parseInt(br.readLine());

        number = new int[len];
        ascend = new int[len];
        descend = new int[len];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < len; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < len; i++) {
            ascend[i] = 1;
            for (int j = 0; j < i; j++) {
                if (number[i] > number[j] && ascend[i] < ascend[j] + 1) {
                    ascend[i] = ascend[j] + 1;
                }
            }
        }

        for (int i = len - 1; i >= 0; i--) {
            descend[i] = 1;
            for (int j = len - 1; j > i; j--) {
                if (number[i] > number[j] && descend[i] < descend[j] + 1) {
                    descend[i] = descend[j] + 1;
                }
            }
        }

        int max = 0;

        for (int i = 0; i < len; i++) {
            max = Math.max(max, ascend[i] + descend[i] - 1);
        }

        System.out.println(max);
    }
}
