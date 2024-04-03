import java.util.*;

public class score{

    public static void main(String[] args){
        System.out.println(Arrays.toString(solution(new String[] {"may", "kein", "kain", "radi"}, new int[]{5, 10, 1, 3}, new String[][]{{"may", "kein", "kain", "radi"},{"may", "kein", "brin", "deny"},{"kon", "kain", "may", "coni"}})));
    }

    public static int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        HashMap<String,Integer> map = new HashMap<>();

        for(int i  = 0; i < name.length; i++){
            map.put(name[i],yearning[i]);
        }


        for( int i = 0; i < photo.length; i++){
            int sum = 0;
            for( int j = 0; j < photo[i].length; j++){
                if(map.get(photo[i][j]) == null){
                    sum += 0;
                }else sum += map.get(photo[i][j]);
            }
            answer[i] = sum;
        }




        return answer;
    }
}