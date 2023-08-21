import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] check = new int[n+1];
		
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if(!map.containsKey(start)) {
				map.put(start, new HashSet<>());
			}
			map.get(start).add(end);
			
			check[end]++;
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i = 1; i < check.length; i++) {
			if(check[i] == 0) {
				queue.add(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int element = queue.remove();
			bw.write(element + " ");
			if(map.containsKey(element)) {
				for(Integer child : map.get(element)) {
					check[child]--;
					if(check[child] == 0) {
						queue.add(child);
					}
				}
			}
		}
		
		bw.flush();
	}
}