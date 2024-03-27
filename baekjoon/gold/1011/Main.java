import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for( int i = 0; i < T; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();

            System.out.println(solution(y-x));
        }

    }

    public static int solution(int n) {
        int answer = 0;
        int num = 0;
        int i = 1;

        while(n > 0){

            n -= i;

            num++;

            if( num % 2 == 0){
                i++;
            }
            System.out.println("중간: " + answer);
            answer += 1;
        }


        return answer;
    }
}