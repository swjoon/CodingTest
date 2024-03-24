package silver.터렛;

import java.util.*;

public class turret {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int r1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int r2 = sc.nextInt();

            double d =  Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

            if (x1 == x2 && y1 == y2 && r1 == r2) {
                System.out.println(-1);
            } else {
                int sum = r1 + r2;
                int diff = Math.abs(r1 - r2);

                if (d == sum || d == diff) {
                    System.out.println(1);
                } else if (d < sum && d > diff) {
                    System.out.println(2);
                } else {
                    System.out.println(0);
                }
            }
        }
        sc.close();
	}
}
