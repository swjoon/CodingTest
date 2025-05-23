package baekjoon.platinum.달리기;

import java.util.*;
import java.io.*;

public class Run {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] cap = new long[N];

        for (int i = 0; i < N; i++) {
            cap[i] = Long.parseLong(br.readLine());
        }

        long[] copyCap = cap.clone(); // 평균 능력 순

        Arrays.sort(copyCap);

        Map<Long, Integer> capMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            capMap.put(copyCap[i], i);
        }

        int[] newCap = new int[N];

        for (int i = 0; i < N; i++) {
            newCap[i] = capMap.get(cap[i]); // 능력치 재분배
        }

        int size = 1;

        while (size < N) {
            size <<= 1;
        }

        int[] capTrie = new int[size * 2];

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int value = newCap[i];
            
            int rank = i + 1;

            rank -= findOverTakeCount(value, size, capTrie);

            addTrie(value + size, capTrie);

            sb.append(rank).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    /**
     * Trie 에 앞선 선수의 능력치 반영
     * 
     * @param idx 선수 평균 능력치 + trie 사이즈
     * @param capTrie
     */
    private static void addTrie(int idx, int[] capTrie) {
        
        capTrie[idx] = 1;

        idx >>= 1;

        while (idx > 0) {
            capTrie[idx] = capTrie[idx << 1] + capTrie[idx << 1 | 1];

            idx >>= 1;
        }
    }

    /**
     * 추월 가능 횟수 조회
     * 
     * @param value 선수 평균 능력치
     * @param size trie 사이즈
     * @param capTrie
     * @return 추월 횟수
     */
    private static int findOverTakeCount(int value, int size, int[] capTrie) {
        int ans = 0;

        int l = size;
        int r = size + value;

        while (l <= r) {
            if ((l & 1) == 1)
                ans += capTrie[l++];
            if ((r & 1) == 0)
                ans += capTrie[r--];

            l >>= 1;
            r >>= 1;
        }

        return ans;
    }
}
