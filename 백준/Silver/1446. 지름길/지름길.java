import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		Map<Integer, Map<Integer, Integer>> map = makeMap(br, n, d);
		
		System.out.println(getAnswer(map,d));
	}
	static int getAnswer(Map<Integer, Map<Integer, Integer>> map, int dest) {
		int[] distance = new int[dest + 1];
		for(int i = 1; i <= dest; i++) {
			distance[i] = i;
		}
		
		for(int i = 0; i <= dest; i++) {
			if(i >= 1) {
				distance[i] = Math.min(distance[i], distance[i-1] + 1);
			}
			
			if(map.containsKey(i)) {
				for(Integer toGo : map.get(i).keySet()) {
					distance[toGo] = Math.min(distance[toGo], distance[i] + map.get(i).get(toGo));
				}
			}
		}
		
		return distance[dest];
	}
	static Map<Integer, Map<Integer, Integer>> makeMap(BufferedReader br, int size, int max) throws IOException{
		Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer,Integer>>();
		
		for(int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			if(end > max || value >= (end - start)) {
				continue;
			}
			
			if(!map.containsKey(start)) {
				map.put(start, new HashMap<>());
			}
			
			if(!map.get(start).containsKey(end) || map.get(start).get(end) > value) {
				map.get(start).put(end, value);
			}
		}

		return map;
	}
}