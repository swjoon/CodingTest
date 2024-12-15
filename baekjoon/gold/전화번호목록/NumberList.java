package baekjoon.gold.전화번호목록;

import java.util.*;
import java.io.*;

public class NumberList {
    static int check = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            
            String[] numberList = new String[N];

            for (int i = 0; i < N; i++) {
                numberList[i] = br.readLine();
            }

            Arrays.sort(numberList, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    for (int j = 0; j < Math.min(o1.length(), o2.length()); j++) {
                        if (o1.charAt(j) > o2.charAt(j)) {
                            return 1;
                        }
                        if (o1.charAt(j) < o2.charAt(j)) {
                            return -1;
                        }
                    }
                    check = 1;

                    return o1.length() > o2.length() ? 1 : -1;
                }
            });

            if( check == 1){
                System.out.println("NO");
                check = 0;
            }else{
                System.out.println("YES");
            }
        }
    }
}
