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

// 아호-코라식 알고리즘
class AhoCorasick {

    // 트라이 자료구조
    static class TrieNode {
        Map<Character, TrieNode> child = new HashMap<>();
        TrieNode fail;
        List<String> output = new ArrayList<>();
    }

    static TrieNode root;

    public AhoCorasick() {
        root = new TrieNode();
    }

    // 패턴들을 트라이로 build
    // ex) pattern: { abcd, cd }
    public void buildTrieNode(List<String> patternList) {

        for (String pattern : patternList) {
            TrieNode current = root;

            for (char c : pattern.toCharArray()) {
                current = current.child.computeIfAbsent(c, n -> new TrieNode());
            }

            current.output.add(pattern);
        }
        // a -> b -> c -> d <output -> "abcd"> 
        // c -> d <output -> "cd">

        // 각 노드별 불일치시 이동할 노드 연결
        buildFailLinks();
    }

    // bfs로 탐색
    private void buildFailLinks() {
        Deque<TrieNode> queue = new ArrayDeque<>();
    
        // child : { a, c }
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
            // 1. queue [a, c]
            // 2. a.child -> b   b.fail -> root ((b.fail) -> root.child 탐색)   queue [c , b]
            // 3. c.child -> d   d.fail -> root ((d.fail) -> root.child 탐색)   queue [b , d]
            // 4. b.child -> c   c.fail -> root.child -> c ((c.fail) -> root.child 탐색)   queue[d , c]
            // 5. d.child -> null (queue 삭제)   queue[c]
            // 6. c.child -> d   d.fail -> c.fail -> c -> d ((c.fail) -> c.child 탐색) queue[d]
            // 7. d.child -> null (queue 삭제)   queue[] -> 탐색종료 
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

        // pattern : { abcdef, bcdec, dea }
        // ex) abcdea 탐색시 실패 ->  fail을 타고 가서 dea 탐색

        return false;
    }
}