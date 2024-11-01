package programmers.Lv4.쿠키구입;

public class Cookie {
    public static void main(String[] args) {
        System.out.println("result : " + solution(new int[] { 1, 1, 2, 3 }));
    }

    public static int solution(int[] cookie) {
        int N = cookie.length;
        int[] sumValue = new int[N + 1];

        sumValue[0] = 0;

        for (int i = 1; i <= N; i++) {
            sumValue[i] = sumValue[i - 1] + cookie[i - 1];
        }

        int Max = 0;

        for (int end = 2; end <= N; end++) {
            for (int start = 1; start < end; start++) {
                // 두 아이에게 줄 과자값보다 총값이 작거나 같으면 Max 값을 갱신하지 못함.
                if(sumValue[end] - sumValue[start-1] <= Max * 2){
                    break;
                }
                // < start ~~ middle >  < middle + 1 ~~ end> 비교
                for (int middle = start; middle < end; middle++) {
                    if (sumValue[end] - sumValue[middle] < sumValue[middle] - sumValue[start - 1]) {
                        break;
                    }
                    if (sumValue[end] - sumValue[middle] == sumValue[middle] - sumValue[start - 1]) {
                        Max = Math.max(Max, sumValue[end] - sumValue[middle]);
                        break;
                    }
                }
            }
        }

        return Max;
    }
}