package 아날로그시계;

public class clock {
    public static void main(String[] args) {
        solution(0, 5, 30, 0, 7, 0);
    }

    public static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        if (sAngle(s1) == mAngle(s1, m1) || sAngle(s1) == hAngle(s1, m1, h1))
            answer++;
        answer += cnt(h2, m2, s2) - cnt(h1, m1, s1);
        System.out.println("최종: " + answer);
        return answer;
    }

    public static int cnt(int h2, int m2, int s2) {
        int result = 0;
        int h1 = 0;
        int m1 = 0;
        int s1 = 0;
        if (sAngle(s1) == mAngle(s1, m1) || sAngle(s1) == hAngle(s1, m1, h1))
            result++;
        while (h2 != h1 || m2 != m1 || s2 != s1) {
            s1++;
            if (s1 == 60) {
                s1 = 0;
                m1++;
                if (m1 == 60) {
                    m1 = 0;
                    h1++;
                }
            }
            if (sAngle(s1) == mAngle(s1, m1) || sAngle(s1) == hAngle(s1, m1, h1))
                result++;
            else {
                if (s1 == 0) {
                    if (mAngle(59, m1 - 1) > sAngle(59) && mAngle(s1, m1) < 360)
                        result++;
                    if (hAngle(59, m1 - 1, h1) > sAngle(59) && hAngle(s1, m1, h1) < 360)
                        result++;
                    if (m1 == 0) {
                        if (hAngle(59, 59, h1 - 1) > sAngle(59) && hAngle(s1, m1, h1) < 360)
                            result++;
                    }
                } else {
                    if (mAngle(s1 - 1, m1) > sAngle(s1 - 1) && mAngle(s1, m1) < sAngle(s1))
                        result++;
                    if (hAngle(s1 - 1, m1, h1) > sAngle(s1 - 1) && hAngle(s1, m1, h1) < sAngle(s1))
                        result++;
                }
            }
        }
        return result;
    }

    public static double sAngle(int s1) {
        return 6 * s1;
    }

    public static double mAngle(int s1, int m1) {
        return 6 * m1 + 0.1 * s1;
    }

    public static double hAngle(int s1, int m1, int h1) {
        h1 %= 12;
        return 30 * h1 + 0.5 * m1 + 0.00833 * s1;
    }

}
