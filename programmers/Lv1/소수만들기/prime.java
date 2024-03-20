package CodingTest.programmers.Lv1.소수만들기;

import java.util.*;

public class prime {

    public static int solution(int[] nums) {
        int answer = 0;

        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    list.add(nums[i] + nums[j] + nums[k]);
                }
            }
        }

        for (int num : list) {

            if (primenum(num)) {
                answer += 1;
            }
        }

        return answer;
    }

    public static boolean primenum(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }
}
