package 택배배달과수거하기;

import java.util.ArrayList;
import java.util.Arrays;

public class delivery {
    public static void main(String[] args) {
        System.out.println("정답: " + solution(4, 5, new int[] { 1, 0, 3, 1, 2 }, new int[] { 0, 3, 0, 4, 0 }));
        System.out.println("정답: " + solution(2, 7, new int[] { 1, 0, 2, 0, 1, 0, 2}, new int[] { 0, 2, 0, 1, 0, 2, 0}));
        System.out.println("정답: " + solution(4, 5, new int[] { 8, 0, 8, 0, 4}, new int[] { 0, 0, 0, 0, 20})); //50
        System.out.println("정답: " + solution(2, 2,  new int[] { 0, 1}, new int[] { 0, 4}));// 8
        System.out.println("정답: " + solution(4, 5, new int[] { 1, 1, 1, 1, 1}, new int[] { 1, 1, 1, 1, 1})); //12
        System.out.println("정답: " + solution(2, 3, new int[] { 0, 6, 13}, new int[] { 19, 0, 1})); // 54
        
    }
    // TEST 1 시간초과 정답률 85/100
    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        ArrayList<int[]> list = new ArrayList<>();
        long answer = 0;
        for (int i = 0; i < deliveries.length; i++) {
            list.add(new int[] { deliveries[i], pickups[i] });
        }
        System.out.println("cap: " + cap ); //
        System.out.println("1 집 리스트: " + Arrays.deepToString(list.toArray())); //

        // 제일 먼거리의 집에서 부터 배달과 수거없는 집 목록 제거.
        removelist(list);

        System.out.println("2 집 리스트: " + Arrays.deepToString(list.toArray())); //
        while (!list.isEmpty()) {
            int newcap = cap;
            // 제일 먼곳부터 배달
            int i = list.size() - 1;
            int j = list.size() - 1;
            while (newcap > 0) {
                if(i < 0){
                    newcap = 0;
                }else{
                    int num = list.get(i)[0];
                    if (newcap > num) {
                        list.set(i, new int[] { 0, list.get(i)[1] });
                        newcap -= num;
                    } else {
                        list.set(i, new int[] { list.get(i)[0] - newcap, list.get(i)[1] });
                        newcap = 0;
                    }
                    i--;
                }
            }
            System.out.println("3 집 리스트: " + Arrays.deepToString(list.toArray())); //
            while (newcap < cap) {
                if (j < 0) {
                    newcap = cap;
                }else{
                    int num = list.get(j)[1];
                    if (newcap + num <= cap) {
                        list.set(j, new int[] { list.get(j)[0], 0 });
                        newcap += num;
                    } else {
                        list.set(j, new int[] { list.get(j)[0], num - cap + newcap });
                        newcap = cap;
                    }
                    j--;
                }
            }
            System.out.println("4 집 리스트: " + Arrays.deepToString(list.toArray())); //
            System.out.println(list.size());
            answer += list.size()*2;
            removelist(list);
        }

        System.out.println("최종 집 리스트: " + Arrays.deepToString(list.toArray())); //

        return answer;
    }
    // 끝에서부터 빈 리스트 삭제
    public static ArrayList<int[]> removelist(ArrayList<int[]> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i)[0] != 0 || list.get(i)[1] != 0)
                break;
            else {
                list.remove(i);
            }
        }
        return list;
    }
}
