package baekjoon.platinum.오아시스재결합;

import java.util.*;
import java.io.*;

public class Oasis {
    static int N;
    static long ans = 0;
    static long[] people;
    static int[] peoplenum;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 사람 수
        N = Integer.parseInt(br.readLine());

        // 큰 키를 담기위한 배열
        people = new long[N + 1];

        // 같은 키를 가진 사람의 수
        peoplenum = new int[N + 1];

        Arrays.fill(peoplenum, 1);

        for (int i = 1; i <= N; i++) {
            long now = Long.parseLong(br.readLine());
            people[i] = now;
            simulation(i);
        }

        System.out.println(ans);
    }

    static void simulation(int now) {
        while (!stack.isEmpty()) {
            int n = stack.peek();
            // 앞사람보다 클때 앞사람을 제거 (중복된 수를 더하고 1로 초기화)
            if (people[n] < people[now]) {
                n = stack.pop();
                ans += peoplenum[n];
                peoplenum[n] = 1;
                continue;
            }
            // 키가 같다면 앞사람을 제거 후 겹친 수를 더하고 현재사람의 배열로 가져온 뒤 1로 초기화
            if (people[n] == people[now]) {
                stack.pop();
                ans += peoplenum[n];
                peoplenum[now] = peoplenum[n];
                peoplenum[n] = 1;
                peoplenum[now]++;
                continue;
            }
            // 앞사람보다 작다면 한쌍 추가후 스택에 넣고 메소드 종료
            if (people[n] > people[now]) {
                ans += 1;
                stack.push(now);
                return;
            }
        }
        // 내 앞사람이 없다면 스택에 넣고 메소드 종료
        stack.push(now);
    }
}
