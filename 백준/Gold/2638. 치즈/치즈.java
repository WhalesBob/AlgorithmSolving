import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, cnt;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0}; //상 하 좌 우
	static int[] dc = { 0, 0,-1, 1};
	static List<int[]> list;
	static List<int[]> list2;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		list = new ArrayList<>();
		list2 = new ArrayList<>();
		
		cnt = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) {
					cnt ++;
				}
			}
		}
		
		int time = 0;
		while(true) {
			
			BFS(0,0);
			check();
			time++;
			
			if(cnt == 0) {
				break;
			}
		}
		
		System.out.println(time);
	}
	
	//0부터 탐색을 해서 외곽 치즈 찾기
	static void BFS(int startR, int startC) {
		
		Queue<int[]> q = new ArrayDeque<>();
		visited = new boolean[N][M];
		q.offer(new int[] {startR, startC});
		visited[startR][startC] = true;
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			int r = current[0];
			int c = current[1];
			
			int nr,nc;
			for(int d = 0; d < 4; d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}
				
				if(visited[nr][nc]) {
					continue;
				}
				
				if(map[nr][nc] == 1) {
					list.add(new int[] {nr, nc});
					visited[nr][nc] = true;
					continue;
				}
				
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
				
			}
		}
		
		
	}
	
	// 2변 이상의 치즈인가요?
	static void check() {
		
		for(int i = 0; i < list.size(); i++) {
			int r = list.get(i)[0];
			int c = list.get(i)[1];
			
			int val = 0;
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(map[nr][nc] == 0) {
					if(visited[nr][nc]) {
						val++;
					}
				}
			}
			
			if(val >= 2) {
				list2.add(new int[] {r,c});
			}
		}
		for(int i = 0; i < list2.size(); i++) {
			int r = list2.get(i)[0];
			int c = list2.get(i)[1];
			
			map[r][c] = 0;
		}
		
		cnt -= list2.size();
		list2.clear();
		list.clear();
		
	}
	
	static void debug() {
		System.out.println();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println();
	}
}
