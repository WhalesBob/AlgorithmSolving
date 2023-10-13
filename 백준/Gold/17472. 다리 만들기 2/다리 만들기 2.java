import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

	static class Node implements Comparable<Node> {

		int from;
		int to;
		int weight;

		public Node(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}

		@Override
		public String toString() {
			return "Node [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}

	}

	static int N, M, isNum, result;
	static int[][] map;

	static List<int[]> island;
	static List<int[]> coastLine;
	static Queue<Node> pq = new PriorityQueue<>();

	static boolean[][] visited;
	static int[] parents;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// 다리의 방향이 중간에 바뀌면 안된다.
		// 다리의 길이는 2 이상이어야한다.

		// 일단 섬을 분리한다. (현재 1로 되어 있음)
		// 섬을 분리 했다면 해안선을 구한다. (치즈 풀듯이 0부터 탐색)

		// 다리를 만들고 크루스칼

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		island = new ArrayList<>();

		// 맵 받아오기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1) {
					island.add(new int[] { i, j });
				}
			}
		}

		// 섬 분리하기
		isNum = 1;
		visited = new boolean[N][M];
		for (int i = 0; i < island.size(); i++) {
			int r = island.get(i)[0];
			int c = island.get(i)[1];
			divide(r, c);
		}

		// 해안선 구하기
		coastLine = new ArrayList<>();
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					getCoastLine(i, j);
				}
			}
		}

		// 그래프 구하기
		for (int i = 0; i < coastLine.size(); i++) {
			int r = coastLine.get(i)[0];
			int c = coastLine.get(i)[1];

			makeBridge(r, c, map[r][c]);
		}

		// 최소 신장 트리
		parents = new int[isNum];
		for (int i = 1; i < isNum; i++) {
			parents[i] = i;
		}


		shortestPath();

		if (result == 0) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}

	}

	static void divide(int r, int c) {

		if (visited[r][c]) {
			return;
		}

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { r, c });
		visited[r][c] = true;
		map[r][c] = isNum;

		while (!q.isEmpty()) {
			int[] current = q.poll();
			int cr = current[0];
			int cc = current[1];

			int nr, nc;
			for (int d = 0; d < 4; d++) {
				nr = cr + dr[d];
				nc = cc + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}

				if (visited[nr][nc]) {
					continue;
				}

				if (map[nr][nc] == 0) {
					continue;
				}

				q.offer(new int[] { nr, nc });
				visited[nr][nc] = true;
				map[nr][nc] = isNum;

			}
		}

		isNum++;
	}

	static void getCoastLine(int r, int c) {
		if (visited[r][c]) {
			return;
		}

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { r, c });
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int[] current = q.poll();
			int cr = current[0];
			int cc = current[1];

			int nr, nc;
			for (int d = 0; d < 4; d++) {
				nr = cr + dr[d];
				nc = cc + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}

				if (visited[nr][nc]) {
					continue;
				}

				if (map[nr][nc] != 0) {
					coastLine.add(new int[] { nr, nc });
					visited[nr][nc] = true;
					continue;
				}

				q.offer(new int[] { nr, nc });
				visited[nr][nc] = true;

			}
		}

	}

	static void makeBridge(int r, int c, int type) {

		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];

		for (int d = 0; d < 4; d++) {
			q.offer(new int[] { r, c, 0 });
			visited[r][c] = true;

			while (!q.isEmpty()) {
				int[] current = q.poll();
				int cr = current[0];
				int cc = current[1];
				int len = current[2];

				int nr = cr + dr[d];
				int nc = cc + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}

				if (visited[nr][nc]) {
					continue;
				}

				if (map[nr][nc] == type) {
					break;
				}

				if (map[nr][nc] != type && map[nr][nc] != 0) {
					int from = type;
					int to = map[nr][nc];
					int brLen = len;

					if (brLen > 1) {
						pq.add(new Node(from, to, brLen));
						break;
					}
				}

				else {
					q.add(new int[] { nr, nc, len + 1 });
					visited[nr][nc] = true;
				}
			}
			q.clear();
		}

	}

	static void shortestPath() {
		int sum = 0;
		int size = pq.size();

		for (int i = 0; i < size; i++) {
			Node node = pq.poll();
			int a = node.from;
			int b = node.to;

			if (union(a, b)) {
				sum += node.weight;
			}

		}

		
		for(int i = 1; i < isNum; i++) {
			find(i);
		}
		int root = parents[1];
		for (int i = 2; i < isNum; i++) {
			if (find(i) != root) {
				result = 0;
				return;
			}
		}

		result = sum;

	}

	static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b)
			return false;
		else {
			parents[b] = a;
			return true;
		}
	}

}