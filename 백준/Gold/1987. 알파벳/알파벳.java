import java.io.*;
import java.util.*;

public class Main {
	static int max;
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0), Arrays.asList(0,1),
			Arrays.asList(-1,0), Arrays.asList(0,-1)); 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		
		max = 0;
		char[][] matrix = makeMatrix(br, height);
		
		dfs(matrix, 0, 0, new HashSet<>(Arrays.asList(matrix[0][0])));
		System.out.println(max);
	}
	static char[][] makeMatrix(BufferedReader br, int height) throws IOException{
		char[][] matrix = new char[height][];
		for(int i = 0; i < matrix.length; i++) {
			matrix[i] = br.readLine().trim().toCharArray();
		}
		return matrix;
	}
	static void dfs(char[][] matrix, int x, int y, Set<Character> set) {
		for(int i = 0; i < 4; i++) {
			int newX = x + direction.get(i).get(0);
			int newY = y + direction.get(i).get(1);
			if(canGo(newX, newY, matrix) && !set.contains(matrix[newY][newX])) {
				set.add(matrix[newY][newX]);
				dfs(matrix, newX, newY, set);
				set.remove(matrix[newY][newX]);
			}
		}
		
		if(set.size() > max) {
			max = set.size();
		}
	}
	static boolean canGo(int x, int y, char[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
}