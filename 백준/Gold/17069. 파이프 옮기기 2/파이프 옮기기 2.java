import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());
		
		long[][] matrix = makeMatrix(br, size);

		long[][] rightMatrix = new long[matrix.length][matrix[0].length];
		long[][] slideMatrix = new long[matrix.length][matrix[0].length];
		long[][] downMatrix = new long[matrix.length][matrix[0].length];
		
		rightMatrix[0][1] = 1;
		
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 1; x < matrix[0].length; x++) {
				if(canComeFromLeft(x, y, matrix)) {
					rightMatrix[y][x] += (rightMatrix[y][x-1] + slideMatrix[y][x-1]);
				}
				
				if(canComeFromSlide(x, y, matrix)) {
					slideMatrix[y][x] += (slideMatrix[y-1][x-1] + rightMatrix[y-1][x-1] + downMatrix[y-1][x-1]);
				}
				
				if(canComeFromUp(x, y, matrix)) {
					downMatrix[y][x] += (downMatrix[y-1][x] + slideMatrix[y-1][x]);
				}
			}
		}
		
		System.out.println(rightMatrix[size-1][size-1] + slideMatrix[size-1][size-1] + downMatrix[size-1][size-1]);
	}
	static boolean canComeFromLeft(int x, int y, long[][] matrix) {
		return canGo(x-1,y,matrix) && matrix[y][x] != -1 && matrix[y][x-1] != -1;
	}
	
	static boolean canComeFromSlide(int x, int y, long[][] matrix) {
		return canGo(x-1,y-1,matrix) && matrix[y-1][x-1] != -1 
				&& matrix[y][x-1] != -1 && matrix[y-1][x] != -1 && matrix[y][x] != -1;
	}
	static boolean canComeFromUp(int x, int y, long[][] matrix) {
		return canGo(x, y-1, matrix) && matrix[y-1][x] != -1 && matrix[y][x] != -1;
	}
	
	static long[][] makeMatrix(BufferedReader br, int size) throws IOException{
		long[][] matrix = new long[size][size];
		for(int y = 0; y < size; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int x = 0; x < size; x++) {
				matrix[y][x] = Long.parseLong(st.nextToken());
				if(matrix[y][x] == 1) {
					matrix[y][x] = -1;
				}
			}
		}
		return matrix;
	}
	static boolean canGo(int x, int y, long[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
}