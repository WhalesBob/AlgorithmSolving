import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int INF = 987654321;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		List<Integer>[][] list = new ArrayList[n + 1][n + 1];
		int[][] map = new int[n + 1][n + 1];

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				list[i][j] = new ArrayList<>();
				if (i == j) {
					continue;
				}
				map[i][j] = INF;
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			map[a][b] = Math.min(map[a][b], c);
		}

		for (int k = 1; k < n + 1; k++) {
			for (int i = 1; i < n + 1; i++) {
				for (int j = 1; j < n + 1; j++) {

					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
						
						list[i][j].clear();
						for(int temp : list[i][k]) {
							list[i][j].add(temp);
						}
						
						list[i][j].add(k);
						for(int temp : list[k][j]) {
							list[i][j].add(temp);
						}
					}
					
				}
			}
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {

				if (map[i][j] == INF) {
					sb.append("0").append(" ");
				} else {
					sb.append(map[i][j]).append(" ");
				}
			}

			sb.append("\n");
		}
		
		for(int i = 1; i < n+1; i++) {
			for(int j = 1; j < n+1; j++) {
				
				if(i == j || map[i][j] == INF) {
					sb.append(0).append("\n");
					continue;
				}
				
				sb.append(list[i][j].size()+2).append(" ");
				sb.append(i).append(" ");
				for(int k = 0; k < list[i][j].size(); k++) {
					sb.append(list[i][j].get(k)).append(" ");
				}
				sb.append(j).append("\n");
			}
		}

		System.out.println(sb.toString());
	}

}
