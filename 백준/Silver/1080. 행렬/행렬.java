import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] A = makeMatrix(br, n, m);
		int[][] B = makeMatrix(br, n, m);
		
		int maxY = n - 3 + 1;
		int maxX = m - 3 + 1;
		int count = 0;
		
		for(int y = 0; y < maxY; y++) {
			for(int x = 0; x< maxX; x++) {
				if(A[y][x] != B[y][x]) {
					changeMatrix(A, x, y);
					count++;
				}
			}
		}
		
		System.out.println(compareTwoMatrix(A, B) ? count : -1);
	}
	static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
		int[][] matrix = new int[height][width];
		for(int y = 0; y < height; y++) {
			char[] charArray = br.readLine().trim().toCharArray();
			
			for(int x = 0; x < width; x++) {
				matrix[y][x] = charArray[x] - '0';
			}
		}
		return matrix;
	}
	static void changeMatrix(int[][] matrix, int startX, int startY) {
		int endY = Math.min(startY + 3, matrix.length);
		int endX = Math.min(startX + 3, matrix[0].length);
		
		for(int y = startY; y < endY; y++) {
			for(int x = startX; x < endX; x++) {
				matrix[y][x] = (matrix[y][x] + 1) % 2;
			}
		}
	}
	static boolean compareTwoMatrix(int[][] matrixA, int[][] matrixB) {
		for(int y = 0; y < matrixA.length; y++) {
			for(int x = 0; x < matrixA[0].length; x++) {
				if(matrixA[y][x] != matrixB[y][x]) {
					return false;
				}
			}
		}
		return true;
	}
}