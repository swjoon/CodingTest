package 인사고과;

import java.util.*;

public class review {
    public static void main(String[] args){
        System.out.println(solution(new int[][]{{2,2},{1,4},{3,2},{3,2},{2,1}})+"\n");
        System.out.println(solution(new int[][]{{2,2},{3,4},{10,2},{3,2},{2,5},{7,1},{1,1}}));
    }


    // 정확성 88/100 -> 시간 초과 3 -> 2중for문 비효율적이라 생각.
    public static int solution(int[][] scores) {
        List<int[]> scoreslist = new ArrayList<>();
        List<Integer> scoresnum = new ArrayList<>();
        int answer = 0;

        int[] mainman = new int[]{scores[0][0],scores[0][1]};
        int mainmanscore = scores[0][0] + scores[0][1];
        System.out.println("현재 배열: " + Arrays.deepToString(scores)); //
        
        for(int i = 0; i < scores.length; i++){
            scoreslist.add(scores[i]); // list 에 추가
            for(int j = 0; j < scores.length; j++){
                if(scores[0][0]<scores[j][0] && scores[0][1] < scores[j][1]) return -1;
                if(scores[i][0]<scores[j][0] && scores[i][1] < scores[j][1]) {
                    scoreslist.remove(scores[i]);
                    break;
                }                
            }
        }

        System.out.println("완호의 점수: " + Arrays.toString(mainman) + " 총점: " + mainmanscore ); //

        System.out.println("삭제 후 배열: " + Arrays.deepToString(scoreslist.toArray())); //

        if(!Arrays.equals(scoreslist.get(0), mainman)){
            return -1;
        }

        for(int[] list: scoreslist){
            int sum = list[0] + list[1];
            scoresnum.add(sum);
        }

        scoresnum.sort((o1,o2)->o2 - o1 );

        System.out.println("점수 순위: " + Arrays.toString(scoresnum.toArray()));

        for(int i = 0; i < scoresnum.size();i++){
            if(scoresnum.get(i) == mainmanscore){
                answer = i+1; 
                break;
            };
        }

        return answer;
    }
}
// test1
// 1. scores에서 인사고과, 동료평가중 큰값과 작은 값 구분. 후 작은값 max 추출, 큰값 추출  -> 문제 잘못이해
// 2. 작은값 max 보다 작은 큰값 배열에서 제외

// test2
// 이중 for문 비효율적.

// test3
// 