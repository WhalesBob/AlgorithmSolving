import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 한 섬과 다른 섬을 잇는 다리 하나만을 만들기
	// 다리를 가장 짧게
	// 제일 먼저 섬 구분하기
	// 모든 육지에서 BFS후 다른 육지로 닿는 가장 짧은 경우를 구하기

	static int N;
	static int[][] map;
	static Queue<int[]> q;
	static ArrayList<int[]> list;
	static ArrayList<int[]> coastLine;
	static boolean[][] visited;
	static int[][] visited2;
	static int vcount;
	static int count, result;
	static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					list.add(new int[] { i, j });
				}

			}
		}

		visited = new boolean[N][N];

		// 구역 나누기
		count = 1;
		for (int i = 0; i < list.size(); i++) {
			division(list.get(i)[0], list.get(i)[1]);
		}
		
		visited = new boolean[N][N];
		coastLine = new ArrayList<>();
		
		// 해안선 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					getCoastLine(i, j);
				}
			}
		}

		//최소 거리 구하기
		visited2 = new int[N][N];
		vcount = -1;
		result = Integer.MAX_VALUE;
		for(int i = 0; i < coastLine.size(); i++) {
			getDistance(coastLine.get(i)[0], coastLine.get(i)[1], map[coastLine.get(i)[0]][coastLine.get(i)[1]]);
		}
		
		System.out.println(result);
	}

	static void division(int r, int c) {
		q = new ArrayDeque<>();

		if (visited[r][c]) {
			return;
		}

		q.offer(new int[] { r, c });
		visited[r][c] = true;
		map[r][c] = count;

		while (!q.isEmpty()) {
			int[] current = q.poll();

			int nr, nc;
			for (int d = 0; d < 4; d++) {
				nr = current[0] + dr[d];
				nc = current[1] + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
					continue;
				}

				if (visited[nr][nc]) {
					continue;
				}

				if (map[nr][nc] == 0) {
					continue;
				}

				map[nr][nc] = count;
				q.offer(new int[] { nr, nc });
				visited[nr][nc] = true;
			}

		}
		count++;
	}

	static void getCoastLine(int r, int c) {
		q.offer(new int[] {r,c});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			
			int nr,nc;
			for(int d = 0; d < 4; d++) {
				nr = current[0] + dr[d];
				nc = current[1] + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
					continue;
				}
				if(visited[nr][nc]) {
					continue;
				}
				if(map[nr][nc] != 0) {
					coastLine.add(new int[] {nr, nc});
					continue;
				}
				
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
	}
	
	static void getDistance(int r, int c, int type) {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {r, c, type, 0});
		visited2[r][c] = vcount;
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			int nr,nc;
			for(int d = 0; d < 4; d++) {
				nr = current[0] + dr[d];
				nc = current[1] + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
					continue;
				}
				if(visited2[nr][nc] == vcount) {
					continue;
				}
				
				if(map[nr][nc] == current[2]) {
					continue;
				}

				if(map[nr][nc] != current[2] && map[nr][nc] != 0) {
					if(result > current[3]) {
						result = current[3];
					}
					continue;
				}
				
				if(current[3] > result) {
					continue;
				}
				
				q.offer(new int[] {nr, nc, current[2], current[3] + 1});
				visited2[nr][nc] = vcount;
				
			}
		}
		
		vcount --;
	}
}
