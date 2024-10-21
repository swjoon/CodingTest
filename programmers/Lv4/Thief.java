package programmers.Lv4;

public class Thief {
    public static void main(String[] args) {

        int ans = solution(new int[] { 1, 2, 3, 1 });

        System.out.println(ans);
    }

    public static int solution(int[] money) {

        int ans = Math.max(DP(money, 1), DP(money, 2));

        return ans;
    }

    public static int DP(int[] money, int start) {
        int N = money.length;
        int[] dp = new int[N + 1];

        if (start == 1) {
            dp[1] = dp[2] = money[start - 1];
        } else {
            dp[start] = money[start - 1];
        }

        for (int i = start + 2; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i - 1]);
        }

        if (start == 1) {
            return dp[N - 1];
        }

        return dp[N];
    }
}
