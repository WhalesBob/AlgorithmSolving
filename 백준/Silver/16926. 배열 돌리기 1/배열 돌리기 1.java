import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(0,1), Arrays.asList(1,0), Arrays.asList(0,-1), Arrays.asList(-1,0));
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[][] matrix = makeMatrix(br, n, m);
		
		for(int i = 0; i < r; i++) {
			int s = 0;
			while(s < Math.min(n, m) - s) {
				rotateOneLayer(matrix, s, s, matrix[0].length - s, matrix.length - s);
				s++;
			}
		}
		
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				bw.write(matrix[y][x] + " ");
			}
			bw.write("\n");
		}
		bw.flush();
	}
	static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
		int[][] matrix = new int[height][width];
		for(int y = 0; y < height; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int x = 0; x < width; x++ ) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		return matrix;
	}
	static void rotateOneLayer(int[][] matrix, int startX, int startY, int endX, int endY) {
		Queue<Integer> queue = new ArrayDeque<>();
		int x = startX, y = startY, initX = startX, initY = startY;
		int index = 0;
		queue.add(matrix[startY][startX]);
		do {
			int nextX = x + direction.get(index).get(0);
			int nextY = y + direction.get(index).get(1);
			
			if(!isInBound(nextX, nextY, startX, startY, endX, endY)) {
				index++;
				continue;
			}
			
			queue.add(matrix[nextY][nextX]);
			matrix[nextY][nextX] = queue.remove();
			x = nextX; y = nextY;
		}while(index < 4);
	}
	static boolean isInBound(int x, int y, int startX, int startY, int endX, int endY ) {
		return (startX <= x && x < endX) && (startY <= y && y < endY);
	}
}