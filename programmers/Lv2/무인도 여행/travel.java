import java.util.ArrayList;
import java.util.Comparator;

public class travel {
    public static int days;
    public static char frame[][];
    
    public int[] solution(String[] maps) {
        int[] answer = {};
        
        days = 0;
        
        ArrayList<Integer> lists = new ArrayList<Integer>();
        
        int n = maps.length;
        int m = maps[0].length();
        
        frame = new char[n][m];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                frame[i][j] = maps[i].charAt(j);
            }
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(dfs(i,j,n,m)){
                    lists.add(days);
                    days = 0;
                }
            }
        }
        
        if(lists.isEmpty()){
            answer = new int[] {-1};
        }else{
            answer = new int[lists.size()];
            lists.sort(Comparator.naturalOrder());
            for(int i = 0; i < lists.size(); i++){
                answer[i] = lists.get(i);
            }
        }
        
        return answer;
    }
    
    
    public static boolean dfs(int i, int j, int n, int m){
        
        if(i <= -1 || j <= -1 || i >= n || j >= m) return false;
        
        if(!(frame[i][j] == 'X')){
            
            int num = Character.getNumericValue(frame[i][j]);
            days += num;
            
            frame[i][j] = 'X';
            
            dfs(i+1,j,n,m);
            dfs(i-1,j,n,m);
            dfs(i,j+1,n,m);
            dfs(i,j-1,n,m);
            
            return true;
        }
        
        return false;
        
    }
}
