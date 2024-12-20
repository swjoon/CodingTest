package baekjoon.gold.두배열의합;

import java.util.*;
import java.io.*;

public class TwoArrays {
    static int T;
    static List<int[]> array = new ArrayList<>();
    static List<Long[]> arraySum = new ArrayList<>();
    static List<Map<Long, Long>> map = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        input();

        for (int i = 0; i < 2; i++) {
            map.add(new HashMap<>());
            solution(i);
        }

        long answer = 0;

        for (long keyA : map.get(0).keySet()) {
            long keyB = T - keyA;
            if (map.get(1).containsKey(keyB)) {
                answer += map.get(0).get(keyA) * map.get(1).get(keyB);
            }
        }

        System.out.println(answer);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < 2; i++) {
            int size = Integer.parseInt(br.readLine());

            array.add(new int[size + 1]);
            arraySum.add(new Long[size + 1]);
            array.get(i)[0] = 0;
            arraySum.get(i)[0] = 0L;

            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= size; j++) {
                array.get(i)[j] = Integer.parseInt(st.nextToken());
                arraySum.get(i)[j] = arraySum.get(i)[j - 1] + array.get(i)[j];
            }
        }
    }

    private static void solution(int type) {
        Long[] currentArray = arraySum.get(type);
        for (int startPoint = 0; startPoint < currentArray.length - 1; startPoint++) {
            for (int endPoint = startPoint + 1; endPoint < currentArray.length; endPoint++) {
                long sum = currentArray[endPoint] - currentArray[startPoint];
                map.get(type).put(sum, map.get(type).getOrDefault(sum, 0L) + 1);
            }
        }
    }
}