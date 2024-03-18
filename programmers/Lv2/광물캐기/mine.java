import java.util.*;

public class mine {
    public static void main(String[] args) {

        solution(new int[] { 1, 3, 2 },
                new String[] { "diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone" });
        solution(new int[] { 0, 1, 1 },
                new String[] { "diamond", "diamond", "diamond","diamond","diamond", "iron", "iron", "iron", "iron", "iron", "diamond" });

    }

    public static int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int[][] list;

        int len = minerals.length;
        int dia = picks[0];
        int iron = picks[1];
        int stone = picks[2];
        int pick = dia + iron + stone;
        int j = 0;

        if (len >= pick * 5) {
            list = new int[pick][3];
            for (int i = 0; i < pick * 5; i++) {
                if (minerals[i].equals("diamond")) {
                    list[j][0]++;
                } else if (minerals[i].equals("iron")) {
                    list[j][1]++;
                } else {
                    list[j][2]++;
                }
                if (i % 5 == 4)
                    j++;
            }
        } else {
            list = new int[(len + 4) / 5][3];
            for (int i = 0; i < len; i++) {
                if (minerals[i].equals("diamond")) {
                    list[j][0] += 1;
                } else if (minerals[i].equals("iron")) {
                    list[j][1] += 1;
                } else {
                    list[j][2] += 1;
                }
                if (i % 5 == 4 && j <= len / 5 - 1)
                    j ++;
            }
        }


        Arrays.sort(list, (o2, o1) -> {

            int cmp = Integer.compare(o1[0],o2[0]);
            if (cmp != 0) {
                return cmp;
            }

            cmp = Integer.compare(o1[1], o2[1]);
            if (cmp != 0) {
                return cmp;
            }

            return Integer.compare(o1[2], o2[2]);
        });

   

        System.out.println(Arrays.deepToString(list));
        System.out.println(list.length);

        for(int i = 0; i < list.length; i++){
            if(picks[0] != 0){
                answer += list[i][0] + list[i][1] + list[i][2];
                picks[0]--;
            }else if(picks[1] != 0){
                answer += 5*list[i][0] + list[i][1] + list[i][2];
                picks[1]--;
            }else{
                answer += 25*list[i][0] + 5*list[i][1] + list[i][2];
                picks[2]--;
            }
        }
        System.out.println(answer);

        return answer;
    }

}