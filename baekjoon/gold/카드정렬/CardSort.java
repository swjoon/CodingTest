package baekjoon.gold.카드정렬;

import java.util.*;

public class CardSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 우선순위큐를 이용한 정렬
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            queue.add(sc.nextInt());
        }

        System.out.println(solution(queue));

        sc.close();
    }

    public static int solution(PriorityQueue<Integer> queue) {
        int answer = 0;
        // 묶음이 하나인 경우 비교횟수 0
        if(queue.size() == 1)
            return 0;
        
        // 묶음이 두개인 경우 A+B
        if (queue.size() < 3) {
            while (!queue.isEmpty()) {
                answer += queue.poll();
            }
        } else {
        // 나머지 경우 묶음이 합쳐져서 하나만 남을때 까지 작은 묶음끼리 더하고 비교횟수 저장
            answer = queue.poll() + queue.poll();
            queue.add(answer);
            while (queue.size() > 1) {
                int sum = queue.poll() + queue.poll();
                queue.add(sum);
                answer += sum;
            }
        }

        return answer;
    }
}