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

        // 거리를 w 로 설정 각 사다리의 높이 비 로 한쪽 사다리의 높이를 교차점에 관한 식으로 변환 (yh/xh+1)c -> 피타고라스로 w에
        // 관한 식으로 변환

        // 이분탐색
        double start = 0;
        double end = L[0];
        double w = (end + start / 2);

        // x < y
        double x2 = L[0] * L[0];
        double y2 = L[1] * L[1];
        double c2 = c * c;

        while (true) {

            double w2 = w * w;

            double yh = Math.sqrt(y2 - w2);
            double xh = Math.sqrt(x2 - w2);

            double value = w2 + (xh + yh) * (xh + yh) * c2 / (x2 - w2);

            if (Math.abs(y2 - value) < 1e-3) {
                System.out.printf("%.3f", w);
                return;
            }

            if (Double.compare(y2, value) < 0) {
                end = w;
                w = (start + w) / 2;
            } else {
                start = w;
                w = (w + end) / 2;
            }
        }
    }
}
