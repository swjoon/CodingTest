import java.util.*;

public class set {
    
    public static int[] solution(int n, int s) {
        int[] answer = {};
        
        
        int num = (int) (s/n);
        
        int remain = (int) s%n;
        
        if(s<n){
            answer = new int[] {-1};
        }else{
            answer = new int[n];
            
            Arrays.fill(answer, num);
        
            for (int i = 0; i < remain; i++){
                answer[i]++;
            }
        
            Arrays.sort(answer);
        }
        
        return answer;
    }

}
