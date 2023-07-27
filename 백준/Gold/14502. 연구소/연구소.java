import java.io.*;
import java.util.*;

public class Main {
	static int maxCount;
	static List<List<Integer>> virusList;
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(-1,0), Arrays.asList(0,-1),
			Arrays.asList(1,0), Arrays.asList(0,1));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		virusList = new ArrayList<>();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] matrix = makeMatrix(n, m, br);
		maxCount = Integer.MIN_VALUE;
		
		combination(matrix, 0, 0, 3);
		System.out.println(maxCount);
	}
	static int[][] makeMatrix(int height, int width, BufferedReader br) throws IOException{
		int[][] matrix = new int[height][width];
		for(int y = 0; y < height; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int x= 0; x < width; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
				if(matrix[y][x] == 2) {
					virusList.add(Arrays.asList(x,y));
				}
			}
		}
		return matrix;
	}
	static void combination(int[][] matrix,int x, int y, int r) {
		if(r == 0) {
			int[][] newMatrix = cloneMatrix(matrix);
			maxCount = Math.max(maxCount, getSafetyCount(newMatrix));
			return ;
		}	
		
		while(x < matrix[0].length && y < matrix.length) {
			if (matrix[y][x] == 0) {
				matrix[y][x] = 1;
				int newY = x + 1 < matrix[0].length ? y : y + 1;
				int newX = x + 1 < matrix[0].length ? x + 1 : 0;
				combination(matrix,newX,newY,r-1);
				matrix[y][x] = 0;
			}
			
			y = x + 1 < matrix[0].length ? y : y + 1;
			x = x + 1 < matrix[0].length ? x + 1 : 0;
		}
	}
	static int getSafetyCount(int[][] matrix) {	
		
		Queue<List<Integer>> virusQueue = new LinkedList<>(virusList);
		
		while(!virusQueue.isEmpty()) {
			List<Integer> element = virusQueue.remove();
			int elementX = element.get(0);
			int elementY = element.get(1);
			
			for(int i = 0; i < 4; i++) {
				int newX = elementX + direction.get(i).get(0);
				int newY = elementY + direction.get(i).get(1);
				
				if(canGo(newX, newY, matrix)) {
					matrix[newY][newX] = 2;
					virusQueue.add(Arrays.asList(newX, newY));
				}
			}
		}
		
		int count=0;
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				if(matrix[y][x] == 0) {
					count ++;
				}
			}
		}
		return count;
	}
	static int[][] cloneMatrix(int[][] matrix){
		int[][] newMatrix = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				newMatrix[i][j] = matrix[i][j];
			}
		}
		return newMatrix;
	}
	
	static boolean canGo(int x, int y, int[][] matrix) {
		boolean physicallyCanGo = (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
		return physicallyCanGo && matrix[y][x] == 0;
	}
}