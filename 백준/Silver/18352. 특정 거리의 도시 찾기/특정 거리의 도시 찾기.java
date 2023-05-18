import java.io.*;
import java.util.*;

public class Main {
   public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int dist = Integer.parseInt(input[2]);
        int start = Integer.parseInt(input[3]);

        if(dist == 0){
            System.out.println(start);
            return;
        }

        Map<Integer, List<Integer>> adjacencyMap = makeMap(m, br);
        boolean[] visited = new boolean[n+1];

        visited[start] = true;
        Map<Integer, Integer> d = new HashMap<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int count = 0;

        for(int i = 0; i < dist; i++){
            count++;
            int queueSize = queue.size();
            for(int j = 0; j < queueSize; j++){
                int city = queue.remove();
                if(!adjacencyMap.containsKey(city)){
                    continue;
                }
                for(int next : adjacencyMap.get(city)){
                    if(!visited[next]){
                        queue.add(next);
                        visited[next] = true;
                        d.put(next, count);
                    }
                }
            }
        }

        List<Integer> answer = new ArrayList<>();
        for (int key : d.keySet()) {
            if(d.get(key) == dist){
                answer.add(key);
            }
        }

        if(answer.size() == 0){
            answer.add(-1);
        }
        Collections.sort(answer);
        for(int i = 0; i < answer.size(); i++){
            bw.write(answer.get(i) + "\n");
        }
        bw.flush();
    }

    static Map<Integer, List<Integer>> makeMap(int count, BufferedReader br) throws IOException {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < count; i++){
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            if(map.containsKey(a)){
                List<Integer> list = map.get(a);
                list.add(b);
                map.replace(a, list);
            }else{
                map.put(a, new ArrayList<>(Arrays.asList(b)));
            }
        }
        return map;
    }
}