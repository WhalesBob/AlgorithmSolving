import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] matrix = makeMatrix(br, n);
		long[][] count = new long[matrix.length][matrix[0].length];
		count[0][0] = 1;
		System.out.println(getCount(matrix, count));
	}
	static long getCount(int[][] matrix, long[][] count) {
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				int value = matrix[y][x];
				if(value == 0) {
					continue;
				}
				
				if(canGo(x + value, y, matrix)) {
					count[y][x + value] += count[y][x];
				}
				if(canGo(x, y + value, matrix)) {
					count[y + value][x] += count[y][x];
				}
			}
		}
		
		return count[matrix.length - 1][matrix[0].length - 1];
	}
	static boolean canGo(int x, int y, int[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
	static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
		int[][] matrix = new int[size][size];
		for(int y = 0; y < size; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int x = 0; x < size; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		return matrix;
 	}
}