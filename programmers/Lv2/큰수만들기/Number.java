package 큰수만들기;

public class Number {
    public static void main(String[] args) {
        solution("1924", 2);
    }

    public static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        String[] list = number.split("");
        int now = 0;

        // 반복문 조건 설정
        while (k > 0 || now < list.length - k) {
            int check = 0;

            // 잔여 자릿수와 제거할 숫자가 동일할 때 제외 후 답 출력
            if (now + k == list.length) return sb.toString(); 

            for (int i = now + 1; i <= now + k; i++) {
                
                // 9 보다 큰 한자리 수는 없으므로 다음으로 넘김
                if (list[now].equals("9")) break; 

                // 현재값보다 큰 수가 나오면 그 사이의 수 제거 
                if (Integer.parseInt(list[now]) < Integer.parseInt(list[i])) { 
                    k -= i - now;
                    now = i;
                    check++;
                    break;
                }
            }

            // 변동없을시 리스트에 추가 후 다음으로 넘김
            if (check == 0) {
                sb.append(list[now]);
                now++;
            }
        }

        return sb.toString();
    }
}
