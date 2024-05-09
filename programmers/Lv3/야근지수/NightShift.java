package 야근지수;

import java.util.*;

public class NightShift {
    public static void main(String[] args) {
        solution(4, new int[] { 4, 3, 3 });
    }

    // priority queue 학습완료 추후 코드 수정.
    public static long solution(int n, int[] works) {
        long answer = 0;
        int[] gap = new int[works.length];
        Arrays.sort(works);
        gap[0] = works[0];
        int total = works[0];
        for (int i = 1; i < gap.length; i++) {
            gap[i] = works[i] - works[i - 1];
            total += works[i];
        }

        if (total <= n)
            return 0;

        while (n != 0) {
            int length = 0;
            int now = 0;

            for (int i = gap.length - 1; i >= 0; i--) {
                ++length;
                if (gap[i] != 0) {
                    now = i;
                    break;
                }
            }
            if (n > length * gap[now]) {
                for(int i = works.length-1; i > works.length - length - 1; i--){
                    works[i] -= gap[now];
                }
                n -= length * gap[now];
                gap[now] = 0;

            }else{
                for(int i = works.length-1; i > works.length - length - 1; i--){
                    works[i] -= n/length;
                }
                if(n % length == 0 ) break;
                n %= length;
                for(int i = 0; i < n; i++){
                    works[works.length-1-i]--;
                }
                n = 0;
            }
        }

        for(int i = 0; i < works.length; i++) answer += (long)Math.pow(works[i], 2);

        return answer;
    }
}
