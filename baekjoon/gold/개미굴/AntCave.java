package baekjoon.gold.개미굴;

import java.util.*;
import java.io.*;

public class AntCave {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int infoNumber = Integer.parseInt(br.readLine());

        TreeMap<String, String> caveMap = new TreeMap<>();

        for (int info = 0; info < infoNumber; info++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dep = Integer.parseInt(st.nextToken());

            StringBuilder sb = new StringBuilder();

            for (int layer = 0; layer < dep; layer++) {
                String food = st.nextToken();
                sb.append(food);
                caveMap.put(sb.toString(), addLayer(layer) + food);
            }
        }

        caveMap.values().stream().forEach(System.out::println);
    }

    private static String addLayer(int layer) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < layer; i++) {
            sb.append("--");
        }
        return sb.toString();
    }
}
