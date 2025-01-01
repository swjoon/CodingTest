package baekjoon.gold.ThirtySix;

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
import java.math.BigInteger;

public class ThirtySix {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, BigInteger> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        int N = Integer.parseInt(br.readLine());

        HashMap<Integer, List<String>> digitNum = new HashMap<>();

        for (int i = 0; i < 53; i++) {
            digitNum.put(i, new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            int L = input.length;
            for (int digit = L - 1; digit >= 0; digit--) {
                String c = input[digit];
                set.add(c);
                digitNum.get(L - 1 - digit).add(c);

                int cValue = Integer.parseInt(c, 36);
                int zValue = Integer.parseInt("Z", 36);
                int gain = zValue - cValue;

                BigInteger d = BigInteger.valueOf(36).pow(L - 1 - digit);
                BigInteger value = BigInteger.valueOf(gain).multiply(d);

                map.put(c, map.getOrDefault(c, BigInteger.ZERO).add(value));
            }
        }

        int K = Integer.parseInt(br.readLine());

        List<String> list = new ArrayList<>(set.stream().collect(Collectors.toList()));

        list.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));

        while (list.size() > K) {
            list.remove(list.size() - 1);
        }

        System.out.println(sum(list, digitNum));

    }

    private static String sum(List<String> list, HashMap<Integer, List<String>> digitNum) {
        StringBuilder sb = new StringBuilder();

        int maxDigit = Collections.max(digitNum.keySet());
        int carry = 0;

        for (int i = 0; i < maxDigit; i++) {
            int value = 0;
            for (String s : digitNum.get(i)) {
                value += list.contains(s) ? 35 : Integer.parseInt(s, 36);
            }

            value += carry;

            carry = value / 36;

            sb.append(Integer.toString(value % 36, 36));

        }

        sb.reverse();

        while (carry > 0) {
            sb.append(Integer.toString(carry % 36, 36));
            carry /= 36;
        }

        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.toString().toUpperCase();
    }
}