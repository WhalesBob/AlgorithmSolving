import java.io.*;
import java.util.*;

public class Main {
	static final int INFINITY = 999_999_999;
	static int[] cost;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Map<Integer, Map<Integer, Integer>> adjacencyMap = makeAdjacencyMap(br, m);
		boolean[] visited = new boolean[n + 1];
		
		System.out.println(getMiminumCostByDijkstra(visited, adjacencyMap, n));
	}
	static Map<Integer,Map<Integer,Integer>> makeAdjacencyMap(BufferedReader br, int size) throws IOException{
		Map<Integer,Map<Integer,Integer>> map = new HashMap<>();
		for(int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			if(!map.containsKey(start)) {
				map.put(start, new HashMap<>());
			}
			if(!map.containsKey(end)) {
				map.put(end, new HashMap<>());
			}
			
			if(map.get(start).get(end) == null) {
				map.get(start).put(end, value);
				map.get(end).put(start, value);
			}else {
				int existedValue = map.get(start).get(end);
				if(value < existedValue) {
					map.get(start).replace(end, value);
					map.get(end).replace(start, value);
				}
			}
			
		}
		return map;
	}
	static int getMiminumCostByDijkstra(boolean[] visited, Map<Integer, Map<Integer, Integer>> map, int n) {
		PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a,b) -> Integer.compare(a.get(1), b.get(1)));
		
		int start = 1;
		cost = new int[n+1];
		for(int i = 1; i < cost.length; i++) {
			cost[i] = INFINITY;
		}
		
		cost[start] = 0;
		queue.add(Arrays.asList(start, cost[start]));

		while(!visited[n]) {
			int currentFocus = queue.remove().get(0);
			if(visited[currentFocus]) {
				continue;
			}
			visited[currentFocus] = true;
			
			for (Integer key : map.get(currentFocus).keySet()) {
				int compareValue = cost[currentFocus] + map.get(currentFocus).get(key);
				if (!visited[key] && cost[key] > compareValue) {
					cost[key] = compareValue;
					queue.add(Arrays.asList(key, compareValue));
				}
			}
		}

		return cost[n];
	}
}