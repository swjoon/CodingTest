import java.util.*;

public class hotel {

    public static int solution(String[][] book_time) {
        int answer = 0;
        int newbook_time[][] = new int[book_time.length][2];
        ArrayList<int[]> list = new ArrayList<int[]>();
        
        // 문자열 정수값으로 변환해 배열에 저장
        for(int i = 0; i < book_time.length; i++){
            String[] start = book_time[i][0].split(":");
            String[] end = book_time[i][1].split(":");
            
            if(Integer.parseInt(end[1]) >= 50){
                end[0] = String.valueOf(Integer.parseInt(end[0])+1);
                end[1] = "0" + String.valueOf(Integer.parseInt(end[1])-50);
            }else{
                end[1] = String.valueOf(Integer.parseInt(end[1])+10);
            }

            newbook_time[i][0] = Integer.parseInt(start[0]+start[1]);
            newbook_time[i][1] = Integer.parseInt(end[0]+end[1]);

            list.add(newbook_time[i]);
        }

        // 시작시간순으로 정렬
        list.sort(Comparator.comparingInt( o -> o[0]));

        System.out.println(Arrays.deepToString(list.toArray()));


        while(!list.isEmpty()){
            int e1 = list.remove(0)[1];
            for(int i = 0; i < list.size(); i++){
                int s2 = list.get(i)[0];
                if(e1 <= s2){
                    e1 = list.remove(i)[1];
                    i--;
                }
            }
            answer ++;
        }
       



        return answer;
    }

}
