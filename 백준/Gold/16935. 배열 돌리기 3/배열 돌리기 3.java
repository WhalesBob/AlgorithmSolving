import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> pointList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[][] matrix = makeMatrix(br, height, width);
		
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i < r; i++) {
			int operation = Integer.parseInt(st.nextToken());
			
			switch(operation) {
				case 1: matrix = mirrorByUpDown(matrix); break;
				case 2: matrix = mirrorByLeftRight(matrix); break;
				case 3: matrix = turnLeft(matrix); break;
				case 4: matrix = turnRight(matrix); break;
				case 5: matrix = move(matrix, 1); break;
				case 6: matrix = move(matrix, -1); break;
			}
		}
		
		printMatrix(bw, matrix);
	}
	static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
		int[][] matrix = new int[height][width];
		for(int y = 0; y < height; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int x = 0; x < width; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		return matrix;
	}
	static int[][] mirrorByUpDown(int[][] matrix) {
		for(int y = 0; y < matrix.length / 2; y++) {
			int down = matrix.length - y - 1;
			int[] temp = matrix[y];
			matrix[y] = matrix[down];
			matrix[down] = temp;
		}
		return matrix;
	}
	static int[][] mirrorByLeftRight(int[][] matrix) {
		int[][] newMatrix = new int[matrix.length][matrix[0].length];
		for(int x = 0; x < matrix[0].length; x++) {
			int right = matrix[0].length - x - 1;
			for(int y = 0; y < matrix.length; y++) {
				newMatrix[y][right] = matrix[y][x];
			}
		}
		return newMatrix;
	}
	static int[][] turnLeft(int[][] matrix) {
		int[][] newMatrix = new int[matrix[0].length][matrix.length];
		Stack<Integer> stack = new Stack<>();
		for(int x = 0; x < matrix[0].length; x++) {
			for(int y = 0; y < matrix.length; y++) {
				stack.push(matrix[y][x]);
			}
		
			for(int y = 0; y < matrix.length; y++) {
				newMatrix[x][y] = stack.pop();
			}
		}
		
		return newMatrix;
	}
	static int[][] turnRight(int[][] matrix) {
		Queue<Integer> queue = new ArrayDeque<>();
		int[][] newMatrix = new int[matrix[0].length][matrix.length];
		
		for(int x = 0; x < matrix[0].length; x++) {
			for(int y = 0; y < matrix.length; y++) {
				int right = matrix[0].length - x - 1;
				queue.add(matrix[y][right]);
			}
			
			for(int y = 0; y < matrix.length; y++) {
				newMatrix[x][y] = queue.remove();
			}
		}
		
		return newMatrix;
	}
	static int[][] move(int[][] matrix, int move) {
		int[][] newMatrix = new int[matrix.length][matrix[0].length];
		pointList = Arrays.asList(Arrays.asList(0,0), Arrays.asList(matrix[0].length / 2, 0),
				Arrays.asList(matrix[0].length / 2, matrix.length / 2), Arrays.asList(0, matrix.length / 2));
		
		for(int i = 0; i < 4; i++) {
			int moveIndex = (i + move + 4) % 4;
			int x = pointList.get(i).get(0);
			int y = pointList.get(i).get(1);
			int toX = pointList.get(moveIndex).get(0);
			int toY = pointList.get(moveIndex).get(1);
			
			for(int dy = 0; dy < (matrix.length / 2); dy++) {
				for(int dx = 0; dx < (matrix[0].length / 2); dx++) {
					newMatrix[toY + dy][toX + dx] = matrix[y + dy][x + dx];
				}
			}
			
		}
		
		return newMatrix;
	}
	static void printMatrix(BufferedWriter bw, int[][] matrix) throws IOException {
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				bw.write(matrix[y][x] + " ");
			}
			bw.write("\n");
		}
		bw.flush();
	}
}