import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Main {

	static class Data {
		int r, c;
		char type;

		public Data(int r, int c, char type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}

	}

	static int H = 12;
	static int W = 6;
	static int cnt, result, max;
	static boolean ispop;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		// 뿌요 검사 (뿌요 4개 이상 상하좌우로 연결되어 있는지)
		// 뿌요 내리기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[H][W];
		for (int i = 0; i < H; i++) {
			String temp = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = temp.charAt(j);
			}
		}

		result = 0;

		while (true) {
			ispop = false;
			BFS();
			down();
			
			if(ispop == false) {
				break;
			}
			
			result ++;
		}
		
		System.out.println(result);
	}

	static void BFS() {
		Queue<Data> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[H][W];

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] != '.' && !visited[i][j]) {
					ArrayList<int[]> list = new ArrayList<>();
					q.add(new Data(i, j, map[i][j]));
					list.add(new int[] { i, j });
					visited[i][j] = true;

					while (!q.isEmpty()) {
						Data current = q.poll();

						for (int d = 0; d < 4; d++) {
							int nr = current.r + dr[d];
							int nc = current.c + dc[d];

							if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
								continue;
							}

							if (visited[nr][nc]) {
								continue;
							}

							if (map[nr][nc] == current.type) {
								q.offer(new Data(nr, nc, current.type));
								visited[nr][nc] = true;
								list.add(new int[] { nr, nc });
							}
						}
					}

					if (list.size() >= 4) {
						for (int k = 0; k < list.size(); k++) {
							int r = list.get(k)[0];
							int c = list.get(k)[1];

							map[r][c] = '.';
							ispop = true;
						}

					}
				}
			}
		}
	}

	static void down() {
		for (int i = 0; i < W; i++) {
			Stack<Character> st = new Stack<>();
			for (int j = 0; j < H; j++) {
				if (map[j][i] != '.') {
					st.add(map[j][i]);
					map[j][i] = '.';
				}
			}

			int r = H - 1;
			while (!st.isEmpty()) {
				map[r][i] = st.pop();
				r--;
			}

		}

	}

	static void debug() {
		System.out.println();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
