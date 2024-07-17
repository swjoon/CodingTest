package 불량사용자;

public class Ban {
    public static void main(String[] args) {
        // solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"});
        // solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"*rodo", "*rodo", "******"});
        solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"});
    }

    public static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        

        for (String id : user_id) {
            for (String B_id : banned_id) {
                if (id.length() == B_id.length()) {
                    if(check(id, B_id)) {
                        
                    }
                }
            }
        }

        return answer;
    }

    public static boolean check(String Id, String B_id){
        String[] id = Id.split("");
        String[] b = B_id.split("");
        for(int i = 0; i < id.length; i++){
            if(b[i].equals("*")) continue;
            if(!id[i].equals(b[i])) return false;
        }
        return true;
    }
}