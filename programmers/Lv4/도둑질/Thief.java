package programmers.Lv4.도둑질;

public class Thief {
    public static void main(String[] args) {

        System.out.println(solution(new int[] { 1, 2, 3, 1 }));

    }

    public static int solution(int[] money) {
        
        int ans = Math.max(DP(money, 1), DP(money, 2));
        
        return ans;
    }
    
    public static int DP(int[] money, int start) {
        int N = money.length;
        int[] dp = new int[N + 1];
        
        if (start == 1) {
            dp[start] = dp[start + 1] = money[start - 1];
        }else{
            dp[start] = money[start - 1];
        }
        
        for (int i = start + 1; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i - 1]);
        }
        
        // 1 시작이면 마지막 집을 못턴다.
        if (start == 1) {
            return dp[N - 1];
        }
        
        return dp[N];
    }
}