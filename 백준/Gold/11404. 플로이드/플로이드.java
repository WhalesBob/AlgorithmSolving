import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int INF = 987654321;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n+1][n+1];
		for(int i = 1; i < n+1; i++) {
			for(int j = 1; j < n+1; j++) {
				if(i == j) {
					map[i][j] = 0;
					continue;
				}
				map[i][j] = INF;
			}
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[a][b] = Math.min(map[a][b], c);
		}
		
		
		for(int k = 1; k < n+1; k++) {
			for(int i = 1; i < n+1; i++) {
				for(int j = 1; j < n+1; j++){
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}	
		}
		
		for(int i = 1; i < n+1; i++) {
			for(int j = 1; j < n+1; j++) {
				if(map[i][j] == INF) {
					System.out.print(0 + " ");
					continue;
				}
				System.out.print(map[i][j] + " ");
			}
			System.out.println("");
		}
		
	}

}
