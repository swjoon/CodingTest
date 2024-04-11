package CodingTest.programmers.기타.주사위;

import java.util.*;

public class dice {
    public static void main(String[] args) {
        System.out.println(solution(6, 6, 6, 6));
    }

    public static int solution(int a, int b, int c, int d) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int answer = 0;
        int[] value = new int[] { a, b, c, d };
        
        //map에 값당 횟수 저장
        for (int i = 0; i < value.length; i++) {
            if (map.containsKey(value[i])) {
                map.put(value[i], map.get(value[i]) + 1);
            } else
                map.put(value[i], 1);
        }
        // key 정렬
        List<Integer> list = new ArrayList<Integer>(map.keySet()); 
        Collections.sort(list, (a1, a2) -> map.get(a2) - map.get(a1));

        // 경우의 수
        switch (map.size()) {
            case 4:
                answer = list.get(0);
                break;
            case 3:
                answer = list.get(1)*list.get(2);
                break;
            case 2:
                if(map.get(list.get(0)) == map.get(list.get(1))){
                    answer = (list.get(0) + list.get(1)) * Math.abs(list.get(0)-list.get(1));
                }else answer = 2 * ( 10 * list.get(0) * list.get(1));
                break;
            case 1:
                answer = 1111 * list.get(0);
                break;
        }

        return answer;
    }
}