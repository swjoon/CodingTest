package 이중우선순위큐;

import java.util.*;

public class Queuee {
    public static void main(String[] args){
        // solution(new String[]{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"});
        solution(new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"});
    }
    // priority queue
    public static int[] solution(String[] operations) {
        ArrayList<Integer> list = new ArrayList<>();
        String[] splitString = new String[2];
        int[] answer = new int[]{0,0};
        for(int i = 0; i < operations.length; i++){
            splitString = operations[i].split(" ");
            int num = Integer.parseInt(splitString[1]);

            if( splitString[0].equals("I")){
                if(list.isEmpty()){
                    list.add(num);
                }else{
                    for(int j = 0; j < list.size(); j++){
                        if(list.get(j) < num){
                            list.add(j, num);
                            break;
                        }
                        if(j == (list.size()-1)) {
                            list.add(num);
                            break;
                        }
                    }
                }
            }else if(splitString[0].equals("D")){
                if(list.isEmpty()) continue;
                if(num == 1){
                    list.remove(0);
                }else{
                    list.remove(list.size()-1);
                }
            }
            System.out.println(i+ "회차 리스트: "+list.toString());
        }

        if(!list.isEmpty()){
            answer[0] = list.get(0);
            answer[1] = list.get(list.size()-1);
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }
}
