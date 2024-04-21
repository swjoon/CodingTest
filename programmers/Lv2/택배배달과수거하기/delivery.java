package 택배배달과수거하기;

import java.util.Stack;

public class delivery {
    public static void main(String[] args) {
        System.out.println("정답: " + solution(4, 5, new int[] { 1, 0, 3, 1, 2 }, new
        int[] { 0, 3, 0, 4, 0 })); // 16
    }
    //TEST2
    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        Stack<Integer> deliveryStack = new Stack<>();
        Stack<Integer> pickupsStack = new Stack<>();
        long answer = 0;
        for (int i = 0; i < deliveries.length; i++) {
            deliveryStack.push(deliveries[i]);
            pickupsStack.push(pickups[i]);
        }
        while (!(deliveryStack.isEmpty() && pickupsStack.isEmpty())) {
            int max = Integer.max(checkmax(deliveryStack), checkmax(pickupsStack));
            answer += max*2;
            remove(deliveryStack, cap);
            remove(pickupsStack, cap);
        }
        return answer;
    }
    // 배달 수거중 먼곳 체크
    public static int checkmax(Stack<Integer> stack) {
        int max = 0;
        while (!stack.isEmpty()) {
            int stacknow = stack.pop();
            if (stacknow != 0) {
                stack.push(stacknow);
                max = stack.size();
                break;
            }
        }
        return max;
    }
    // 이번턴 배달 수거 행동
    public static void remove(Stack<Integer> stack, int cap){
        while (!stack.isEmpty()) {
            if(cap > 0){
                int num = stack.pop();
                int remain = num - cap;
                if( remain > 0){
                    stack.push(remain);
                    break;
                }else cap -= num;
            }else{
                break;
            }
        }
    }
}
// TEST 1 시간초과 3개
// public static long solution(int cap, int n, int[] deliveries, int[] pickups)
// {
// ArrayList<int[]> list = new ArrayList<>();
// long answer = 0;
// for (int i = 0; i < deliveries.length; i++) {
// list.add(new int[] { deliveries[i], pickups[i] });
// }
// System.out.println("cap: " + cap ); //
// System.out.println("1 집 리스트: " + Arrays.deepToString(list.toArray())); //

// // 제일 먼거리의 집에서 부터 배달과 수거없는 집 목록 제거.
// removelist(list);

// System.out.println("2 집 리스트: " + Arrays.deepToString(list.toArray())); //
// while (!list.isEmpty()) {
// int newcap = cap;
// // 제일 먼곳부터 배달
// int i = list.size() - 1;
// int j = list.size() - 1;
// while (newcap > 0) {
// if(i < 0){
// newcap = 0;
// }else{
// int num = list.get(i)[0];
// if (newcap > num) {
// list.set(i, new int[] { 0, list.get(i)[1] });
// newcap -= num;
// } else {
// list.set(i, new int[] { list.get(i)[0] - newcap, list.get(i)[1] });
// newcap = 0;
// }
// i--;
// }
// }
// System.out.println("3 집 리스트: " + Arrays.deepToString(list.toArray())); //
// while (newcap < cap) {
// if (j < 0) {
// newcap = cap;
// }else{
// int num = list.get(j)[1];
// if (newcap + num <= cap) {
// list.set(j, new int[] { list.get(j)[0], 0 });
// newcap += num;
// } else {
// list.set(j, new int[] { list.get(j)[0], num - cap + newcap });
// newcap = cap;
// }
// j--;
// }
// }
// System.out.println("4 집 리스트: " + Arrays.deepToString(list.toArray())); //
// System.out.println(list.size());
// answer += list.size()*2;
// removelist(list);
// }

// System.out.println("최종 집 리스트: " + Arrays.deepToString(list.toArray())); //

// return answer;
// }
// // 끝에서부터 빈 리스트 삭제
// public static ArrayList<int[]> removelist(ArrayList<int[]> list) {
// for (int i = list.size() - 1; i >= 0; i--) {
// if (list.get(i)[0] != 0 || list.get(i)[1] != 0)
// break;
// else {
// list.remove(i);
// }
// }
// return list;
// }
