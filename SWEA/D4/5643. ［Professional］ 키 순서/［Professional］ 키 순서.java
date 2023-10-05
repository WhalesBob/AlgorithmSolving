import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, M;
	static int[][] adj;
	static int gcnt, lcnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			M = Integer.parseInt(br.readLine().trim());

			// 인접행렬 : 인접행렬의 값이 1이면 나보다 키가 큰 사람 (행 기준)
			adj = new int[N + 1][N + 1];

			for (int m = 0; m < M; m++) {

				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				adj[i][j] = 1;

			}
			
			// 행렬을 다 받았다면 어떻게?
			// 내가 구해야할 것은 나보다 큰 사람 + 나보다 작은 사람 == N-1인 경우를 찾아야함
			// 나보다 큰 사람은 어떻게 구함? : 행을 기준으로 열의 인덱스 부분을 1로 탐색을 해야지
			// 탐색은 어떻게 ? : DFS로

			int answer = 0;

			for (int i = 1; i <= N; i++) {
				
				gcnt = 0;
				lcnt = 0;
				
				gtDFS(i, new boolean[N+1]);
				ltDFS(i, new boolean[N+1]);
				
				if (gcnt + lcnt == N - 1) {
					answer++;
				}
				
			}

			System.out.println("#" + tc + " " + answer);

		}

	}

	// 필요한 매개변수 : 방문할 행의 인덱스, 방문배열
	static void gtDFS(int cur, boolean[] visited) {
		visited[cur] = true;
		for (int i = 1; i <= N; i++) {
			if (adj[cur][i] == 1 && !visited[i]) {
				gcnt++;
				gtDFS(i, visited);
			}
		}

	}

	static void ltDFS(int cur, boolean[] visited) {
		visited[cur] = true;
		for (int i = 1; i <= N; i++) {
			if (adj[i][cur] == 1 && !visited[i]) {
				lcnt++;
				ltDFS(i, visited);
			}
		}

	}
}
