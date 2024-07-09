package 셔틀버스;

import java.util.*;

public class Bus {
    public static void main(String[] args) {
        solution(2, 10, 2, new String[] { "09:10", "09:09", "08:00" });
    }

    public static String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        List<String> list = new ArrayList<>();

        for (String time : timetable) {
            String[] split = time.split(":");
            list.add(split[0] + split[1]);
        }

        Collections.sort(list);

        Queue<String> queue = new LinkedList<>(list);

        String[] busTime = busTimeTable(n, t);

        // 먼저 셔틀을 탄 인원들 소거
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                String time = queue.peek();
                if (Integer.parseInt(time) > Integer.parseInt(busTime[i]))
                    break;
                queue.poll();
            }
        }

        List<String> wait = new ArrayList<>();
        
        // 잔여인원중 경쟁자 추출
        for (int i = 0; i < m; i++) {
            if (queue.isEmpty())
                break;
            String time = queue.peek();
            if (Integer.parseInt(time) > Integer.parseInt(busTime[n - 1]))
                break;
            wait.add(queue.poll());
        }
        
        // 여유있을시 마지막 버스시간 도착, 아닐시 제일 늦게 도착한 인원보다 1분 먼저 도착.
        if (wait.size() < m) {
            answer = returnTimeformat(busTime[n - 1], false);
        } else {
            answer = returnTimeformat(wait.get(m - 1), true);
            System.out.println(wait.get(m-1));
        }

        return answer;
    }

    // 버스시간표 추출

    public static String[] busTimeTable(int n, int t) {
        String[] busTime = new String[n];

        for (int i = 0; i < n; i++) {
            String hour = String.format("%02d", 9 + (t * i / 60));
            String minute = String.format("%02d", t * i % 60);
            busTime[i] = hour + minute;
        }

        return busTime;
    }

    // 정답형식으로 변환
    public static String returnTimeformat(String time, boolean check) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2, 4));
        System.out.println("hour " + hour);
        System.out.println("minute: " + minute);
        if (check == true) {
            minute -= 1;
            if (minute < 0) {
                hour -= 1;
                minute = 59;
            }
        }
        String sHour = String.format("%02d", hour);
        String sMinute = String.format("%02d", minute );

        return sHour + ":" + sMinute;
    }
}
