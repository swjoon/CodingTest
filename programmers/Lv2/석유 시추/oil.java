import java.util.*;

// DFS 알고리즘을 사용한 풀이법 -> 재귀를 사용한방법 (효율성 테스트 불합) 240325

public class oil{

    public static void main(String[] args){
        System.out.println(solution(new int[][] {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}}));
    }
    
    static int sum;

    public static int solution(int[][] land) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int answer = 0;
        int xlen = land[0].length;
        int ylen = land.length;
        

        for( int i = 0; i < xlen; i++){
            sum = 0; 
            int[][] newland = new int[ylen][xlen];

            for (int j = 0; j < ylen; j++) {
                System.arraycopy(land[j], 0, newland[j], 0, xlen);
            }

            for( int j = 0; j < ylen; j++){
               
                dfs(i, j, xlen, ylen, newland);
            }

            list.add(sum);
            
        }

        answer = Collections.max(list);

        return answer;
    }

    public static void dfs(int x, int y,int xlen, int ylen, int[][] newland){

        if(x < 0 || y < 0 || x >= xlen || y >= ylen) return;

        if(newland[y][x] == 1){

            newland[y][x] = 0;

            sum++;

            dfs( x-1, y, xlen, ylen, newland);
            dfs( x+1, y, xlen, ylen, newland);
            dfs( x, y-1, xlen, ylen, newland);
            dfs( x, y+1, xlen, ylen, newland);
            return ;
        }
    }
}