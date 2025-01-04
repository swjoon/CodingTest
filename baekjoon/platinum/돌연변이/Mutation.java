package baekjoon.platinum.돌연변이;

import java.util.*;
import java.io.*;

public class Mutation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < T; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int DNALength = Integer.parseInt(st.nextToken());
            int markerLength = Integer.parseInt(st.nextToken());
            String DNA = br.readLine();
            String marker = br.readLine();

            AhoCorasick ahoCorasick = new AhoCorasick();

            // Test (buildMutantSet())
            // ahoCorasick.buildMutantSet(marker, markerLength).stream().forEach(System.out::println);

            ahoCorasick.buildTrieNode(marker, markerLength);

            sb.append(ahoCorasick.search(DNA)).append("\n");

        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}

class AhoCorasick {

    class TrieNode {
        Map<Character, TrieNode> child = new HashMap<>();
        TrieNode fail;
        boolean isTrue = false;
    }

    static TrieNode root;

    public AhoCorasick() {
        root = new TrieNode();
    }

    public void buildTrieNode(String marker, int markerLength) {

        for (String s : buildMutantSet(marker, markerLength)) {
            TrieNode current = root;

            for (char c : s.toCharArray()) {
                current = current.child.computeIfAbsent(c, n -> new TrieNode());
            }

            current.isTrue = true;
        }

        buildFailLinks();
    }

    // marker 돌연변이 set build
    private Set<String> buildMutantSet(String marker, int markerLength) {
        Set<String> markerList = new HashSet<>();
        markerList.add(marker);

        for (int start = 0; start < markerLength; start++) {
            StringBuilder sb = new StringBuilder(marker.substring(start, markerLength));
            sb.reverse();
            for (int end = markerLength; end > start + 1; end--) {
                StringBuilder mutant = new StringBuilder();
                mutant.append(marker.substring(0, start)).append(sb.toString())
                        .append(marker.substring(end, markerLength));
                markerList.add(mutant.toString());
                sb.deleteCharAt(0);
            }
        }

        return markerList;
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
                TrieNode child = entry.getValue();

                TrieNode fail = current.fail;

                while (fail != null && !fail.child.containsKey(c)) {
                    fail = fail.fail;
                }

                if (fail != null) {
                    child.fail = fail.child.get(c);
                } else {
                    child.fail = root;
                }

                queue.add(child);
            }
        }
    }

    public int search(String DNA) {
        TrieNode current = root;
        int num = 0;

        for (char c : DNA.toCharArray()) {

            while (current != root && !current.child.containsKey(c)) {
                current = current.fail;
            }

            if (current.child.containsKey(c)) {
                current = current.child.get(c);
            }

            if (current.isTrue) {
                num++;
            }
        }

        return num;
    }
}