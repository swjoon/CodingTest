package programmers.Lv4.호텔방배정;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Hotel {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(10, new long[] { 1, 3, 4, 1, 3, 1, 2, 7, })));
    }

    // 룸 선택시 마다 선택가능한 번호를 반환하고, 동시에 다음 선택시 가능한 번호를 업데이트해줌.
    public static long[] solution(long k, long[] room_number) {
        Map<Long, Long> map = new HashMap<>();
        int N = room_number.length;
        long[] ans = new long[N];

        for (int i = 0; i < N; i++) {
            long select = room_number[i];
            ans[i] = find(map, select);
        }

        return ans;
    }

    public static long find(Map<Long, Long> map, long select) {
        long parent = findParent(map, select);

        map.put(select, parent + 1);

        return parent;
    }

    public static long findParent(Map<Long, Long> map, long select) {
        // 빈방 선택
        if (!map.containsKey(select)) {
            map.put(select, select+1);
            return select;
        }

        long parent = findParent(map, map.get(select));

        map.put(select, parent);

        return parent;
    }
}
