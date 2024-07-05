package 숫자게임;

import java.util.*;

public class numgame {
    public static void main(String[] args){
        solution(new int[]{5,1,3,7}, new int[]{2,2,6,8});
    }

    public static int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);
        int a = A.length-1;
        int b = B.length-1;

        while(a > -1 && b > -1){
            if(A[a] >= B[b]) a--;
            else{
                a--;
                b--;
                answer++;
            }
        }
        return answer;
    }
}
