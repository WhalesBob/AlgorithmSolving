import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map, visited;
	static int N, result;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = { 0, 0,-1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int cnt = 1;
		while(true) {
			int temp = Integer.parseInt(br.readLine());
			if(temp == 0) {
				break;
			}
			
			N = temp;
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			visited = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					visited[i][j] = Integer.MAX_VALUE;
				}
			}
			BFS();
			
			System.out.println("Problem " + cnt + ": " + visited[N-1][N-1]);
			cnt++;
		}
	}
	
	static void BFS() {
		Queue<int[]> q = new PriorityQueue<>(new Comparator<int[]>(){

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
			
		});
		
		q.offer(new int[] {0, 0, map[0][0]});
		visited[0][0] = map[0][0];
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			int r = current[0];
			int c = current[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
					continue;
				}
				
				if(visited[nr][nc] > map[nr][nc] + visited[r][c]) {
					visited[nr][nc] = map[nr][nc] + visited[r][c];
					q.offer(new int[] {nr, nc, visited[nr][nc]});
				}
				
			}
		}
	}

}
