package baekjoon.platinum.Boggle;

import java.util.*;
import java.io.*;

public class Boggle {
    private static final int SIZE = 4;
    private static final int[] dirX = { 0, 1, 1, 1, 0, -1, -1, -1 };
    private static final int[] dirY = { 1, 1, 0, -1, -1, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int wordCount = Integer.parseInt(br.readLine());

        Map<String, Integer> wordInfo = new HashMap<>();

        TrieNode root = new TrieNode();

        while (true) {
            String word = br.readLine();

            if (word == null || word.trim().isEmpty()) {
                break;
            }

            wordInfo.put(word, parseScore(word.length()));

            insertTrieNode(root, word);
        }

        int boardCount = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < boardCount; testcase++) {
            char[][] map = new char[4][4];
            boolean[][] visited = new boolean[4][4];

            int readLine = 0;

            while (readLine < 4) {
                String line = br.readLine();

                if (line == null || line.trim().isEmpty()) {
                    continue;
                }

                map[readLine++] = line.toCharArray();
            }

            game(root, map, visited, wordInfo);
        }

        br.close();
    }

    private static void game(TrieNode node, char[][] map, boolean[][] visited, Map<String, Integer> wordInfo) {
        Set<String> found = new HashSet<>();

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                findWord(x, y, node, map, visited, found);
            }
        }

        int count = found.size();

        String longWord = "";

        int score = 0;

        for (String word : found) {
            longWord = findLongWord(longWord, word);
            score += wordInfo.get(word);
        }

        System.out.printf("%d %s %d\n", score, longWord, count);
    }

    private static void findWord(int x, int y, TrieNode node, char[][] map, boolean[][] visited, Set<String> found) {

        char nowWord = map[y][x];

        if (!node.children.containsKey(nowWord)) {
            return;
        }

        node = node.children.get(nowWord);

        if (node.isWord) {
            found.add(node.word);
        }

        visited[y][x] = true;

        for (int i = 0; i < 8; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];

            if (nextX < 0 || nextY < 0 || nextX >= SIZE || nextY >= SIZE || visited[nextY][nextX]) {
                continue;
            }

            findWord(nextX, nextY, node, map, visited, found);
        }

        visited[y][x] = false;
    }

    private static void insertTrieNode(TrieNode root, String word) {

        TrieNode node = root;

        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }

        node.isWord = true;
        node.word = word;
    }

    private static String findLongWord(String oldWord, String newWord) {
        int oldLen = oldWord.length();
        int newLen = newWord.length();

        if (oldLen > newLen) {
            return oldWord;
        } else if (oldLen < newLen) {
            return newWord;
        }

        return oldWord.compareTo(newWord) <= 0 ? oldWord : newWord;
    }

    private static int parseScore(int size) {
        switch (size) {
            case 3:
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 3;
            case 7:
                return 5;
            case 8:
                return 11;
            default:
                return 0;
        }
    }
}

class TrieNode {

    Map<Character, TrieNode> children = new HashMap<>();

    boolean isWord = false;

    String word = null;
}