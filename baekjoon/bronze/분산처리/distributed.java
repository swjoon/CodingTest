package baekjoon.bronze.분산처리;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class distributed {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < T; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) % 10;
            int b = Integer.parseInt(st.nextToken());
            int data = 1;
            for (int i = 0; i < b; i++) {
                data = (data * a) % 10;
            }
            System.out.println(data == 0 ? 10 : data);
        }
        br.close();
    }
}
