import java.util.*;

public class bridge{
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for( int i = 0; i < T; i++){
            int N = sc.nextInt();
            int M = sc.nextInt();

            int result = (int)Math.round(factorial(M)/(factorial(N)*factorial(M-N)));

            System.out.println(result);
        }
    }



    public static double factorial(int n){
        double answer = 1;
        
        for( int i = 1; i <= n; i++){
            answer *= i;
        }

        return answer;
    }

}