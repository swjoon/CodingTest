package 구명보트;

import java.util.*;

public class Lifeboat {
    public static void main(String[] args){
        solution(new int[]{70,50,80,50}, 100);
    }

    public static int solution(int[] people, int limit){
        int answer = 0;

        Arrays.sort(people);

        int start = 0;
        int end = people.length - 1;

        while(start <= end){
            
            if(people[start] + people[end] <= limit){
                start++;
            }
        
            end--;

            answer ++; 
        }
        
        return answer;
    }
}
