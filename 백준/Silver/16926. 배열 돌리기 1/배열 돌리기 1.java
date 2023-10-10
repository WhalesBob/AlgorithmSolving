import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M,R;
	static String map[][];
	static int startR, startC;
	
	static int[] dr = {1, 0, -1, 0}; // 하 우 상 좌
	static int[] dc = {0, 1,  0,-1};
	static boolean[][] v;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new String[N][M];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().split(" ");
		}
		
		int half;
		
		if(N > M) half = M/2 -1;
		else half = N/2 - 1;
		
		v = new boolean[N][M];
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j <= half; j++) {
				startR = j;
				startC = j;
				rotate(startR, startC, 0, map[j][j]);
			} 
			v = new boolean[N][M];
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	
	static void rotate(int r, int c, int dir, String pre) {
		// 기저 조건
		if(v[r][c]) {
			return;
		}
		v[r][c] = true;
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		
		if(nr < startR || nr >= N - startR || nc < startC || nc >= M - startC) {
			dir = (dir + 1) % 4;
			nr = r + dr[dir];
			nc = c + dc[dir];
		}
		
		String temp = map[nr][nc];
		map[nr][nc] = pre;
		
		rotate(nr, nc, dir, temp);
	}
}
