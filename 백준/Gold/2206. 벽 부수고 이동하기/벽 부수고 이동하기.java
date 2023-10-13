import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map, visited[];
	static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		String[] temp = null;
		for (int i = 0; i < N; i++) {
			temp = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		visited = new int[N][M][2];
		BFS();

		if (visited[N - 1][M - 1][0] == 0 && visited[N - 1][M - 1][1] == 0) {
			System.out.println(-1);
		} else if (visited[N - 1][M - 1][0] == 0 || visited[N - 1][M - 1][1] == 0) {
			System.out.println(Math.max(visited[N - 1][M - 1][0], visited[N - 1][M - 1][1]));
		} else {
			System.out.println(Math.min(visited[N - 1][M - 1][0], visited[N - 1][M - 1][1]));
		}

	}

	static void BFS() {
		Queue<int[]> q = new ArrayDeque<>();

		q.offer(new int[] { 0, 0, 0 });
		visited[0][0][0] = 1;

		while (!q.isEmpty()) {
			int[] current = q.poll();

			int nr, nc;
			for (int d = 0; d < 4; d++) {
				nr = current[0] + dr[d];
				nc = current[1] + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}

				if (map[nr][nc] == 1 && visited[nr][nc][1] == 0 && current[2] == 0) {
					q.offer(new int[] { nr, nc, 1 });
					visited[nr][nc][1] = visited[current[0]][current[1]][0] + 1;
				}
				if (map[nr][nc] == 0 && visited[nr][nc][current[2]] == 0) {
					q.offer(new int[] { nr, nc, current[2] });
					visited[nr][nc][current[2]] = visited[current[0]][current[1]][current[2]] + 1;
				}

			}

		}
	}
}
