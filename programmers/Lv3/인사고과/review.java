package 인사고과;

import java.util.*;

public class review {
    public static void main(String[] args) {
        System.out.println(solution(new int[][] { { 2, 2 }, { 1, 4 }, { 3, 2 }, { 3, 2 }, { 2, 1 } }));
    }

    public static int solution(int[][] scores) {
        List<int[]> scoreslist = new ArrayList<>();
        int answer = 0;
        scoreslist.add(scores[0]);
        // 기준보다 총점이 큰 사람만 리스트 집어넣기. -> 반복문 최대한 적게
        for (int i = 0; i < scores.length; i++) {
            if ((scores[0][0] + scores[0][1]) < (scores[i][0] + scores[i][1])) {
                scoreslist.add(scores[i]);
            }
        }
        // 조건 만족.
        for (int i = 0; i < scoreslist.size(); i++) {
            for (int j = 0; j < scoreslist.size(); j++) {
                if (scoreslist.get(0)[0] < scoreslist.get(j)[0] && scoreslist.get(0)[1] < scoreslist.get(j)[1])
                    return -1;
                if (scoreslist.get(i)[0] < scoreslist.get(j)[0] && scoreslist.get(i)[1] < scoreslist.get(j)[1]) {
                    scoreslist.remove(i);
                    i--;
                    break;
                }
            }
        }
        answer = scoreslist.size();
        return answer;
    }
}
