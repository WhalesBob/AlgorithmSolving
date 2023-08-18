import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int max,limit;
	static int[][] matrix;
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(-1,0), Arrays.asList(0,-1), Arrays.asList(1,0));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());
		
		max = 0;
		matrix = makeMatrix(br, height, width);
		int[] archerArray = new int[width];
		for(int i = 0; i < archerArray.length; i++) {
			archerArray[i] = i;
		}
		combination(archerArray, new boolean[width], 0, width, 3);
		System.out.println(max);
	}
	static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
		int[][] matrix =new int[height][width];
		for(int y = 0; y < height; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int x = 0; x < width; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		return matrix;
	}
	static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
	static int[][] copyMatrix(int[][] matrix){
		int[][] newMatrix = new int[matrix.length][matrix[0].length];
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				newMatrix[y][x] = matrix[y][x];
			}
		}
		return newMatrix;
	}
	static int[] getArcher(int[] arr, boolean[] visited) {
		int[] array = new int[3];
		int index = 0;
		for(int i = 0; i < arr.length; i++) {
			if(visited[i]) {
				array[index++] = arr[i];
			}
		}
		return array;
	}
	static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
		if(r == 0) {
			int[] archer = getArcher(arr, visited);
			int value = getMaxCount(copyMatrix(matrix), archer);
			if(value > max) {
				max = value;
			}
			return ;
		}
		
		for(int i = start; i < n; i++) {
			visited[i] = true;
			combination(arr, visited, i + 1, n, r-1);
			visited[i] = false;
		}
	}
	static int getMaxCount(int[][] map, int[] archer) {
		Set<Point> set = new HashSet<>();

		for(int i = 0; i < map.length; i++) {
			int y = map.length - i;
			Queue<Point> queue = new ArrayDeque<>();
			for(int a = 0;  a < 3; a++) {
				queue.clear();
				Point enemy = findClosest(map,new Point(archer[a], y), queue);
				if(enemy != null) {
					set.add(enemy);
				}
			}
			
			setNextmap(map, y-1, set);
		}
		
		return set.size();
	}
	static Point findClosest(int[][] map,Point arch, Queue<Point> queue) {
		queue.add(arch);
		int distance = 0;
		
		while(!queue.isEmpty() && distance < limit) {
			boolean[][] visited = new boolean[map.length][map[0].length];
			distance++;
			int queueSize = queue.size();
			for(int q = 0; q < queueSize; q++) {
				Point element = queue.remove();
				for(int i = 0; i < 3; i++) {
					int newX = element.x + direction.get(i).get(0);
					int newY = element.y + direction.get(i).get(1);
					Point p = new Point(newX, newY);
					if(canGo(newX, newY, arch.y, map) && !visited[p.y][p.x]) {
						if(map[newY][newX] == 1) {
							return p;
						}
						queue.add(p);
						visited[p.y][p.x] = true;
					}
				}
			}
		}
		
		return null;
	}
	static void setNextmap(int[][] map, int targetY, Set<Point> set) {
		for(Point p : set) {
			map[p.y][p.x] = 0;
		}
		for(int x = 0; x < map[0].length; x++) {
			map[targetY][x] = 0;
		}
	}
	static boolean canGo(int x, int y, int limitY, int[][] map) {
		return (0 <= x && x < map[0].length) && (0 <= y && y < limitY);
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
			return (1000 * y) + x;
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