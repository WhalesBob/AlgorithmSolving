import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[9];
		for(int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		combination(arr, new boolean[9], 0, 9, 7);
	}
	static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
		if(r == 0) {
			int sum = 0;
			for(int i = 0; i < 9; i++) {
				if(visited[i]) {
					sum += arr[i];
				}
			}
			if(sum == 100) {
				for(int i = 0; i < 9; i++) {
					if(visited[i]) {
						System.out.println(arr[i]);
					}
				}
				System.exit(0);
			}
			return;
		}
		
		for(int i = start; i < n; i++) {
			visited[i] = true;
			combination(arr, visited, i + 1, n, r - 1);
			visited[i] = false;
		}
	}
}