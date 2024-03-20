import java.util.*;

public class circle{
    public static void main(String[] args){

    }

    public long solution(int r1, int r2) {
        long answer = 0;
        
        int x1 = 0;
        int x2 = 0;
        
        for(int y = 1; y <= r2; y++ ){
            if(y<r1){
                x1 = (int)Math.ceil(Math.sqrt((long)Math.pow(r1,2) - (long) Math.pow(y,2)));
            }else x1 = 0;
            
            x2 = (int)Math.floor(Math.sqrt((long) Math.pow(r2,2) - (long) Math.pow(y,2)));
            
            answer += (long)(x2-x1)+1;
        }
        
        answer *= 4;
        
        
        return answer;
    }
}