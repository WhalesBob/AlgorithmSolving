import java.io.*;
import java.util.*;

public class Solution {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0), 
			Arrays.asList(0,1), Arrays.asList(-1,0), Arrays.asList(0,-1));
	static int max;
	static int maxNum, startNum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			int n = Integer.parseInt(br.readLine());
			int[][] matrix = makeMatrix(br, n);
			max = Integer.MIN_VALUE;
			
			for(int y = 0; y < matrix.length; y++) {
				for(int x = 0; x < matrix[0].length; x++) {
					startNum = matrix[y][x];
					dfs(x, y, matrix, 1);
				}
			}
			
			System.out.printf("#%d %d %d\n", test_case, maxNum, max);
		}

	}

	static int[][] makeMatrix(BufferedReader br, int size) throws IOException {
		int[][] matrix = new int[size][size];
		for (int y = 0; y < size; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < size; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		return matrix;
	}
	
	static void dfs(int x, int y, int[][] matrix, int count) {
		for(int i = 0; i < 4; i++) {
			int nextX = x + direction.get(i).get(0);
			int nextY = y + direction.get(i).get(1);
			
			if(canGo(nextX, nextY, matrix) && matrix[nextY][nextX] == matrix[y][x] + 1) {
				dfs(nextX, nextY, matrix, count + 1);
			}
		}
		
		if(max < count || (max == count && maxNum > startNum)) {
			max = count;
			maxNum = startNum;
		}
	}
	static boolean canGo(int x, int y, int[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
}