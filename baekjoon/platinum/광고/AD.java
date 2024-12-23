package baekjoon.platinum.광고;

import java.io.*;

public class AD {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());
        String S = br.readLine();

        int[] pi = kmp(S);

        int minAd = L - pi[L - 1];

        System.out.println(minAd);
    }

    private static int[] kmp(String input) {
        int L = input.length();
        int[] pi = new int[L];

        // 연속된 중복 횟수 
        int count = 0;

        // 최소 문자열 길이를 찾으면 문자열 그뒤로는 count만 상승 
        for (int i = 1; i < L; i++) {
            
            while (count > 0 && input.charAt(i) != input.charAt(count)) {
                count = pi[count - 1];
            }

            if (input.charAt(i) == input.charAt(count)) {
                count++;
            }

            pi[i] = count;
        }

        return pi;
    }
}