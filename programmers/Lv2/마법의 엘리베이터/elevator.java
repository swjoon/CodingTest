public class elevator {

    public static int solution(int storey) {
        int answer = 0;

        int len = Integer.toString(storey).length();

        for (int i = 1; i <= len + 1; i++) {
            int remain = storey % (int) Math.pow(10, i);
            int div = (int) Math.pow(10, i - 1);

            if (remain < 5 * div) {

                answer += remain / div;
                storey -= remain;

            } else if (remain == 5 * div) {
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
        }
        return answer;
    }
}
