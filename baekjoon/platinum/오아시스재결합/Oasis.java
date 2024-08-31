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

        N = Integer.parseInt(br.readLine());
        people = new long[N + 1];
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
        boolean check = false;
        while (!stack.isEmpty()) {
            int n = stack.peek();
            if (people[n] < people[now]) {
                n = stack.pop();
                ans += peoplenum[n];
                peoplenum[n] = 1;
                continue;
            }
            if (people[n] == people[now]) {
                stack.pop();
                ans += peoplenum[n];
                peoplenum[now] = peoplenum[n];
                peoplenum[n] = 1;
                check = true;
                continue;
            }
            if (people[n] > people[now]) {
                ans += 1;
                stack.push(now);
                if (check) {
                    peoplenum[now]++;
                }
                return;
            }
        }
        if (check) {
            peoplenum[now]++;
        }
        stack.push(now);
    }
}
