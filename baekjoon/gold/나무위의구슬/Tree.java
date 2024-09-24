package baekjoon.gold.나무위의구슬;

import java.util.*;
import java.io.*;

public class Tree {
    static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        list.add(new int[] {});

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int leftNode = Integer.parseInt(st.nextToken());
            int rightNode = Integer.parseInt(st.nextToken());
            if (leftNode == -1 && rightNode == -1) {
                list.add(new int[] {});
            } else if (leftNode == -1 && rightNode != -1) {
                list.add(new int[] { rightNode });
            } else if (leftNode != -1 && rightNode == -1) {
                list.add(new int[] { leftNode });
            } else {
                list.add(new int[] { leftNode, rightNode });
            }
        }

        long K = Long.parseLong(br.readLine());

        find(1, K);
    }

    static void find(int node, long k) {
        int type = (int)(k % 2);
        switch (list.get(node).length) {
            case 2:
                if (type == 1) {
                    find(list.get(node)[0], (k + 1) / 2);
                } else {
                    find(list.get(node)[1], k / 2);
                }
                break;
            case 1:
                find(list.get(node)[0], k);
                break;
            default:
                System.out.println(node);
                return;
        }
    }
}
