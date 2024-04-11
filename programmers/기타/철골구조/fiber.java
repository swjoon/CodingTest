package CodingTest.programmers.기타.철골구조;

import java.util.*;

public class fiber {
    public static void main(String[] args) {

        int xlength = 100;
        int ylength = 100;
        int bordersize = 5;
        int fibersize = 2;
        int xnumber = 4;
        int ynumber = 3;

        System.out.println(Arrays.deepToString(solution(xlength, ylength, bordersize, fibersize, xnumber, ynumber)));

    }

    public static double[][] solution(int xlength, int ylength, int bordersize, int fibersize, int xnumber, int ynumber) {

        int total = (xnumber + ynumber - 2) * 2;

        double[][] coordinate = new double[total][2];
        int[] start = new int[] {bordersize + fibersize, - bordersize - fibersize };
        
        double xinterval = Math.round(((double)(xlength - 2 * (bordersize + fibersize)) / (xnumber - 1)) * 10) / 10.0;
        double yinterval = Math.round(((double)(ylength - 2 * (bordersize + fibersize)) / (ynumber - 1)) * 10) / 10.0;

        int now = 0;

        coordinate[now][0] = start[0];
        coordinate[now][1] = start[1];

        for (int i = 0; i < xnumber; i++) {
            coordinate[now][0] = start[0] + (int)(i * xinterval);
            coordinate[now][1] = start[1];
            now++;          
        }
        
        for (int i = 1; i < ynumber; i++) {
            coordinate[now][0] = start[0] + (int)((xnumber - 1) * xinterval);
            coordinate[now][1] = start[1] - (int)(i * yinterval);
            now++;
        }


        for (int i = 1; i < xnumber; i++) {
            coordinate[now][0] = start[0] + (int)((xnumber - 1) * xinterval - i * xinterval);
            coordinate[now][1] = start[1] - (int)((ynumber - 1) * yinterval);
            now++;
        }

        for (int i = 1; i < ynumber -1; i++) {
            coordinate[now][0] = start[0];
            coordinate[now][1] = start[1] - (int)((ynumber - 1) * yinterval + i * yinterval);
            now++;
        }

        return coordinate;
    }

}