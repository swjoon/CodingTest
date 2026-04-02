package 힌트스테이지;

public class Hint {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {

        int[][] cost = {
            {160, 140, 120, 110, 60},
            {290, 270, 260, 120, 10},
            {160, 130, 120, 60, 20},
            {160, 120, 80, 70, 20},
            {110, 70, 60, 30, 20}
        };

        int[][] hint = {
            {40, 2, 3},
            {40, 5, 3},
            {20, 5, 4},
            {50, 5, 5}
        };

        System.out.println(solution(cost, hint)); // 810

    }

    private static int solution(int[][] cost, int[][] hint) {
        int totalStageN = cost.length;

        int[] hintInfo = new int[totalStageN + 1];

        return dfs(1, 0, totalStageN, hintInfo, cost, hint);
    }

    private static int dfs(int stage, int nowCost, int totalStageN, int[] hintInfo, int[][] cost, int[][] hint) {
        int answer = INF;

        int hintN = hintInfo[stage];

        int nextCost = nowCost + cost[stage - 1][hintN > totalStageN - 1 ? totalStageN - 1 : hintN];

        if (stage == totalStageN) {
            return nextCost;
        }

        for (int i = 0; i < 2; i++) {
            if (i == 0) {

                answer = Math.min(answer, dfs(stage + 1, nextCost, totalStageN, hintInfo, cost, hint));

            } else {
                int hintCost = plusHint(stage, hintInfo, hint);

                answer = Math.min(answer, dfs(stage + 1, nextCost + hintCost, totalStageN, hintInfo, cost, hint));

                minusHint(stage, hintInfo, hint);
            }
        }

        return answer;
    }

    private static int plusHint(int stage, int[] hintInfo, int[][] hint) {

        for (int i = 1; i < hint[0].length; i++) {
            hintInfo[hint[stage - 1][i]]++;
        }

        return hint[stage - 1][0];
    }

    private static void minusHint(int stage, int[] hintInfo, int[][] hint) {

        for (int i = 1; i < hint[0].length; i++) {
            hintInfo[hint[stage - 1][i]]--;
        }
    }
}
