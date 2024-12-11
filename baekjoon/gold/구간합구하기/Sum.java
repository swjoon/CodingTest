package baekjoon.gold.구간합구하기;

import java.io.*;
import java.util.*;

public class Sum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Long[] number = new Long[N + 1];
        Long[] preSum = new Long[N + 1];
        preSum[0] = 0L;

        for (int i = 1; i <= N; i++) {
            number[i] = Long.parseLong(br.readLine());
            preSum[i] = preSum[i - 1] + number[i];
        }


        TreeMap<Integer, Long> changeList = new TreeMap<>();

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 1) {
                int point = Integer.parseInt(st.nextToken());
                changeList.put(point, Long.parseLong(st.nextToken()) - number[point]);
                continue;
            }
            int startPoint = Integer.parseInt(st.nextToken());
            int endPoint = Integer.parseInt(st.nextToken());

            System.out.println(preSum[endPoint] - preSum[startPoint - 1] + check(startPoint, endPoint, changeList));
        }
    }

    public static Long check(int startPoint, int endPoint, TreeMap<Integer, Long> changeList) {
        Long changes = 0L;

        for (Long value : changeList.subMap(startPoint, endPoint + 1).values()) {
            changes += value;
        }

        return changes;
    }
}
