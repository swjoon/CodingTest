public class elevator {
    public static void main(String[] args) {
        solution(16);
        solution(2554);
        solution(545);

    }

    public static int solution(int storey) {
        int answer = 0;

        int len = Integer.toString(storey).length();

        for (int i = 1; i <= len+1; i++) {
            int remain = storey % (int) Math.pow(10, i);
            int div = (int) Math.pow(10, i - 1);

            if (remain < 5 * div) {

                answer += remain / div;
                storey -= remain;

            } else if (remain == 5 * div) {
                System.out.println("조건절: "+(storey % (int) Math.pow(10, i + 1)) / (int) Math.pow(10, i));
                if ((storey % (int) Math.pow(10, i + 1)) / (int) Math.pow(10, i) < 5) {
                    
                    answer += remain / div;
                    storey -= remain;
                } else {
                    answer += 10 - remain / div;
                    storey += (int) Math.pow(10, i) - remain;
                }

            } else {

                answer += 10 - remain / div;
                storey += (int) Math.pow(10, i) - remain;
            }

            System.out.println("storey: " + storey + " answer: " + answer);
        }

        System.out.println("final: " + answer);
        return answer;
    }
}
