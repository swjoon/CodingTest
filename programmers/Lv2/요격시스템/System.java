package 요격시스템;

import java.util.*;

public class System {

    public static int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (a1, a2) -> a1[1] - a2[1] );
        
        int now = 0;
        
        for( int i = 0; i < targets.length; i++){
            if(now <= targets[i][0]){
                now = targets[i][1];
                answer++;
            }
        }
        
        return answer;
    }
}
