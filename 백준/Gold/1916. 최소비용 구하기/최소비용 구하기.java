import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		Map<Integer, Map<Integer, Integer>> adjacencyMap =  getAdjacencyMap(br, m);
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int start = Integer.parseInt(st.nextToken());
		int dest = Integer.parseInt(st.nextToken());
		
		System.out.println(getMinimumCost(adjacencyMap, start, dest, n));
	}
	static Map<Integer, Map<Integer, Integer>> getAdjacencyMap(BufferedReader br, int busCount) throws IOException{
		Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
		for(int i = 0; i < busCount; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			if(!map.containsKey(start)) {
				map.put(start, new HashMap<>());
			}
			
			if(map.get(start).containsKey(end)) {
				int have = map.get(start).get(end);
				if(have > value) {
					map.get(start).replace(end, value);
				}
			}else {
				map.get(start).put(end, value);
			}
		}
		return map;
	}
	
	static int getMinimumCost(Map<Integer, Map<Integer, Integer>> map, int start, int dest, int cityCount) {
		boolean[] visited = new boolean[cityCount + 1];
		int[] cost = new int[cityCount + 1];
		for(int i = 1; i < cost.length; i++) {
			cost[i] = INF;
		}
		
		cost[start] = 0;
		PriorityQueue<List<Integer>> queue = new PriorityQueue<List<Integer>>((o1,o2) -> Integer.compare(o1.get(1), o2.get(1)));
		queue.add(Arrays.asList(start, cost[start]));
		
		while(!queue.isEmpty() && !visited[dest]) {
			List<Integer> element = queue.remove();
			int point = element.get(0);
			visited[point] = true;
			if(!map.containsKey(point)) {
				continue;
			}
			
			for(Integer toGo : map.get(point).keySet()) {
				int value = cost[point] + map.get(point).get(toGo);
				if(!visited[toGo] && cost[toGo] > value) {
					cost[toGo] = value;
					queue.add(Arrays.asList(toGo, cost[toGo]));
				}
			}
		}
		
		return cost[dest];
	}
}