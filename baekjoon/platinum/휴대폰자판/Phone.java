package baekjoon.platinum.휴대폰자판;

import java.util.*;
import java.io.*;

public class Phone {
    static int ans = 0;

    static class TrieNode {

        Map<Character, TrieNode> child;
        boolean isEnd;

        public TrieNode() {
            this.child = new HashMap<>();
            this.isEnd = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = null;

        while ((input = br.readLine()) != null) {
            int N = Integer.parseInt(input);

            TrieNode root = new TrieNode();

            Set<Character> start = new HashSet<>();

            for (int i = 0; i < N; i++) {
                String s = br.readLine().trim();
                start.add(s.charAt(0));

                insert(root, s);
            }

            ans = 0;

            for (char c : start) {
                dfs(root.child.get(c), 1);
            }

            sb.append(String.format("%.2f", (float) ans / N, args)).append("\n");
        }
        System.out.print(sb);
    }

    private static void insert(TrieNode root, String s) {

        TrieNode node = root;

        for (char c : s.toCharArray()) {
            node = node.child.computeIfAbsent(c, n -> new TrieNode());
        }

        node.isEnd = true;
    }

    private static void dfs(TrieNode node, int count) {

        if (node.isEnd) {
            ans += count;
        }

        int size = node.child.size();

        for (Map.Entry<Character, TrieNode> entry : node.child.entrySet()) {

            TrieNode child = entry.getValue();

            if (node.isEnd || size > 1) {
                dfs(child, count + 1);
            } else {
                dfs(child, count);
            }
        }
    }
}