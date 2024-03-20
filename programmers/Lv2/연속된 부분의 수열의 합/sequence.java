import java.util.ArrayList;

public class sequence {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        
        ArrayList <int[]> list = new ArrayList<>();
        
        int left = 0;
        int right = 1;
        
        int sum = sequence[0];
        
        while(left < right){
            if(sum == k){
                list.add(new int[] {left,right-1});
                sum -= sequence[left++];
            }else if(sum > k){
                sum -= sequence[left++];
            }else if( right < sequence.length){
                sum += sequence[right++];
            }else break;
        }
        
        answer = list.get(0);
        int now = list.get(0)[1] - list.get(0)[0];
        
        for(int i = 0; i < list.size(); i++){
            int cal = list.get(i)[1] - list.get(i)[0];
            if( cal < now){
                 now = cal;
                 answer = list.get(i);
            }
        
        }
        
        
        return answer;
    }
}
