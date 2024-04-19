package 과제진행하기;

import java.util.*;

public class assignment {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[][] { { "science", "12:40", "50" },
                { "music", "12:20", "40" }, { "history", "14:00", "30" }, { "computer", "12:30", "100" } })));
    }

    public static String[] solution(String[][] plans) {
        String[] answer = {};
        HashMap<String, Integer> map = new HashMap<>();
        Stack<String> stack = new Stack<>();
        ArrayList<String[]> list = new ArrayList<>();
        ArrayList<String> sequence = new ArrayList<>();

        for (int i = 0; i < plans.length; i++) {
            list.add(plans[i]);
            map.put(plans[i][0], Integer.parseInt(plans[i][2]));
        }

        Collections.sort(list, (o1, o2) -> Integer.parseInt(o1[1].split(":")[0] + o1[1].split(":")[1])
                - (Integer.parseInt(o2[1].split(":")[0] + o2[1].split(":")[1])));

        for (int num = 0; num < list.size(); num++) {
            int time = 0;
            if (num >= list.size() -1) {
                sequence.add(list.get(num)[0]);
                while (!stack.isEmpty()) {
                    sequence.add(stack.pop());
                }
            } else {
                if (hour(list.get(num)[1]) == hour(list.get(num + 1)[1])) {
                    time = minute(list.get(num + 1)[1]) - minute(list.get(num)[1]);
                } else {
                    time = 60 * ((hour(list.get(num + 1)[1]) - hour(list.get(num)[1])) - 1) + 60
                            - minute(list.get(num)[1])
                            + minute(list.get(num + 1)[1]);
                }
                if (time < map.get(list.get(num)[0])) {
                    map.put(list.get(num)[0], map.get(list.get(num)[0]) - time);
                    stack.push(list.get(num)[0]);
                } else {
                    time -= map.get(list.get(num)[0]);
                    map.remove(list.get(num)[0]);
                    sequence.add(list.get(num)[0]);
                    while (time > 0) {
                        if(!stack.isEmpty()){
                            String subject = stack.pop();
                            if (time >= map.get(subject)) {
                                time -= map.get(subject);
                                sequence.add(subject);
                                map.remove(subject);
                            } else {
                                map.put(subject, map.get(subject) - time);
                                time = 0;
                                stack.push(subject);
                            }
                        }else break;
                    }
                }
            }
        }
        answer = sequence.toArray(new String[sequence.size()]);
        return answer;
    }

    public static int hour(String time) {
        return Integer.parseInt(time.split(":")[0]);
    }

    public static int minute(String time) {
        return Integer.parseInt(time.split(":")[1]);
    }
}
