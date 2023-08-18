import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0),
			Arrays.asList(0,1), Arrays.asList(-1,0), Arrays.asList(0,-1));
	static int B,W,count;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		
		char[][] matrix = makeMatrix(br, height);
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				if(matrix[y][x] != 'O') {
					char store = matrix[y][x];
					count = 0;
					dfs(matrix[y][x], matrix, x, y);
					if(store == 'B') {
						B += Math.pow(count, 2);
					}
					if(store == 'W') {
						W += Math.pow(count, 2);
					}
				}
			}
		}
		System.out.println(W + " " + B);
	}
	static char[][] makeMatrix(BufferedReader br, int height) throws IOException{
		char[][] matrix = new char[height][];
		for(int i = 0; i < matrix.length; i++) {
			matrix[i] = br.readLine().trim().toCharArray();
		}
		return matrix;
	}
	static void dfs(char team, char[][] matrix, int x, int y) {
		matrix[y][x] = 'O';
		count++;
		for(int i = 0; i < 4; i++) {
			int newX = x + direction.get(i).get(0);
			int newY = y + direction.get(i).get(1);
			if(canGo(newX, newY, matrix) && matrix[newY][newX] == team) {
				dfs(team, matrix, newX, newY);
			}
		}
	}
	static boolean canGo(int x, int y, char[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
}