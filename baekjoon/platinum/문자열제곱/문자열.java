package baekjoon.platinum.문자열제곱;

import java.io.*;

public class 문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = br.readLine();

            if (input.equals(".")) {
                return;
            }

            System.out.println(kmp(input));
        }
    }

    private static int kmp(String input) {
        int L = input.length();
        int[] pi = new int[L];
        int j = 0;

        for (int i = 1; i < L; i++) {

            while (j > 0 && input.charAt(i) != input.charAt(j)) {
                j = pi[j - 1];
            }

            if (input.charAt(i) == input.charAt(j)) {
                j++;
            }

            pi[i] = j;
        }

        int minL = L - pi[L - 1];

        return L % minL == 0 ? L / minL : 1;
    }
}
