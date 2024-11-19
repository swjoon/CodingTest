package programmers.Lv4.징검다리;

import java.util.Arrays;

public class Bridge {
    public static void main(String[] args) {
        System.out.println(solution(25, new int[] { 2, 14, 11, 21, 17 }, 2));
    }

    public static int solution(int distance, int[] rocks, int n) {
        int length = rocks.length;

        Arrays.sort(rocks);

        int[] rocksList = new int[length + 2];

        for (int i = 1; i <= length; i++) {
            rocksList[i] = rocks[i-1];
        }

        rocksList[0] = 0;
        rocksList[length + 1] = distance;

        int max = distance;
        int min = 0;
        int maxValue = 0;

        while (min < max) {
            int nowPoint = 0;
            int beforePoint = 0;
            int breakNumber = 0;
            int mid = (max + min) / 2;
            int minValue = distance;

            // 돌다리 두드리기
            for (int i = 1; i <= length+1; i++) {
                nowPoint = rocksList[i];

                // 바위 부숨
                if (nowPoint - beforePoint <= mid) {
                    breakNumber++;
                    continue;
                }

                // 바위 못부숨
                // 바위간 거리중 최솟값 기록
                minValue = Math.min(minValue, nowPoint - beforePoint);
                beforePoint = nowPoint;
            }

            // 부순 횟수가 범위 내 -> 현재까지 기록했던 mid값 별 최소 거리중 최대값 저장.
            if(breakNumber <= n){
                maxValue = Math.max(maxValue, minValue);
                min = mid + 1;
                continue;
            }

            max = mid;
        }
        return maxValue;
    }
}