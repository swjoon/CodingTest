package baekjoon.gold.사다리;

import java.io.*;
import java.util.*;

public class Ladder {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double[] L = new double[2];

        L[0] = Double.parseDouble(st.nextToken());
        L[1] = Double.parseDouble(st.nextToken());
        double c = Double.parseDouble(st.nextToken());

        Arrays.sort(L);

        // x < y
        double x2 = L[0] * L[0];
        double y2 = L[1] * L[1];

        // 이분탐색
        double start = 0;
        double end = L[0];
        double w = 0;

        while (end - start > 1e-3) {
            w = (start + end) / 2;

            double w2 = w * w;

            double yh = Math.sqrt(y2 - w2);
            double xh = Math.sqrt(x2 - w2);

            double value = xh * yh / (xh + yh);
            
            if (value < c) {
                end = w;
            } else {
                start = w;
            }
        }

        System.out.printf("%.3f", w);
    }
}
