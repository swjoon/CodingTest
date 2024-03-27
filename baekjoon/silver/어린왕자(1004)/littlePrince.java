import java.util.Scanner;

//1003번문제
public class littlePrince {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int n = sc.nextInt();

            int totalNum = 0;

            for (int j = 0; j < n; j++) {
                int cx = sc.nextInt();
                int cy = sc.nextInt();
                int r = sc.nextInt();

                double d1 = Math.sqrt((x1 - cx) * (x1 - cx) + (y1 - cy) * (y1 - cy));
                double d2 = Math.sqrt((x2 - cx) * (x2 - cx) + (y2 - cy) * (y2 - cy));

                if (d1 < r && d2 < r) {

                } else if (d1 < r) {
                    totalNum++;
                } else if (d2 < r) {
                    totalNum++;
                }

                System.out.println(totalNum);

            }
        }
        sc.close();
    }
}