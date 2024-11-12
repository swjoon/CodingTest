package baekjoon.gold.소문난칠공주;

import java.io.*;
import java.util.Arrays;

public class SevenPrincess {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] map = new char[5][5];

        for(int y = 0; y < 5; y++){
            map[y] = br.readLine().toCharArray();
        }

        for(int y = 0; y < 5; y++){
            System.out.println(Arrays.toString(map[y]));
        }
    }
}
