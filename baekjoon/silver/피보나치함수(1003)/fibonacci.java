import java.util.Scanner;

//1003번문제
public class fibonacci {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int i = 0; i < T; i++) {
			int num = sc.nextInt();
			
			System.out.println(fibo(num-1)+" "+fibo(num));
			
		}
        sc.close();
	}
	
	public static int fibo(int n) {
		
		if(n<0) {
			return 1;
		}
		int[] fib = new int[n+1];
		
		fib[0] = 0;		
		
		if (n>0) {
			fib[1] = 1;
		}
		
		for(int i = 2; i<=n; i++) {
			fib[i] = fib[i-1] + fib[i-2];
		}
		
		return fib[n];
		
	}
}
