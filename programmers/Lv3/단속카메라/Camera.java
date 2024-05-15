package 단속카메라;

import java.util.*;

public class Camera {
    public static void main(String[] args){
        solution(new int[][] {{-20,-15},{-14,-5},{-18,-13},{-5,-3}});
    }

    public static int solution(int[][] routes) {
        int answer = 1;
        Arrays.sort(routes, (o1,o2) -> o1[0] - o2[0]);
        int last = routes[0][1];
        for( int i = 0; i < routes.length; i++){
            if( last < routes[i][0]){
                answer++;
                last = routes[i][1];
            }else last = (last > routes[i][1])? routes[i][1]: last;
        }
        return answer;
    }
}
