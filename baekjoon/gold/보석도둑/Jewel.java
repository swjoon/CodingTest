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

        jewelItem[] jewel = new jewelItem[N];

        PriorityQueue<Integer> bag = new PriorityQueue<>();
        PriorityQueue<Integer> list = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            jewel[i] = new jewelItem(w, value);
        }

        Arrays.sort(jewel, new Comparator<jewelItem>() {
            public int compare(jewelItem o1, jewelItem o2) {
                if (o1.weight == o2.weight) {
                    return o2.value - o1.value;
                }
                return o1.weight - o2.weight;
            }
        });

        for (int i = 0; i < K; i++) {
            bag.add(Integer.parseInt(br.readLine()));
        }

        long maxsum = 0;
        int jnum = 0;
        while (!bag.isEmpty()) {
            int now = bag.poll();

            while (jnum < N && jewel[jnum].weight <= now) {
                list.add(jewel[jnum++].value);
            }

            if (!list.isEmpty()) {
                maxsum += list.poll();
            }

        }

        System.out.println(maxsum);
    }
}

class jewelItem {
    int weight;
    int value;

    jewelItem(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}