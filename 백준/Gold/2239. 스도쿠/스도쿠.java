import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static int[][] map;
	static List<int[]> list;
	static int length;
	static boolean first;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 0인 숫자에 하나씩 다 1부터 9까지 다 넣어봄
		// 검사(해당 행과 열, 3x3)해서 안되면 버리고 이를 반복 (재귀)
		
		map = new int[9][9];
		list = new ArrayList<>();
		
		for(int i = 0; i < 9; i++) {
			String temp = br.readLine();
			for(int j = 0; j < 9; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}
		
		DFS(0);
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}
		
		
	}
	
	static void DFS(int cnt) {
		if(cnt == 81) {
			first = true;
			return;
		}
		
		int r = cnt / 9;
		int c = cnt % 9;
		
		if(map[r][c] != 0) {
			DFS(cnt +1);
		}
		
		else {
			for(int i = 1; i < 10; i++) {
				if(check(r,c,i)) {
					map[r][c] = i;
					DFS(cnt+1);
					if(first) {
						return;
					}
					map[r][c] = 0;
				}
			}
		}
		
	}
	
//	static void DFS(int cnt) {
//		
//		if(cnt == length) {
//			first = true;
//			return;
//		}
//		
//		for(int j = 1; j < 10; j++) {
//			
//			int r = list.get(cnt)[0];
//			int c = list.get(cnt)[1];
//			System.out.println(r + " " + c);
//			if(check(r,c,j)) {
//				map[r][c] = j;
//				
//				DFS(cnt + 1);
//				if(first) {
//					return;
//				}
//				map[r][c] = 0;
//			}
//		
//		}
//		
//	}
	
	static boolean check(int r, int c, int val) {
		
		for(int i = 0; i < 9; i++) {
			if(map[r][i] == val) {
				return false;
			}
			
			if(map[i][c] == val) {
				return false;
			}
		}
		
		int sr = (r/3) * 3;
		int sc = c - (c%3);
		
		// c - (c%3)
		// 0,0 / 3,0 / 6,0
		// 3,0 / 3,3 / 3,6
		// 6,0 / 6,3 / 6,6

		for(int x = sr; x < sr+3; x++) {
			for (int y = sc; y < sc+3; y++) {
				if(map[x][y] == val) {
					return false;
				}
			}
		}
		
		return true;
	}
}
