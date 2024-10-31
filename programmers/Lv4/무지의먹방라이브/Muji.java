package programmers.Lv4.무지의먹방라이브;

import java.util.*;

public class Muji {
    public static void main(String[] args) {
        System.out.println(solution(new int[] { 3, 3, 3 }, 8));
    }

    public static int solution(int[] food_times, long k) {
        int N = food_times.length;

        // 1 사이클 안에 해결
        if (N > k) {
            return (int) k + 1;
        }

        Map<Integer, Integer> map = new HashMap<>();

        // 섭취 시간별 음식 분포도
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(food_times[i])) {
                map.put(food_times[i], 1);
                continue;
            }
            map.replace(food_times[i], map.get(food_times[i]) + 1);
        }

        List<Integer> list = new ArrayList<>(map.keySet());

        // 섭취 시간 짧은 순으로 정렬
        Collections.sort(list);

        long remainTime = k;
        long beforeCnt = 0;
        long remain = 0;
        int food = N;

        for (int cnt : list) {
            long check = remainTime - food * (cnt - beforeCnt);
            if (check < 0) {
                remain = remainTime % food;
                break;
            }
            food -= map.get(cnt);
            beforeCnt = cnt;
            remainTime = check;
        }

        for (int i = 0; i < N; i++) {
            if (food_times[i] > beforeCnt) {
                if (remain == 0) {
                    return i + 1;
                }
                remain--;
            }
        }
        return -1;
    }
}
