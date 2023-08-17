import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,-1), Arrays.asList(1,0), Arrays.asList(1,1));
	static Set<Point> alreadyHaveSet;
	static boolean arrived;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		char[][] matrix = makeMatrix(br, r, c);
		int count = 0;
		alreadyHaveSet = new HashSet<>();
		for(int y = 0; y < matrix.length; y++) {
			arrived = false;
			putPipe(0, y, matrix, new HashSet<>());
			if(arrived) {
				count++;
			}
		}

		System.out.println(count);
	}
	static void putPipe(int x, int y, char[][] matrix, Set<Point> set) {
		
		if(x == matrix[0].length - 1) {
			alreadyHaveSet.addAll(set);
			arrived = true;
			return;
		}
		
		for(int i = 0; i < 3 && !arrived; i++) {
			int newX = x + direction.get(i).get(0);
			int newY = y + direction.get(i).get(1);
			Point p = new Point(newX, newY);
			if(canGo(newX, newY, matrix) && matrix[newY][newX] == '.' && !alreadyHaveSet.contains(p)) {
				set.add(p);
				putPipe(newX, newY, matrix, set);
				set.remove(p);
			}
		}
        
        if(!arrived) {
			matrix[y][x] = 'x';
		}
	}
	static char[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
		char[][] matrix = new char[height][];
		for(int y = 0; y < height; y++) {
			matrix[y] = br.readLine().trim().toCharArray();
		}
		return matrix;
	}
	static boolean canGo(int x, int y, char[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length) ;
	}
	static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return this.y * 100_000 + this.x;
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Point) {
				Point o = (Point)obj;
				return this.x == o.x && this.y == o.y;
			}
			return false;
		}
	}
}