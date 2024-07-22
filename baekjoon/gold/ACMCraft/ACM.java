package baekjoon.gold.ACMCraft;

import java.util.*;
import java.io.*;

public class ACM {
    static int[] time;
    static int[] save;
    static HashMap<Integer, List<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            time = new int[N + 1];
            save = new int[N + 1];

            Arrays.fill(save, -1);

            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                time[j] = Integer.parseInt(st.nextToken());
            }

            map = new HashMap<>();

            for (int j = 1; j <= N; j++) {
                map.put(j, new ArrayList<>());
            }

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                map.get(end).add(start);
            }

            int W = Integer.parseInt(br.readLine());

            System.out.println(DP(W));
        }
    }

    public static int DP(int W) {
        int answer = time[W];
        int max = 0;

        // 이미 저장된 값이면 바로 반환. (중복 제거)
        if (save[W] != -1) {
            return save[W];
        }

        // 해당 건물을 조건없이 바로 건설 가능하거나, 시작점에 도달했을때 값 반환
        if (map.get(W).size() == 0) {
            save[W] = answer;
            return answer;
        }

        // 선행 조건에서 경과시간이 제일 긴것을 구함.
        for (int list : map.get(W)) {
            max = Math.max(max, DP(list));
        }

        // 현재 값에 선행조건중 제일 오래걸리는 시간값을 더한 후 저장과 반환.
        save[W] = answer + max;
        return save[W];
    }
}
