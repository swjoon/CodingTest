package baekjoon.gold.동전;

import java.io.*;
import java.util.*;

public class Coin {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coin = new int[n];
        int[] numbercase = new int[k + 1];

        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coin);

        for(int i = 0; i < n; i++){
            if(coin[i] > k ) break;
            numbercase[coin[i]]++;
            for(int j = coin[i]; j <= k; j++){
                numbercase[j] = numbercase[j] + numbercase[j-coin[i]];
            }
        }
        
        System.out.println(numbercase[k]);
    }
}