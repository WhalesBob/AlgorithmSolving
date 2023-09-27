import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R,C;
	static int tr, tc;
	static char map[][];
	static Queue<int[]> q1, q2;
	static int[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = { 0, 0,-1, 1};
	static boolean check;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		q1 = new ArrayDeque<>();
		q2 = new ArrayDeque<>();
		visited = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			String temp = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = temp.charAt(j);
				
				if(map[i][j] == 'S') {
					q2.offer(new int[] {i,j});
				}
				
				else if(map[i][j] == 'D') {
					tr = i;
					tc = j;
				}
				
				else if(map[i][j] == '*') {
					q1.offer(new int[] {i,j});
				}
			}
		}
		
		check = true;
		
		int time = 0;
		String result = "";
		while(check) {
			time ++;
			moveWa();
			moveGo();
			
//			for(int i = 0; i < R; i++) {
//				for(int j = 0; j < C; j++) {
//					System.out.print(map[i][j] +  " ");
//				}
//				
//				System.out.println("");
//			}
//			System.out.println("");
			
			if(q1.isEmpty() && q2.isEmpty()) {
				result += "KAKTUS";
				break;
			}
			
		}
		
		if(!check) {
			System.out.println(time);
		}
		
		else {
			System.out.println(result);
		}
			
	}
	
	static void moveWa() {
		Queue<int[]> temp = new ArrayDeque<>();
		
		while(!q1.isEmpty()) {
			
			int current[] = q1.poll();
			int r = current[0];
			int c = current[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
					continue;
				}
				
				if(map[nr][nc] == 'X') {
					continue;
				}
				
				if(map[nr][nc] == 'D') {
					continue;
				}
				
				if(visited[nr][nc] != 0) {
					continue;
				}
				
				temp.offer(new int[] {nr, nc});
				map[nr][nc] = '*';
				visited[nr][nc] = 1;
			}
			
		}
		
		while(!temp.isEmpty()) {
			q1.offer(temp.poll());
		}
	}
	
	static void moveGo() {
		Queue<int[]> temp = new ArrayDeque<>();
		
		while(!q2.isEmpty()) {
			
			int current[] = q2.poll();
			int r = current[0];
			int c = current[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
					continue;
				}
				
				if(map[nr][nc] == 'X') {
					continue;
				}
				
				if(map[nr][nc] == '*') {
					continue;
				}
				
				if(visited[nr][nc] != 0) {
					continue;
				}
				
				if(map[nr][nc] == 'D') {
					check = false;
					break;
				}
				
				temp.offer(new int[] {nr, nc});
				map[nr][nc] = 'S';
				visited[nr][nc] = 1;
			}
			
		}
		
		while(!temp.isEmpty()) {
			q2.offer(temp.poll());
		}
	}
	
	static void check() {
		
	}
}
