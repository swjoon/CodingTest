package 점찍기;

public class dot {

    public static long solution(int k, int d) {
        long answer = 0;
        long dd = (long) Math.pow(d, 2);

        for (int y = 0; y <= d; y += k) {
            // d^2 - y^2 보다 작은 k의 배수
            long yy = (long) Math.pow(y, 2);
            int num = (int) Math.sqrt(dd - yy) / k + 1;
            answer += num;

        }

        return answer;
    }
}
