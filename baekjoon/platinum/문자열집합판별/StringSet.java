package baekjoon.platinum.문자열집합판별;

import java.util.*;
import java.io.*;

public class StringSet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int patternN = Integer.parseInt(br.readLine());
        List<String> patternL = new ArrayList<>();

        for (int i = 0; i < patternN; i++) {
            patternL.add(br.readLine());
        }

        AhoCorasick aho = new AhoCorasick();

        aho.buildTrieNode(patternL);

        int stringN = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < stringN; i++) {
            sb.append(aho.search(br.readLine()) ? "YES" : "NO").append("\n");
        }

        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb);
    }
}

class AhoCorasick {

    static class TrieNode {
        Map<Character, TrieNode> child = new HashMap<>();
        TrieNode fail;
        List<String> output = new ArrayList<>();
    }

    static TrieNode root;

    public AhoCorasick() {
        root = new TrieNode();
    }

    public void buildTrieNode(List<String> patternList) {

        for (String pattern : patternList) {
            TrieNode current = root;

            for (char c : pattern.toCharArray()) {
                current = current.child.computeIfAbsent(c, n -> new TrieNode());
            }

            current.output.add(pattern);
        }

        buildFailLinks();
    }

    private void buildFailLinks() {
        Deque<TrieNode> queue = new ArrayDeque<>();

        for (TrieNode child : root.child.values()) {
            child.fail = root;
            queue.add(child);
        }

        while (!queue.isEmpty()) {
            TrieNode current = queue.poll();

            for (Map.Entry<Character, TrieNode> entry : current.child.entrySet()) {
                char c = entry.getKey();
                TrieNode childNode = entry.getValue();

                TrieNode failNode = current.fail;

                while (failNode != null && !failNode.child.containsKey(c)) {
                    failNode = failNode.fail;
                }

                if (failNode != null) {
                    childNode.fail = failNode.child.get(c);
                } else {
                    childNode.fail = root;
                }

                childNode.output.addAll(childNode.fail.output);

                queue.add(childNode);
            }

        }
    }

    public boolean search(String text) {
        TrieNode current = root;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            while (current != root && !current.child.containsKey(c)) {
                current = current.fail;
            }

            if (current.child.containsKey(c)) {
                current = current.child.get(c);
            }

            if (current.output.size() >= 1) {
                return true;
            }
        }

        return false;
    }
}