package 기지국설치;

public class BaseStation {
    public static void main(String[] args) {
        solution(11, new int[] { 4, 11 }, 1);
    }

    public static int solution(int n, int[] stations, int w) {
        int answer = 0;
        int width = 2 * w + 1;

        // 설치된 기지국 사이의 빈 공간 구하기 -> 기지국당 커버가능한 범위로 나눠서 최소치 계산
        for (int i = 0; i < stations.length; i++) {
            if (i + 1 >= stations.length)
                break;
            if (stations[i + 1] - stations[i] - width > 0) {
                answer += (int) Math.ceil((double) (stations[i + 1] - stations[i] - width) / width);
            }
        }

        // 1 ~ 첫번째 기지국 사이 빈 공간 구하기
        if(stations[0] - w > 1){
            answer += (int) Math.ceil((double) (stations[0] - w - 1 ) / width);
        }

        // 마지막 기지국 ~ n 사이 빈 공간 구하기
        if(stations[stations.length-1] + w < n){
            answer += (int) Math.ceil((double) (n - (stations[stations.length-1] + w)) / width);
        }

        return answer;
    }
}
