package 섬연결하기;

public class Connect {
    public static void main(String[] args){
        solution(4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}});
    }

    public static int solution(int n, int[][] costs) {
        int answer = 0;
        int[] node = new int[n];
        greedy(costs[0][0]);



        return answer;
    }

    public static void greedy(int now){

    }
}
