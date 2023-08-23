import java.io.*;
import java.util.*;

public class Solution {
	static int start;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			start = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			Map<Integer, Map<Integer, Integer>> map = makeAdjacencyMap(br, e);
			System.out.printf("#%d %d\n", test_case, prim(map, start, v));
		}
	}
	static long prim(Map<Integer, Map<Integer, Integer>> map, int start, int n) {
		boolean[] visit = new boolean[n + 1];
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		queue.add(new Edge(start, 0));
		long sum = 0;
		
		while(!queue.isEmpty()) {
			Edge e = queue.remove();
			int v = e.end;
			int cost = e.weight;
			
			if(visit[v]) {
				continue;
			}
			
			visit[v] = true;
			sum += cost;
			
			for(Integer key : map.get(v).keySet()) {
				if(!visit[key]) {
					queue.add(new Edge(key, map.get(v).get(key)));
				}
			}
		}
			
		return sum;
	}
	static Map<Integer, Map<Integer, Integer>> makeAdjacencyMap(BufferedReader br, int size) throws IOException{
		Map<Integer, Map<Integer,Integer>> map = new HashMap<>();
		
		for(int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			if(start == 0) {
				start = a;
			}
			
			if(!map.containsKey(a)) {
				map.put(a, new HashMap<>());
			}
			if(!map.containsKey(b)) {
				map.put(b, new HashMap<>());
			}
			
			if(!map.get(a).containsKey(b) || map.get(a).get(b) > weight) {
				map.get(a).put(b, weight);
			}
			
			if(!map.get(b).containsKey(a) || map.get(b).get(a) > weight) {
				map.get(b).put(a, weight);
			}
		}
		return map;
	}
	static class Edge implements Comparable<Edge>{
		int end;
		int weight;
		
		public Edge(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
}