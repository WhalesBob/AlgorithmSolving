import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int min = 999_999_999;
	static boolean[] visited;
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		int[][] matrix = makeMatrix(br, n);
		visited = new boolean[n+1];
		dfs(matrix,1,1);
		System.out.println(min);
		
	}
	static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
		int[][] matrix = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j < size; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return matrix;
	}
	static void dfs(int[][] matrix, int index, int size) {
		if(size > n/2){
			int start = 0, link = 0;
			for(int pickIndex = 0; pickIndex < n; pickIndex++) {
				for(int playerIndex = 0; playerIndex < n; playerIndex++) {
					if (visited[pickIndex] && visited[playerIndex]) {
						start += matrix[pickIndex][playerIndex];
					}
					if (!visited[pickIndex] && !visited[playerIndex]) {
						link += matrix[pickIndex][playerIndex];
					}
				}
			}
			
			if(min > Math.abs(start - link)){
				min = Math.abs(start- link);
			}
			return;
		}
		
		for(int nodeIndex = index; nodeIndex < n; nodeIndex++) {
			visited[nodeIndex] = true;
			dfs(matrix,nodeIndex + 1, size + 1);
			visited[nodeIndex] = false;
		}
	}
}