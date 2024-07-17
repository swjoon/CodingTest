package 불량사용자;

import java.util.*;

public class Ban {
    public static void main(String[] args) {
        // solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new
        // String[]{"fr*d*", "abc1**"});
        // solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new
        // String[]{"*rodo", "*rodo", "******"});
        solution(new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
                new String[] { "fr*d*", "*rodo", "******", "******" });
    }

    public static int solution(String[] user_id, String[] banned_id) {
        Set<Set<String>> resultSet = new HashSet<>();

        findBannedCombinations(new HashSet<>(), user_id, banned_id, 0, resultSet);

        return resultSet.size();
    }

    private static void findBannedCombinations(Set<String> currentSet, String[] user_id, String[] banned_id, int index,
            Set<Set<String>> resultSet) {

        // 조합이 ban 목록 개수와 같으면 결과에 추가
        if (index == banned_id.length) {
            resultSet.add(new HashSet<>(currentSet));
            return;
        }

        // 조합생성
        for (String user : user_id) {
            // 값 겹치면 생략
            if (currentSet.contains(user))
                continue;
            if (check(user, banned_id[index])) {
                currentSet.add(user);
                findBannedCombinations(currentSet, user_id, banned_id, index + 1, resultSet);
                currentSet.remove(user);
            }
        }
    }

    // ban 값으로 판단여부 확인
    public static boolean check(String Id, String B_id) {
        if (Id.length() != B_id.length())
            return false;
        for (int i = 0; i < Id.length(); i++) {
            if (B_id.charAt(i) == '*')
                continue;
            if (Id.charAt(i) != B_id.charAt(i))
                return false;
        }
        return true;
    }
}