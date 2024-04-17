import java.util.*;

public class change{

    public static void main(String[] args){
        System.out.println(solution(10, 40, 5));
        System.out.println(solution(10, 40, 30));
        System.out.println(solution(2, 5, 4));
    }

    public static int solution(int x, int y, int n){
        int answer = 0;

        answer = bfs(x,y,n);

        return answer;
    }
// 시간초과

    public static int bfs(int x, int y, int n){
        Queue<Integer> queue = new LinkedList<Integer>();
        int result = 0;

        queue.add(x);

        while(!queue.isEmpty()){
            int[] now = new int[queue.size()];
            for(int i = 0; i < now.length; i++){
                now[i] = queue.poll();
                if(now[i] == y) return result;
            }
            for(int i = 0; i < now.length; i++){
                int testnow = now[i];
                if(testnow + n <= y) queue.add(testnow + n);
                if(testnow * 2 <= y) queue.add(testnow * 2);
                if(testnow * 3 <= y) queue.add(testnow * 3); 
            }
            result++;
        }




        return -1;
    }
}