import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		
		int[] array = makeArray(br, n);
		
		Map<Integer, Set<Integer>> map = new HashMap<>();
		
		for(int mid = 0; mid < array.length; mid++) {
			for(int d = 0; mid - d >= 0 && mid + d < array.length; d++) {
				int start = mid - d, end = mid + d;
				if(array[start] != array[end]) {
					break;
				}
				
				if(!map.containsKey(start)) {
					map.put(start, new HashSet<>());
				}
				
				map.get(start).add(end);
			}
			
			for(int d = 1; mid - d >= 0 && mid + d - 1 < array.length; d++) {
				int start = mid - d, end = mid + d - 1;
				if(array[start] != array[end]) {
					break;
				}
				
				if(!map.containsKey(start)) {
					map.put(start, new HashSet<>());
				}
				
				map.get(start).add(end);
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			
			bw.write((map.get(start).contains(end) ? 1 : 0) + "\n"); 
		}
		bw.flush();
		
	}
	static int[] makeArray(BufferedReader br, int size) throws IOException{
		int[] array = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i < size; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		return array;
	}
}