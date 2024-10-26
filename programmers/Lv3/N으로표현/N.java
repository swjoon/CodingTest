package N으로표현;

import java.util.*;

public class N {
    public static void main(String[] args) {
        System.out.println(solution(5, 55));
    }

    public static int solution(int N, int number) {
        List<Set<Integer>> dp = new ArrayList<>();
        dp.add(new HashSet<>(Collections.singleton(0)));

        for (int i = 1; i < 9; i++) {
            Set<Integer> currentSet = new HashSet<>();
            int repeatedNumber = Integer.parseInt(String.valueOf(N).repeat(i));
            currentSet.add(repeatedNumber);

            for (int j = 1; j < i; j++) {
                for (int l : dp.get(j)) {
                    for (int r : dp.get(i - j)) {
                        currentSet.add(l + r);
                        currentSet.add(l - r);
                        currentSet.add(l * r);
                        if (r != 0) currentSet.add(l / r);
                    }
                }
            }

            if (currentSet.contains(number)) return i;
            dp.add(currentSet);
        }

        return -1;
    }
}
