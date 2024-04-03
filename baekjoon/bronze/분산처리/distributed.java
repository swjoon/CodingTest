package CodingTest.baekjoon.bronze.분산처리;

import java.util.*;
import java.math.*;
public class distributed {
    //메모리 초과
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            BigInteger c = BigInteger.valueOf(1);

            for(int j = 0; j < b; j++){
                c = c.multiply(BigInteger.valueOf(a));
                c = c.remainder(BigInteger.TEN);
            }

            if(c.equals(BigInteger.ZERO)){
                System.out.println(10);
            }else{
                System.out.println(c);
            }
         
        }
        sc.close();
    }
}
