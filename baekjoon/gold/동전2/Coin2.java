package baekjoon.gold.동전2;

import java.io.*;
import java.util.*;

public class Coin2 {
    static int n, k;
    static int[] coin;
    static long[] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        DP = new long[k + 1];

        Arrays.fill(DP, 10001);

        // 중복된 코인의 값 제거
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num <= k) {
                set.add(num);
            }
        }

        coin = new int[set.size()];

        int s = 0;
        for (int i : set) {
            coin[s++] = i;
        }
        // 작은값부터 정렬
        Arrays.sort(coin);

        // 코인의 값의 배수 dp 배열에 넣기. 겹치면 적게 쓴값 저장
        for (int i = 0; i < coin.length; i++) {
            for (int j = 1; j <= k / coin[i]; j++) {
                DP[coin[i] * j] = Math.min(DP[coin[i] * j], j);
            }
        }

        // DP 로직
        for (int i = coin[0]; i <= k; i++) {
            for (int j = 1; j <= i / 2; j++) {
                // i 값을 만드는 코인의 조합중 가장 적게 쓰는 값 저장
                DP[i] = Math.min(DP[i], DP[j] + DP[i - j]);
            }
        }

        System.out.println((DP[k] == 10001) ? -1 : DP[k]);
    }
}
