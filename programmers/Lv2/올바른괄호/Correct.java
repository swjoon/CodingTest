package 올바른괄호;

public class Correct {
    public static void main(String[] args) {
        System.out.println(solution("(())()"));
        System.out.println(solution("(()("));
    }

    static boolean solution(String s) {
        String[] input = s.split("");
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (input[i].equals("(")) {
                count ++;
            } else {
                if (count == 0)
                    return false;
                count--;
            }
        }

        if (count == 0) {
            return true;
        }

        return false;
    }
}
