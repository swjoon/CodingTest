package 에어컨;

import java.util.*;

public class airconditioner {
    public static void main(String[] args) {
        solution(28, 18, 26, 10, 8, new int[] { 0, 0, 1, 1, 1, 1, 1 });
        solution(-10, -5, 5, 5, 1, new int[] { 0, 0, 0, 0, 0, 1, 0 });
        solution(11, 8, 10, 10, 1, new int[] { 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1 });
    }

    // 1차시도 : bfs는 시간초과. 너무 많은 경우의수를 탐색하려고함. -> visited 시도 ( 메모리 초과 )
    public static int solution(int temperature, int t1, int t2, int a, int b,
            int[] onboard) {
        int answer = 0;
        int sign = 0;
        int last = 0;
        if (temperature > t2) {
            sign = -1;
        } else if (temperature < t1) {
            sign = 1;
        }
        if (temperature < 0 || t1 < 0) {
            t1 += 10;
            t2 += 10;
            temperature += 10;
        }
        for (int i = onboard.length - 1; i > 0; i--) {
            if (onboard[i] == 1) {
                last = i;
                break;
            }
        }
        System.out.println("마지막 i 위치: " + last);
        answer = maxReturn(temperature, t1, t2, a, b, onboard, last, sign);
        System.out.println(answer);
        return answer;
    }

    public static int maxReturn(int temperature, int t1, int t2, int a, int b,
            int[] onboard, int last, int sign) {
        Queue<NowData> queue = new LinkedList<NowData>();
        int min = 100000;
        queue.add(new NowData(0, temperature, 0));
        while (!queue.isEmpty()) {
            NowData nowdata = queue.poll();
            if (nowdata.now == last) {
                if (nowdata.nowTemp >= t1 && nowdata.nowTemp <= t2)
                    min = (min < nowdata.nowPower) ? min : nowdata.nowPower;
                else
                    continue;
            } else if (!(onboard[nowdata.now] == 1 && (nowdata.nowTemp < t1 ||
                    nowdata.nowTemp > t2))) {
                queue.add(new NowData(nowdata.now + 1, nowdata.nowTemp + sign,
                        nowdata.nowPower + a));
                queue.add(new NowData(nowdata.now + 1, nowdata.nowTemp, nowdata.nowPower +
                        b));
                if (nowdata.nowTemp == temperature) {
                    queue.add(new NowData(nowdata.now + 1, nowdata.nowTemp, nowdata.nowPower));
                } else
                    queue.add(new NowData(nowdata.now + 1, nowdata.nowTemp - sign,
                            nowdata.nowPower));
            }
        }
        return min;
    }

}

class NowData {
    int now;
    int nowTemp;
    int nowPower;

    NowData(int now, int nowTemp, int nowPower) {
        this.now = now;
        this.nowTemp = nowTemp;
        this.nowPower = nowPower;
    }
}