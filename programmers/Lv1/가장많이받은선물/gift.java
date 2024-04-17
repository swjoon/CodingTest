package 가장많이받은선물;

import java.util.*;

public class gift {
    public static void main(String[] args) {
        System.out.println(solution(new String[] { "muzi", "ryan", "frodo", "neo" }, new String[] { "muzi frodo",
                "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi" }));
    }

    public static int solution(String[] friends, String[] gifts) {
        HashMap<String,Integer> map = new HashMap<>();
        int answer = 0;

        for(int i =0; i < friends.length; i++){
            map.put(friends[i], i); // 맵에 각 행열 번호매김
        }

        int[][] gift = new int[friends.length][friends.length]; // 선물 2차원 배열
        int[] giftnum = new int[friends.length]; // 선물 지수 배열
        int[] finishgift = new int[friends.length]; // 최종 선물 개수


        for( int i = 0; i < gifts.length; i++){
            String[] name = new String[2];
            name = gifts[i].split(" "); 
            String give = name[0]; // 준사람
            String take = name[1]; // 받은사람
            giftnum[map.get(give)]++;
            giftnum[map.get(take)]--;
            gift[map.get(give)][map.get(take)]++;
        }

        System.out.println("선물 배열: "+Arrays.deepToString(gift));
        System.out.println("선물 지수: "+Arrays.toString(giftnum));

        for(int i = 0; i < friends.length; i++){
            for(int j = i; j < friends.length; j++){
                int give =  map.get(friends[i]);
                if(gift[give][j] < gift[j][give] && give != j){ // give맨이 더 적게 줬을때: 상대방이 받는다
                    finishgift[j]++;
                }else if(gift[give][j] > gift[j][give] && give != j){ // give 맨이 더 많이 줬을때: 받아야한다
                    finishgift[give]++;
                }else{ // 서로 같을때 선물지수 비교
                    if(giftnum[give] < giftnum[j]){ // 선물지수 받는쪽이 크면
                        finishgift[j]++;
                    }else if(giftnum[give] > giftnum[j]){ // 선물지수 주는쪽이 크면
                        finishgift[give]++;
                    }
                }
            }
            System.out.println(i+"번 인간 순서: " + Arrays.toString(finishgift));
        }

        System.out.println("선물받은 수: "+Arrays.toString(finishgift));

        answer = Arrays.stream(finishgift).max().getAsInt();
        return answer;
    }
}
