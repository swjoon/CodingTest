package baekjoon.gold.음주코딩;

import java.util.*;
import java.io.*;

public class Coding {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        String line;

        while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
            st = new StringTokenizer(line);

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] num = new int[N];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                num[i] = parseInt(Integer.parseInt(st.nextToken()));
            }

            int size = 1;

            while (size < N) {
                size <<= 1;
            }

            int[] signArray = new int[size * 2];

            for (int i = 0; i < N; i++) {
                signArray[size + i] = num[i];
            }

            for (int i = size + N; i < size * 2; i++) {
                signArray[i] = 1;
            }

            for (int i = size - 1; i > 0; i--) {
                signArray[i] = signArray[i * 2] * signArray[i * 2 + 1];
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                char type = st.nextToken().charAt(0);

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (type == 'C') {
                    changeNumber(a - 1, b, size, signArray);
                } else {
                    int result = findSign(a - 1, b - 1, size, signArray);

                    char sign = getSign(result);

                    sb.append(sign);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void changeNumber(int node, int number, int size, int[] signArray) {

        int n = size + node;

        int i = parseInt(number);

        if (signArray[n] == i) {
            return;
        }

        signArray[n] = i;

        n >>= 1;

        while (n > 0) {
            signArray[n] = signArray[n * 2] * signArray[n * 2 + 1];

            n >>= 1;
        }
    }

    private static int findSign(int left, int right, int size, int[] signArray) {
        int ans = 1;
        int l = size + left;
        int r = size + right;

        while (l <= r) {
            if ((l & 1) == 1)
                ans *= signArray[l++];

            if ((r & 1) == 0)
                ans *= signArray[r--];

            l >>= 1;
            r >>= 1;
        }

        return ans;
    }

    private static char getSign(int num) {
        switch (num) {
            case 1:
                return '+';
            case -1:
                return '-';
            default:
                return '0';
        }

    }

    private static int parseInt(int num) {
        return num != 0 ? num > 0 ? 1 : -1 : 0;
    }
}
