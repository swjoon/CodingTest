package 입국심사;

import java.util.*;

public class Enter {
    public static void main(String[] args) {
        solution(4, new int[] { 10, 4, 5, 2, 6 });
        solution(6, new int[]{7,10});
    }
    // for문 너무 많음 -> 시간복잡도 ↑
    // 이분탐색으로 재시도 할 것.
    public static long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        List<Integer> list = scope(n, times);
        for (long i = 0; i < (long) n * times[0]; i++) {
            if(n == number(list, i)){
                answer = i;
                break;
            }
        }
        System.out.println(answer);
        return answer;
    }

    public static List<Integer> scope(int n, int[] times) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < times.length; i++) {
            if (times[i] > times[0] * n)
                break;
            list.add(times[i]);
        }
        return list;
    }

    public static int number(List<Integer> lists, long time) {
        int num = 0;
        for(int list : lists){
            num += time/list;
        }
        return num;
    }
}
