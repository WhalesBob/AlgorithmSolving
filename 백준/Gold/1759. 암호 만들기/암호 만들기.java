import java.io.*;
import java.util.*;

public class Main {
	static BufferedWriter bw;
	static Set<Character> motherSet;
	static StringBuilder builder;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		builder = new StringBuilder();
		
		int l = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		char[] letters = makeArray(br);
		motherSet = new HashSet<>(Arrays.asList('a','e','i','o','u'));
		
		combination(letters, new boolean[letters.length], 0, letters.length, l);
		bw.flush();
	}
	static char[] makeArray(BufferedReader br) throws IOException{
		char[] array = br.readLine().trim().replace(" ", "").toCharArray();
		Arrays.sort(array);
		return array;
	}
	static void combination(char[] arr, boolean[] visited, int start, int n, int r) throws IOException{
		if(r == 0) {
			checkAndWrite(arr, visited);
			return;
		}
		
		for(int i = start; i < n; i++) {
			visited[i] = true;
			combination(arr, visited, i+1, n, r-1);
			visited[i] = false;
		}
	}
	static void checkAndWrite(char[] arr, boolean[] visited) throws IOException{
		boolean have = false;
		int sonCount = 0;
		for(int i = 0; i < arr.length; i++) {
			if(visited[i]) {
				if(!motherSet.contains(arr[i])) {
					sonCount++;
				}else {
					have = true;
				}
				builder.append(arr[i]);
			}
		}
		
		if(sonCount >= 2 && have) {
			bw.write(builder.toString() + "\n");
		}
		builder.delete(0, builder.length());
	}
}