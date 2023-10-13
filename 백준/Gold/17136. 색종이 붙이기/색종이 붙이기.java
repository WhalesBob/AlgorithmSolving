import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int[] pCnt;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new int[10][10];
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		pCnt = new int[6];

		for (int i = 1; i < 6; i++) {
			pCnt[i] = 5;
		}

		result = Integer.MAX_VALUE;
		DFS(0, 0, 0);

		if (result == Integer.MAX_VALUE) {
			result = -1;
		}
		System.out.println(result);
	}

	static void DFS(int r, int c, int cnt) {
		// 기저 조건
		if (r > 9 && c >= 9) {
			result = Math.min(result, cnt);
			return;
		}

		if (cnt >= result)
			return;

		if (r > 9) {
			DFS(0, c + 1, cnt);
			return;
		}

//		System.out.println(r + " " + c);

		if (map[r][c] == 1) {
			for (int i = 5; i >= 1; i--) {
				if (pCnt[i] > 0 && isAvailable(r, c, i)) {
					pCnt[i] -= 1;
					gluePaper(r, c, i, 0);
					DFS(r + 1, c, cnt + 1);
					pCnt[i] += 1;
					gluePaper(r, c, i, 1);
				}

			}
		}

		else {
			DFS(r + 1, c, cnt);
		}

	}

	static boolean isAvailable(int r, int c, int len) {

		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				if (i < 0 || i >= 10 || j < 0 || j >= 10 || map[i][j] == 0) {
					return false;
				}
			}
		}

		return true;

	}

	static void gluePaper(int r, int c, int len, int type) {

		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				map[i][j] = type;
			}
		}

	}
}
