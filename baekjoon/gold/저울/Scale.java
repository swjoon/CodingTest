package baekjoon.gold.저울;

import java.util.*;
import java.io.*;

public class Scale {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] weight = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weight);

        int[] dp = new int[N + 1];

        // dp[i] 0 ~ i 번째 무게추들로 만들 수 있는 연속적인 무게한계
        // weight[i] > dp[i] + 1 -> dp[i] + 1 은 조합할 수 없는 무게
        for (int i = 0; i < N; i++) {
            if (weight[i] <= dp[i] + 1) {
                dp[i + 1] = dp[i] + weight[i];
            } else {
                System.out.println(dp[i] + 1);
                return;
            }
        }

        System.out.println(dp[N] + 1);
    }
}