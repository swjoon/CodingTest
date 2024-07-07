package 구명보트;

import java.util.*;

public class Lifeboat {
    public static void main(String[] args){
        solution(new int[]{70,50,80,50}, 100);
    }

    public static int solution(int[] people, int limit){
        int answer = 0;

        Arrays.sort(people);

        int startIdx = 0;  
        int endIdx = people.length -1;  
        
        while (startIdx <= endIdx) {
            
            if (people[startIdx] + people[endIdx] <= limit) { 
                startIdx++;                                    
            }
            
            endIdx--;                                       
            
            answer++;										
        }
        System.out.println(answer);
        
        return answer;
    }
}
