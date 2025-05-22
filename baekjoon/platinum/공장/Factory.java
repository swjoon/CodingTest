package baekjoon.platinum.공장;

import java.util.*;
import java.io.*;

public class Factory {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Map<Integer, Integer> cableMap = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            cableMap.put(Integer.parseInt(st.nextToken()), i);
        }

        int size = 1;

        while (size < N) {
            size <<= 1;
        }

        int[] usedCable = new int[size * 2];

        st = new StringTokenizer(br.readLine());

        long ans = 0;

        for (int i = 0; i < N; i++) {
            int cableLocation = cableMap.get(Integer.parseInt(st.nextToken()));

            usedCable(cableLocation, size, usedCable);

            ans += getCrossCable(cableLocation, size, usedCable);
        }

        System.out.println(ans);

        br.close();
    }

    private static void usedCable(int cableLocation, int size, int[] usedCable) {
        int l = cableLocation + size;

        usedCable[l] = 1;

        l >>= 1;

        while (l > 0) {
            usedCable[l] = usedCable[l << 1] + usedCable[l << 1 | 1];
            l >>= 1;
        }
    }

    private static int getCrossCable(int cableLocation, int size, int[] usedCable) {
        int used = 0;
        int l = size;
        int r = size + cableLocation - 1;

        while (l <= r) {
            if ((l & 1) == 1)
                used += usedCable[l++];
            if ((r & 1) == 0)
                used += usedCable[r--];

            l >>= 1;
            r >>= 1;
        }

        return cableLocation - used;
    }
}