package 순위;

import java.util.*;

public class Rank {
    public static void main(String[] args){
        solution(5, new int[][]{{4,3},{4,2},{3,2},{1,2},{2,5}});
    }

    public static int solution(int n, int[][] results) {
        int answer = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int[] result: results){
            int win = result[0];
            int lose = result[1];
        }



        return answer;
    }
}
