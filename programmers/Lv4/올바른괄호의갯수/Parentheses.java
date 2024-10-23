package programmers.Lv4.올바른괄호의갯수;

public class Parentheses {
    public static void main(String[] args) {
        System.out.println(solution(14));

    }

    public static int solution(int n) {
        char[] p = new char[] { '{', '}' };
        int[] ans = new int[1];
        int countA = 0;
        int countB = 0;

        DFS(p, n, countA, countB, 0, ans);

        return ans[0];
    }

    public static void DFS(char[] pt, int n, int countA, int countB, int num, int[] ans) {
        // '{' 괄호 전부 소진시 완성 count++ 
        if (countA == n) {
            ans[0]++;
            return;
        }

        for (int i = 0; i < 2; i++) {
            if (pt[i] == '{') {
                countA++;
                DFS(pt, n, countA, countB, num + 1, ans);
                countA--;
            } else {
                // '{' 보다 '}' 괄호가 더 많을수는 없다
                if (countA > countB) {
                    countB++;
                    DFS(pt, n, countA, countB, num + 1, ans);
                    countB--;
                }
            }
        }
    }
}
