package 여행경로;

import java.util.*;

public class travel {
    public static void main(String[] args) {
        solution(new String[][] { { "ICN", "SFO" }, { "ICN", "STL" }, { "SFO", "STL" }, { "STL", "ICN" },
                { "STL", "SFO" } });
    }

    public static String[] solution(String[][] tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();

        for (int i = 0; i < tickets.length; i++) {
            if (map.get(tickets[i][0]) == null) {
                map.put(tickets[i][0], new PriorityQueue<String>());
            }
            map.get(tickets[i][0]).add(tickets[i][1]);
        }
        
        List<String> list = DFS("ICN", new ArrayList<String>(),map);

        return list.toArray(new String[0]);
    }

    public static List<String> DFS(String start, List<String> list , HashMap<String, PriorityQueue<String>> map) {
        if (!map.containsKey(start) || map.get(start).isEmpty()) {
            list.add(start);
            System.out.println(start);
            return list;
        }
        list.add(start);
        List<String> rlist = DFS(map.get(start).poll(),new ArrayList<String>(), map);
        if(!map.get(start).isEmpty()){
            List<String> llist = DFS(map.get(start).poll(), new ArrayList<String>(), map);
            list.addAll(llist);
        }
        list.addAll(rlist);
        return list;
    }
}
