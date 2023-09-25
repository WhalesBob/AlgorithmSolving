import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static String[][] map, copyMap;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new String[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().split(" ");
		}

		copyMap = new String[N][M];
		for (int i = 0; i < N; i++) {
			copyMap[i] = Arrays.copyOf(map[i], map[i].length);
		}
		
		max = Integer.MIN_VALUE;

		comb(0);

		System.out.println(max);

	}

	private static void comb(int cnt) {
		if (cnt == 3) {

			String[][] bfsMap = new String[N][M];
			for (int i = 0; i < N; i++) {
				bfsMap[i] = Arrays.copyOf(copyMap[i], copyMap[i].length);
			}

			max = Math.max(max, bfs(bfsMap));
			return;
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				if (copyMap[i][j].equals("0")) {
//					copyMap[i][j] = "1";
//					comb(cnt + 1);
//					copyMap[i][j] = "0";
//				}
//			}
//		}
        for(int i = 0; i < N * M; i++) {
            int r = i / M;
            int c = i % M;
            
            if(copyMap[r][c].equals("0")) {
                copyMap[r][c] = "1";
                comb(cnt + 1);
                copyMap[r][c] = "0";
            }
        }

	}

	private static int bfs(String[][] copyMap) {

		Queue<Data> queue = new ArrayDeque<Data>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j].equals("2")) {
					queue.offer(new Data(i, j));
				}
			}
		}

		while (!queue.isEmpty()) {
			Data current = queue.poll();

			int[] dr = { -1, 1, 0, 0 };
			int[] dc = { 0, 0, -1, 1 };

			for (int i = 0; i < 4; i++) {
				int nr = current.i + dr[i];
				int nc = current.j + dc[i];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && copyMap[nr][nc].equals("0")) {
					copyMap[nr][nc] = "2";
					queue.offer(new Data(nr, nc));
				}
			}
		}


		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j].equals("0"))
					count++;
			}
		}

		return count;

	}

	static class Data {
		int i;
		int j;

		public Data(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

}