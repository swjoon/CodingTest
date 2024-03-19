package CodingTest.programmers.Lv1.과일장수;
import java.util.*;

class fruit {

    public static void main(String[] args){
        solution(3,4,new int[]{1,2,3,1,2,3,1});
    }

    public static int solution(int k, int m, int[] score) {
        
        int answer = 0;      
        Arrays.sort(score);   
        
        for(int i = score.length ; i / m >= 1; i-=m ){
            
            answer += score[i-m] * m;
   
        }
       
        return answer;
    }
}