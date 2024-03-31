import java.util.*;

public class distributed {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int T = sc.nextInt();
        
        for(int i = 0; i < T; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            double data = (double)Math.pow(a,b);

            double result = (double)(data % 10);
            if(result == 0){
                result = 10;
            }

            System.out.println(result);
        }
    }
}
