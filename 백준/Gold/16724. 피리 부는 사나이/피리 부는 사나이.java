import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0), 
			Arrays.asList(0,1), Arrays.asList(-1,0), Arrays.asList(0,-1));
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		
		int[][] matrix = makeMatrix(br, height, width);
		parent = new int[height * width];
		for(int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
		
		System.out.println(getCircleUnionCount(matrix));
	}
	static int getCircleUnionCount(int[][] matrix) {
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				int newX = x + direction.get(matrix[y][x]).get(0);
				int newY = y + direction.get(matrix[y][x]).get(1);
				
				union(getKey(x, y, matrix), getKey(newX, newY, matrix));
			}
		}
		
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i  < parent.length; i++) {
			set.add(find(i));
		}
		
		return set.size();
	}
	static int getKey(int x, int y, int[][] matrix) {
		return y * matrix[0].length + x;
	}
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		parent[x] = find(parent[x]);
		return parent[x];
	}
	static void union(int a, int b) {
		a = find(a); b = find(b);
		
		parent[b] = a;
	}
	static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
		int[][] matrix = new int[height][width];
		for(int y = 0; y < height; y++) {
			char[] inputArray = br.readLine().trim().toCharArray();
			
			for(int x = 0; x < width; x++) {
				int intInput = -1;
				switch(inputArray[x]) {
					case 'R': intInput = 0; break;
					case 'D': intInput = 1; break;
					case 'L': intInput = 2; break;
					case 'U': intInput = 3; break;
				}
				
				matrix[y][x] = intInput;
			}
		}
		
		return matrix;
	}
}
