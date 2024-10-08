package baekjoon.gold.인터넷설치;

import java.util.*;
import java.io.*;

public class Internet {
    // 다익스트라 BFS탐색에서 PriorityQueue에 사용되는 클래스
    static class cable implements Comparable<cable> {
        int next, value;

        public cable(int next, int value) {
            this.next = next;
            this.value = value;
        }

        @Override
        public int compareTo(cable o) {
            return this.value - o.value;
        }
    }

    static int N, P, K;
    static ArrayList<ArrayList<cable>> line = new ArrayList<>(); // 그래프 저장 리스트
    static int[] dis; // 지불 비용보다 큰 인터넷 선 연결되는 횟수 저장하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력값 처리하는 BufferedReader
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 결과값 출력하는 BufferedWriter
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dis = new int[N + 1];

        for (int i = 0; i <= N; i++)
            line.add(new ArrayList<>());

        int end = Integer.MIN_VALUE;
        int start = 0;
        // 그래프 정보 저장 및 지불 비용 최대값 구하기
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int cur = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            end = Math.max(end, value);
            line.get(cur).add(new cable(next, value));
            line.get(next).add(new cable(cur, value));
        }

        int answer = Integer.MIN_VALUE;
        // 이분 탐색으로 중간값(지불 비용)기준 BFS탐색
        while (start <= end) {
            int mid = (start + end) / 2;
            if (bfs(mid)) {
                answer = mid;
                end = mid - 1;
            } else
                start = mid + 1;
        }
        // 이분 탐색 후 만족하는 지불 비용값이 없는 경우
        if (answer == Integer.MIN_VALUE)
            bw.write("-1");
        else // 지불 비용 존재시 최소값
            bw.write(answer + "");
        bw.flush(); // 결과 출력
        bw.close();
        br.close();
    }

    // 다익스트라 BFS 탐색으로 지불 비용에 만족하도록 1 -> N이 가능하는지 확인하는 함수
    static boolean bfs(int mid) {
        PriorityQueue<cable> pq = new PriorityQueue<>();
        pq.add(new cable(1, 0));
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[1] = 0;
        while (!pq.isEmpty()) {
            cable cur = pq.poll();
            int curNode = cur.next;
            int value = cur.value;
            if (dis[curNode] < value)
                continue;
            for (cable next : line.get(curNode)) {
                int nextValue = value;
                if (next.value > mid)
                    nextValue++;
                if (nextValue < dis[next.next]) {
                    dis[next.next] = nextValue;
                    pq.add(new cable(next.next, nextValue));
                }
            }
        }
        // K번 이하로 지불 비용보다 큰 인터넷 선 확인
        return dis[N] <= K;
    }
}
