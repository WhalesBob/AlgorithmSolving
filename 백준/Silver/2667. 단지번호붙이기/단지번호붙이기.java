
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;

public class Main {
	public static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static int N, hc, cnt;
	static int[][] lst;
	static boolean[][] v;
	static Queue<Pair> q;
	static ArrayList<Integer> ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		lst = new int[N][N];
		v = new boolean[N][N];
		q = new ArrayDeque<>();
		ans = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				lst[i][j] = s.charAt(j) - '0';
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (lst[i][j] == 1) {
					cnt = 0;
					bfs(i, j);
					hc += 1;
				}
			}
		}
		Collections.sort(ans);
		System.out.println(hc);
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i));
		}
	}

	private static void bfs(int si, int sj) {
		q.offer(new Pair(si, sj));
		v[si][sj] = true;
		cnt++;
		while (!q.isEmpty()) {
			Pair tmp = q.poll();
			for (int d = 0; d < 4; d++) {
				int ni = tmp.x + di[d];
				int nj = tmp.y + dj[d];
				if (-1 < ni && ni < N && -1 < nj && nj < N && lst[ni][nj] == 1) {
					if (!v[ni][nj]) {
						cnt++;
						lst[ni][nj] = 0;
						v[ni][nj] = true;
						q.offer(new Pair(ni, nj));
					}
				}
			}
		}
		ans.add(cnt);
	}

}
