import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int count;
	static int s;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		int[] inputArray = makeArray(br, st, n);
		count = 0;
		
		for(int r = 1; r <= n; r++) {
			combination(inputArray, new boolean[n], 0, n, r);
		}
		System.out.println(count);
	}
	static int[] makeArray(BufferedReader br, StringTokenizer st, int n) throws IOException{
		int[] array = new int[n];
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i < n; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		return array;
	}
	
	static void combination(int[] arr, boolean[] visited,int start, int n, int r) {
		if(r == 0) {
			if(getSum(arr, visited) == s) {
				count ++;
			}
			return;
		}
		
		for(int i = start; i < n; i++) {
			visited[i] = true;
			combination(arr, visited, i+1, n, r-1);
			visited[i] = false;
		}
	}
	static int getSum(int[] arr, boolean[] visited) {
		int sum = 0;
		for(int i = 0; i < arr.length; i++) {
			if(visited[i]) {
				sum += arr[i];
			}
		}
		return sum;
	}
}