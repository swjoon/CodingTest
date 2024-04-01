package 예상대진표;

public class table {
    public static int solution(int n, int a, int b) {
        System.out.printf("%d, %d, %d \n", n, a, b);

        int answer = 1;

        if (a > b) {
            int x = a;
            a = b;
            b = x;
        }

        while (!(b - a == 1 && (a / 2 != b / 2))) {

            a = cal(a);
            b = cal(b);

            answer = answer + 1;
        }

        System.out.printf("%d \n", answer);

        return answer;
    }

    public static int cal(int num) {

        int x = num % 2;

        if (x == 0) {
            return num = num / 2;
        } else {
            return num = num / 2 + 1;
        }
    }
}
