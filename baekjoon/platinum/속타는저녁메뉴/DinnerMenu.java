package baekjoon.platinum.속타는저녁메뉴;

import java.io.*;
import java.util.StringTokenizer;

public class DinnerMenu {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        int result = kmp(sb.toString(), N);

        int remain = N % result;

        if (remain != 0) {
            int add = result - remain;
            sb.append(sb.substring(0, add));
            result = kmp(sb.toString(), N + add);
        }

        int repeatNum = N / result;

        if (repeatNum < 2) {
            System.out.println("1/" + N);
            return;
        }

        int setNum = repeatNum;

        while (setNum > 1) {
            if (repeatNum % setNum == 0 && N % setNum == 0) {
                repeatNum /= setNum;
                N /= setNum;
                setNum = repeatNum;
                continue;
            }
            setNum--;
        }

        System.out.println(repeatNum + "/" + N);
    }

    private static int kmp(String input, int N) {
        int[] pi = new int[N];
        int j = 0;

        for (int i = 1; i < N; i++) {

            while (j > 0 && input.charAt(i) != input.charAt(j)) {
                j = pi[j - 1];
            }

            if (input.charAt(i) == input.charAt(j)) {
                j++;
            }

            pi[i] = j;
        }

        return N - pi[N - 1];
    }
}