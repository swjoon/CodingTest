package CodingTest.programmers.Lv1.과일장수;

import java.util.*;

class fruit {

    public static int solution(int k, int m, int[] score) {
        
        int answer = 0;      
        Arrays.sort(score);   
        
        for(int i = score.length ; i / m >= 1; i-=m ){
            
            answer += score[i-m] * m;
   
        }
       
        return answer;
    }
}