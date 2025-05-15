package baekjoon.gold.컬러볼;

import java.util.*;
import java.io.*;

public class ColorBall {
    private static final int SIZE = 2002;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] ball = new int[N][2];

        int[] sum = new int[SIZE];

        List<int[]> sortBallList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            ball[i][0] = color;
            ball[i][1] = size;

            sortBallList.add(new int[] { color, size });

            sum[size + 1] += size;
        }

        sortBallList.sort((a, b) -> {
            int check = a[0] - b[0];
            return check != 0 ? check : a[1] - b[1];
        });

        Map<Integer, Map<Integer, Integer>> ballInfo = makeInfo(sortBallList, N);

        for (int i = 1; i < SIZE; i++) {
            sum[i] += sum[i - 1];
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int[] info = ball[i];

            int maxSize = sum[info[1]] - ballInfo.get(info[0]).get(info[1]);

            sb.append(maxSize).append("\n");
        }

        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb.toString());

        br.close();
    }

    private static Map<Integer, Map<Integer, Integer>> makeInfo(List<int[]> sortBallList, int N) {
        Map<Integer, Map<Integer, Integer>> ballInfo = new HashMap<>();

        int sum = 0;

        for (int i = 0; i < N; i++) {
            int[] info = sortBallList.get(i);

            if (!ballInfo.containsKey(info[0])) {
                sum = 0;
                ballInfo.put(info[0], new HashMap<Integer, Integer>());
                ballInfo.get(info[0]).put(info[1], sum);
                continue;
            }

            if (ballInfo.get(info[0]).containsKey(info[1])) {
                sum += info[1];
                continue;
            }

            int[] beforeInfo = sortBallList.get(i - 1);

            sum += beforeInfo[1];

            ballInfo.get(info[0]).put(info[1], sum);

        }

        return ballInfo;
    }
}