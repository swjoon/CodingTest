package baekjoon.platinum.큰수만들기;

import java.io.*;
import java.util.*;

public class BigNum {
    // 우선순위 규칙 정하기 -> 더했을때 더 큰수
    static PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> (o2 + o1).compareTo(o1 + o2));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            pq.add(st.nextToken());
        }

        if (pq.peek().equals("0")){
            System.out.println(0);
            return;
        }

        while (!pq.isEmpty()) {
            sb.append(pq.poll());
        }

        System.out.println(sb.toString());

    }
}

// public class BigNum {

//     static PriorityQueue<String[]> pq = new PriorityQueue<>((o1, o2) -> {

//         int minLen = Math.min(o1.length, o2.length);

//         for (int i = 0; i < minLen; i++) {
//             int x = Integer.parseInt(o1[i]);
//             int y = Integer.parseInt(o2[i]);
//             if (x != y)
//                 return Integer.compare(y, x);
//         }

//         int now = Integer.parseInt(o1[0]);

//         if (o1.length == o2.length)
//             return 0;

//         if (o1.length > o2.length) {
//             for (int i = minLen; i < o1.length; i++) {
//                 int num = Integer.parseInt(o1[i]);
//                 if (now != num)
//                     return Integer.compare(now, num);
//             }
//         } else if (o1.length < o2.length) {
//             for (int i = minLen; i < o2.length; i++) {
//                 int num = Integer.parseInt(o2[i]);
//                 if (now != num)
//                     return Integer.compare(now, num);
//             }
//         }

//         return 0;
//     });
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringBuilder sb = new StringBuilder();

//         int N = Integer.parseInt(br.readLine());

//         StringTokenizer st = new StringTokenizer(br.readLine());
//         for (int i = 0; i < N; i++) {
//             pq.add(st.nextToken().split(""));
//         }

//         if (pq.peek()[0].equals("0")) {
//             System.out.println("0");
//             return;
//         }

//         while (!pq.isEmpty()) {
//             String[] num = pq.poll();
//             for (int i = 0; i < num.length; i++)
//                 sb.append(num[i]);
//         }

//         System.out.println(sb.toString());

//     }
// }
