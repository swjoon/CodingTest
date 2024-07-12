package 큰수만들기;

import java.util.*;

public class Number {
    public static void main(String[] args){
        solution("1924", 2);
        solution("1231234", 3);
        solution("4177252841", 4);
    }

    public static String solution(String number, int k) {
        String answer = "";
        List<String> list = new ArrayList<>(Arrays.asList(number.split("")));
        int now = 0;

        while(k > 0 || now < number.length()-1-k){
            int check = 0;
            for(int i = now + 1; i <= now + k; i++){
                if(Integer.parseInt(list.get(now))< Integer.parseInt(list.get(i))){
                    list.remove(now);
                    k--;
                    check++;
                    break;
                }
            }
            if(check == 0) now ++;
        }

        for(String lists: list){
            answer += lists;
        }

        System.out.println(answer);

        return answer;
    }
}
