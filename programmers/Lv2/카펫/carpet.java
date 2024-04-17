package 카펫;

import java.util.*;

public class carpet {
    public static void main(String[] args){
        System.out.println(Arrays.toString(solution(10, 2)));
    }

    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        for(int i = 1; i < yellow/2 ; i++){
            int y = 0;
            if(yellow % i == 0){
                y = yellow / i;
                if((y+i)*2 + 4 == brown){
                    if(y > i){
                        answer[0] = y+2;
                        answer[1] = i+2;
                        break;
                    }else if(y < i){
                        answer[1] = y+2;
                        answer[0] = i+2;
                        break;
                    }else{
                        answer[0] = y+2;
                        answer[1] = i+2;
                        break;
                    }
                }
            }
        }

        return answer;
    }

}
