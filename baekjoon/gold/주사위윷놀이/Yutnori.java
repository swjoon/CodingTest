package baekjoon.gold.주사위윷놀이;

import java.util.*;
import java.io.*;

public class Yutnori {
    static int ans = 0;
    static int[] horse = new int[4];
    static int[] seq = new int[10];
    static int[] map = { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 13, 16, 19, 22,
            24, 28, 27, 26, 25, 30, 35 };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 10; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        DFS(0, 0);

        System.out.println(ans);
    }

    static void DFS(int cnt, int dep) {

        if (dep == 10) {
            ans = Math.max(ans, cnt);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int beforemove = horse[i];
            int aftermove = route(horse[i], seq[dep]);

            if (horse[i] > 31) {
                continue;
            }

            if (aftermove < 32 && check(i, aftermove)) {
                continue;
            }

            horse[i] = aftermove;
            if (aftermove < 32) {
                DFS(cnt + map[aftermove], dep + 1);
            } else {
                DFS(cnt, dep + 1);
            }
            horse[i] = beforemove;
        }
    }

    static boolean check(int now, int location) {
        for (int i = 0; i < 4; i++) {
            if (horse[i] == location) {
                return true;
            }
        }
        return false;
    }

    static int route(int now, int dist) {
        if (now == 5) {
            if (dist <= 3) {
                return 20 + dist;
            } else {
                return 28 + dist - 3;
            }
        }
        if (now == 10) {
            if (dist <= 2) {
                return 23 + dist;
            } else {
                return 28 + dist - 2;
            }
        }
        if (now == 15) {
            return 25 + dist;
        }
        if (20 < now && now <= 23) {
            if (now + dist < 24) {
                return now + dist;
            } else {
                return (5 + now + dist == 32) ? 20 : 5 + now + dist;
            }
        }
        if (23 < now && now <= 25) {
            if (now + dist < 26) {
                return now + dist;
            } else {
                return (3 + now + dist == 32) ? 20 : 3 + now + dist;
            }
        }
        if (now >= 26) {
            return (now + dist == 32) ? 20 : now + dist;
        }
        return (now + dist > 20) ? 32 : now + dist;
    }
}
