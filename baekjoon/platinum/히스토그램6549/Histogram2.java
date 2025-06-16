package baekjoon.platinum.히스토그램6549;

import java.util.*;
import java.io.*;

public class Histogram2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());

            int width = Integer.parseInt(st.nextToken());

            if (width == 0) {
                break;
            }

            int[] height = new int[width];

            for (int i = 0; i < width; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }

            long maxArea = getMaxArea(height, width);

            sb.append(maxArea).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static long getMaxArea(int[] heights, int width) {
        Deque<Integer> stack = new ArrayDeque<>();

        long maxArea = 0;

        for (int i = 0; i <= width; i++) {
            int h = (i == width ? 0 : heights[i]);

            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];

                int w = stack.isEmpty() ? i : i - 1 - stack.peek();

                maxArea = Math.max(maxArea, (long) height * w);
            }

            stack.push(i);
        }

        return maxArea;
    }
}
