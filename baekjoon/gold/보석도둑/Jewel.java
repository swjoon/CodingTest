package baekjoon.gold.보석도둑;

import java.util.*;
import java.io.*;

public class Jewel {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        PriorityQueue<Integer> bag = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            map.putIfAbsent(m, new PriorityQueue<>((o1, o2) -> o2 - o1));
            map.get(m).add(value);
        }

        for (int i = 0; i < K; i++) {
            bag.add(Integer.parseInt(br.readLine()));
        }

        int maxsum = 0;

        while (!bag.isEmpty()) {
            int now = bag.poll();
            int maxV = 0;
            int W = 0;

            for (int i = 1; i <= now; i++) {
                PriorityQueue<Integer> pq = map.get(i);
                if (pq != null && !pq.isEmpty()) {
                    int value = map.get(i).peek();
                    if (value >= maxV) {
                        W = i;
                        maxV = value;
                    }
                }
            }

            if (map.get(W) != null) {
                map.get(W).poll();
                maxsum += maxV;
            }
        }

        System.out.println(maxsum);
    }
}
