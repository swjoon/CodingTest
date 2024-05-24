package 보석쇼핑;

import java.util.*;

public class gem {
    public static void main(String[] args) {
        solution(new String[] { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" });
    }

    //  1차시도 정확성 통과 , 효율성 시간초과 
    public static int[] solution(String[] gems) {
        int[] answer = new int[2];
        List<int[]> list = new ArrayList<>();
        int size = gemListNum(gems);
        int start = 0;
        int end = 1;
        while (end <= gems.length) {
            if (checkList(gems, start, end, size)) {
                list.add(new int[] { start+1, end, end - start });
                start++;
            } else
                end++;

        }
        Collections.sort(list, (o1,o2)-> o1[2] - o2[2]);
        System.out.println(Arrays.deepToString(list.toArray()));
        answer = new int[]{list.get(0)[0],list.get(0)[1]};
        System.out.println(Arrays.toString(answer));
        return answer;
    }

    public static int gemListNum(String[] gems) {
        Set<String> set = new HashSet<>(Arrays.asList(gems));
        return set.toArray(new String[0]).length;
    }

    public static boolean checkList(String[] gems, int start, int end, int size) {
        String[] nowList = Arrays.copyOfRange(gems, start, end);
        Set<String> set = new HashSet<>(Arrays.asList(nowList));
        return (set.size() == size) ? true : false;
    }
}
