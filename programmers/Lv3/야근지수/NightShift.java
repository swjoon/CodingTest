package 야근지수;

public class NightShift {
    public static void main(String[] args){
        solution(4, new int[]{4,3,3});
        solution(1, new int[]{2,1,2});
        solution(3, new int[]{1,1});
    }

    public static long solution(int n, int[] works) {
        long answer = 0;

        return answer;
    }
}

// works -> 큰 순서대로 정렬 -> 제일큰 값이 두번째로 큰 값보다 작아지면 두번째로 작은값 감소 -> 반복하다 세번째 값과 겹치는 순간 셋이서 반복. -> 같은 루틴 반복.