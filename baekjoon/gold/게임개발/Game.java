package baekjoon.gold.게임개발;

import java.util.*;
import java.io.*;

public class Game {
    static int N;
    static long[] dp;
    static int[] value;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new long[N + 1];
        value = new int[N + 1];
        list = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            value[i] = Integer.parseInt(st.nextToken());
            int length = st.countTokens() - 1;
            for (int j = 0; j < length; j++) {
                list[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i = N ; i > 0; i--){ 
           DP(i);
        }


        for(int i = 1; i <= N; i++){
            System.out.println(dp[i]);
        }
    }

    static long DP(int i){
        // 메모제이션
        if(dp[i] != 0){
            return dp[i];
        }

        // 선행되어야 할 조건이 없으면 값 반환
        if(list[i].size() == 0){
            return dp[i] = value[i];
        }

        long maxValue = 0;

        // 모든 선행 조건들이 완료되어야 진행 가능하므로 max value
        for(int l : list[i]){
            maxValue = Math.max(maxValue, DP(l));
        }

        return dp[i] = value[i] + maxValue;
    }
}
