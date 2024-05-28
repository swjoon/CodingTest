package 디스크컨트롤러;

import java.util.*;

public class DiskController {
    public static void main(String[] args) {
        solution(new int[][] { { 0, 3 }, { 1, 9 }, { 2, 6 } });
        solution(new int[][] { { 7, 8 }, { 3, 5 }, { 8, 6 } });
    }

    public static int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<int[]> list = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> joblist = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        Collections.addAll(list,jobs);
        // 작업 시작시간
        int sTime = list.peek()[0];
        while (!list.isEmpty() || !joblist.isEmpty()) {
            while (!list.isEmpty() && list.peek()[0] <= sTime) {
                joblist.offer(list.poll());
            }
            // 다음작업까지 시간이 비는 경우 다음 작업으로 시간 이동
            if(joblist.isEmpty()){
                sTime = list.peek()[0];
                continue;
            }
            int[] job = joblist.poll();
            sTime += job[1];
            answer += sTime - job[0];
        }

        return answer /= jobs.length;
    }
}
