import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Data {
		int r, c, center, val;
		boolean[] dir; // 1: 상 - 2: 하 - 3: 좌 - 4: 우

		public Data(int r, int c, int center) {
			this.r = r;
			this.c = c;
			this.center = center;
			dir = new boolean[4];
		}

	}

	static int R, C, T;
	static int[][] map;
	static int airpos1, airpos2;
	static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dc = { 0, 0, -1, 1 };
	static List<Data> list;

	public static void main(String[] args) throws Exception {
		// 미세먼지의 확산은 동시에 진행된다.
		// 미세먼지는 공기청정기가 있는 곳엔 확산되지 않는다.
		// 확산되는 양은 A(r,c) / 5이다
		// 확산되고 남는 양은 A(r,c) - A(r,c) / 5 * (확산된 방향의 개수)

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		list = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 미세먼지 좌표 얻어오기
				if (map[i][j] > 0) {
					list.add(new Data(i, j, map[i][j]));
				}
			}
		}

		for(int i = 0; i < R; i++) {
			// 공기청정기 좌표 얻어오기
			if (map[i][0] == -1) {
				airpos1 = i;
				airpos2 = i + 1;
				break;
			}
		}
		
		// T초만큼 진행
		for (int time = 0; time < T; time++) {
			
			if(list.size() == 0) {
				for(int i = 0; i < R; i++) {
					for(int j = 0; j < C; j++) {
						if(map[i][j] > 0) {
							list.add(new Data(i, j, map[i][j]));
						}
					}
				}
			}
			
			// 미세먼지 동시 확산
			mise();
			// 공기 청정기 작동
			clean();
		}
		int result = getMise();
		System.out.println(result + 2);
	}

	
	static void mise() {
		// 미세먼지를 어떻게 동시에 확산 시킬까
		// 일단 4방위 탐색을 해야함. 4방위 탐색을 해서 퍼질 수 있는 공간의 갯수, 좌표를 구해야함
		// 그러나 이 정보를 어디에다가 저장을 해야할까? 차라리 그냥 list를 하나 더 만들어서
		// 자료형을 class로 저장하는 것이 좋을 것 같음.
		// class에 상 하 좌 우의 정보를 담아두기

		// 일단 list에 있는 미세먼지 하나씩 꺼내기
		for (int i = 0; i < list.size(); i++) {

			int r = list.get(i).r;
			int c = list.get(i).c;

			// 꺼낸 미세먼지 4방위 탐색
			int cnt = 0;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
					continue;
				}

				if (map[nr][nc] == -1) {
					continue;
				}

				// 해당 방향 check;
				list.get(i).dir[d] = true;
				cnt++;
			}

			// 확산 되는 값 저장하기
			list.get(i).val = list.get(i).center / 5;

			// 확산되고 남는 값을 저장하기
			list.get(i).center = list.get(i).center - (list.get(i).center / 5 * cnt);
			map[r][c] = list.get(i).center;
		}

		// 확산되는 값들 더해주기
		for (int i = 0; i < list.size(); i++) {

			int r = list.get(i).r;
			int c = list.get(i).c;

			// 확산 값
			int val = list.get(i).val;

			for (int d = 0; d < 4; d++) {
				if (list.get(i).dir[d]) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					map[nr][nc] += val;
				}
			}
		}
		
		list.clear();
	}

	static void clean() {
		int top = airpos1;
		
		for(int r = top - 1; r > 0; r--) {
			map[r][0] = map[r - 1][0];
		}
		
		for(int c = 0; c < C - 1; c++) {
			map[0][c] = map[0][c + 1];
		}
		
		for(int r = 0; r < top; r++) {
			map[r][C - 1] = map[r + 1][C - 1];
		}
		
		for(int c = C - 1; c > 1; c--) {
			map[top][c] = map[top][c - 1];
		}
		
		map[top][1] = 0;
		
		int bottom = airpos2;
		 
        for (int r = bottom + 1; r < R - 1; r++) {
            map[r][0] = map[r + 1][0];
        }
 
        for (int c = 0; c < C - 1; c++) {
            map[R - 1][c] = map[R - 1][c + 1];
        }
 
        for (int r = R - 1; r > bottom; r--) {
            map[r][C - 1] = map[r - 1][C - 1];
        }
 
        for (int c = C - 1; c > 1; c--) {
            map[bottom][c] = map[bottom][c - 1];
        }
 
        map[bottom][1] = 0;
	}

	static int getMise() {
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
					sum += map[i][j];
			}
		}
		return sum;
	}
	
	static void debug() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
