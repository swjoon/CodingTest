package 인사고과;

import java.util.*;

public class review {
    public static void main(String[] args){
        System.out.println(solution(new int[][]{{2,2},{1,4},{3,2},{3,2},{2,1}})+"\n");
        System.out.println(solution(new int[][]{{2,2},{3,4},{10,2},{3,2},{2,5},{7,1},{1,1}}));
    }

    public static int solution(int[][] scores) {
        List<int[]> scoreslist = new ArrayList<>();
        List<Integer> scoresnum = new ArrayList<>();
        int answer = 0;
        int max = 0;

        for(int i = 0; i < scores.length; i++){
            if(scores[i][0]> scores[i][1]){ // 작은 수를 [0] 에 넣기.
                int num = scores[i][0];
                scores[i][0] = scores[i][1];
                scores[i][1] = num;
            }

            if(scores[i][0] > max){ // 작은값 중 max 찾기.
                max = scores[i][0];
            }

            scoreslist.add(scores[i]); // list 에 추가
        }

        int[] mainman = new int[]{scores[0][0],scores[0][1]};
        int mainmanscore = scores[0][0] + scores[0][1];
        System.out.println("완호의 점수: " + Arrays.toString(mainman) + " 총점: " + mainmanscore ); //

        System.out.println("현재 배열: " + Arrays.deepToString(scoreslist.toArray())); //

        int comparemax = max;
        
        System.out.println("max: "+ comparemax ); //

        scoreslist.removeIf(o -> o[1] < comparemax);

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
