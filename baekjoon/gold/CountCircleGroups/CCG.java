package baekjoon.gold.CountCircleGroups;

import java.util.*;
import java.io.*;

public class CCG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < T; testcase++) {
            int N = Integer.parseInt(br.readLine());
            List<Circle> list = new ArrayList<>();
            list.add(new Circle(0, 0, 0));
            for (int c = 0; c < N; c++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                list.add(new Circle(x, y, r));
            }
            System.out.println(solution(N, list));
        }
    }

    private static int solution(int N, List<Circle> list) {
        int[] parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int cA = 1; cA < N; cA++) {
            Circle circleA = list.get(cA);
            for (int cB = cA + 1; cB <= N; cB++) {
                Circle circleB = list.get(cB);

                double dist = (double)Math.sqrt(Math.pow(circleB.x - circleA.x, 2) + Math.pow(circleB.y - circleA.y, 2));
                double rSum = circleA.r + circleB.r;

                if (Double.compare(dist, rSum) <= 0) {
                    if(findParent(parent, cA) != findParent(parent, cB)){
                        union(parent, cA, cB);
                    }
                }
            }
        }

        for(int i = 1; i <= N; i++){
            findParent(parent, i);
        }

        Set<Integer> set = new HashSet<>();

        for(int i = 1; i <= N; i++){
            set.add(parent[i]);
        }

        return set.size();
    }

    private static void union(int[] parent, int a, int b) {
        int parentA = findParent(parent, a);
        int parentB = findParent(parent, b);

        if(parentA <= parentB){
            parent[parentB] = parent[parentA];
        }else{
            parent[parentA] = parent[parentB];
        }
    }

    private static int findParent(int[] parent, int a){
        if(parent[a] == a){
            return a;
        }
        return parent[a] = findParent(parent, parent[a]);
    }

    static class Circle {
        int x, y, r;

        public Circle(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }
}
