package programmers.기타.다양성;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class MultiFormity {
    public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            Set<String> set = new HashSet<>(Arrays.asList(String.valueOf(sc.nextInt()).split("")));
         
            System.out.println("#" + test_case + " " + set.size());
		}

        sc.close();
	}
}
