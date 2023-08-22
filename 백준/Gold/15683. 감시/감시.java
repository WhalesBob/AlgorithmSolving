import java.io.*;
import java.util.*;

public class Main {
	static int min;
	static List<Point> cctv;
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0), Arrays.asList(0,1), 
			Arrays.asList(-1,0), Arrays.asList(0,-1));
	static Map<Integer, List<Integer>> cctvSight;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		cctv = new ArrayList<>();
		
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		
		int[][] matrix = makeMatrix(br, height, width);
		min = Integer.MAX_VALUE;
		cctvSight = new HashMap<>();
		
		cctvSight.put(1, Arrays.asList(0));
		cctvSight.put(2, Arrays.asList(0,2));
		cctvSight.put(3, Arrays.asList(0,3));
		cctvSight.put(4, Arrays.asList(0,2,3));
		cctvSight.put(5, Arrays.asList(0,1,2,3));
		
		
		int[] directionArray = new int[cctv.size()];
		
		duplicateCombination(matrix, directionArray, 0, directionArray.length);
		System.out.println(min);
	}
	static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
		int[][] matrix = new int[height][width];
		for(int y = 0; y < height; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int x = 0; x < width; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
				if(1 <= matrix[y][x] && matrix[y][x] <= 5) {
					cctv.add(new Point(x, y, matrix[y][x]));
				}
			}
		}
		return matrix;
	}
	static void duplicateCombination(int[][] matrix,int[] arr, int index, int r) {
		if(r == 0) {
			updateMin(cloneMatrix(matrix), arr);
			return;
		}
		for(int i = 0; i < 4; i++) {
			arr[index] = i;
			duplicateCombination(matrix,arr, index + 1, r-1);
		}
	}
	static void updateMin(int[][] matrix, int[] dir) {
		for(int i = 0; i < dir.length; i++) {
			Point target = cctv.get(i);
			updateMap(target, matrix, dir[i]);
		}
		
		int count = 0;
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				if(matrix[y][x] == 0) {
					count++;
				}
			}
		}
		
		if(min > count) {
			min = count;
		}
	}
	static int[][] cloneMatrix(int[][] matrix){
		int[][] clone = new int[matrix.length][matrix[0].length];
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				clone[y][x] = matrix[y][x];
			}
		}
		return clone;
	}
	static void updateMap(Point p, int[][] matrix, int dir) {
		List<Integer> sightList = cctvSight.get(p.value);
		
		for(Integer sight : sightList) {
			int x = p.x, y = p.y;
			int sightIndex = (sight + dir) % 4;
			while(true) {
				x += direction.get(sightIndex).get(0);
				y += direction.get(sightIndex).get(1);
				
				if(canGo(x,y,matrix) && matrix[y][x] != 6) {
					matrix[y][x] = 9;
				}else {
					break;
				}
			}
		}
		
	}
	static boolean canGo(int x, int y, int[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
	static class Point{
		int x;
		int y;
		int value;
		
		public Point(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
}