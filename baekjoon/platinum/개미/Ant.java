package baekjoon.platinum.개미;

import java.util.*;
import java.io.*;

public class Ant {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] antEnergy = new int[N + 1];
        int[] ant = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            antEnergy[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            ant[i] = i;
        }

        Map<Integer, List<RoomInfo>> roomMap = new HashMap<>();

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int nowRoom = Integer.parseInt(st.nextToken());
            int nextRoom = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            roomMap.putIfAbsent(nowRoom, new ArrayList<>());
            roomMap.putIfAbsent(nextRoom, new ArrayList<>());

            roomMap.get(nowRoom).add(new RoomInfo(nextRoom, dist));
            roomMap.get(nextRoom).add(new RoomInfo(nowRoom, dist));
        }

        boolean[] visited = new boolean[N + 1];

        List<History> roomHistory = new ArrayList<>();

        moveAnt(1, ant, antEnergy, roomMap, roomHistory, visited);

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            sb.append(ant[i]).append("\n");
        }

        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb.toString());

        br.close();
    }

    private static void moveAnt(int now, int[] ant, int[] antEnergy, Map<Integer, List<RoomInfo>> roomMap,
            List<History> roomHistory, boolean[] visited) {

        visited[now] = true;

        List<RoomInfo> roomList = roomMap.get(now);

        int size = roomHistory.size();

        int nowDist = size == 0 ? 0 : roomHistory.get(size - 1).distSum;

        if (nowDist <= antEnergy[now]) {
            ant[now] = 1;
        } else {
            for (int i = size - 1; i >= 0; i--) {
                History history = roomHistory.get(i);
                int dist = nowDist - history.distSum;

                if (dist > antEnergy[now]) {
                    break;
                }

                ant[now] = history.room;
            }
        }

        if (roomList == null) {
            return;
        }

        for (RoomInfo room : roomList) {

            if (visited[room.nextRoom]) {
                continue;
            }

            roomHistory.add(new History(room.nextRoom, nowDist + room.dist));
            moveAnt(room.nextRoom, ant, antEnergy, roomMap, roomHistory, visited);
            roomHistory.remove(roomHistory.size() - 1);
        }
    }
}

class History {
    int room;
    int distSum;

    public History(int room, int distSum) {
        this.room = room;
        this.distSum = distSum;
    }
}

class RoomInfo {
    int nextRoom;
    int dist;

    public RoomInfo(int nextRoom, int dist) {
        this.nextRoom = nextRoom;
        this.dist = dist;
    }
}