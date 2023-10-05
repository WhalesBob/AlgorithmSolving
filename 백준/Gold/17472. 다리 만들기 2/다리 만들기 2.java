import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0), 
			Arrays.asList(0,1), Arrays.asList(-1,0), Arrays.asList(0,-1));
	static List<Island> islandArray;
	static int[] parent;
	static PriorityQueue<Integer> priorityQueue;

	static final int INF = 100_000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());

		int[][] matrix = makeMatrix(br, height, width);
		
		islandArray = getAllIsland(matrix);
		
		priorityQueue = new PriorityQueue<>();
		int[][] minLengthMatrix = getMinimumLength(matrix);
		
		if(priorityQueue.size() == 0) {
			System.out.println(-1);
			return;
		}
		
		parent = new int[islandArray.size()];
		for(int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
		
		int sum = 0;
		int count = 0;
		
		try {
			while(count < parent.length - 1) {
				int element = priorityQueue.poll();
				int length = element / 1000;
				element %= 1000;
				
				int start = element / 10;
				int end = element % 10;
				
				int rootA = find(start);
				int rootB = find(end);
				
				if(rootA != rootB) {
					sum += length;
					union(start, end);
					count++;
				}
			}
			System.out.println(sum);
		}catch(NullPointerException e) {
			System.out.println(-1);
		}
	}
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		parent[b] = a;
	}
	
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		parent[x] = find(parent[x]);
		return parent[x];
	}
	static int[][] getMinimumLength(int[][] matrix){
		int[][] minimumMatrix = new int[islandArray.size()][islandArray.size()];
		
		for(int i = 0; i < minimumMatrix.length; i++) {
			Arrays.fill(minimumMatrix[i], INF);
			minimumMatrix[i][i] = 0;
		}
		
		for(int l = 0; l < islandArray.size(); l++) {
			Island land = islandArray.get(l);
			for(int element : land.edgeLand) {
				int x = element % 1000;
				int y = element / 1000;
				for(int i = 0; i < 4; i++) {
					int newX = x + direction.get(i).get(0);
					int newY = y + direction.get(i).get(1);
					
					if(canGo(newX, newY, matrix) && matrix[newY][newX] == 0) {
						int result = getTourResult(x, y, i, matrix);
						if(result > 0) {
							int target = (result / 1000) - 1;
							int length = result % 1000;
							
							if(length >= 2 && minimumMatrix[l][target] > length) {
								minimumMatrix[l][target] = minimumMatrix[target][l] = length;
								priorityQueue.add(makeDistanceInfo(l, target, minimumMatrix[l][target]));
							}
						}
					}
				}
			}
		}
		
		return minimumMatrix;
	}
	static int getTourResult(int x, int y, int directionIdx, int[][] matrix) {
		int count = 0;
		while(true) {
			x += direction.get(directionIdx).get(0);
			y += direction.get(directionIdx).get(1);
			
			if(!canGo(x, y, matrix)) {
				break;
			}else if(matrix[y][x] != 0) {
				return getKey(count, matrix[y][x]);
			}
			
			count++;
		}
		
		return -1;
	}
	static List<Island> getAllIsland(int[][] matrix) {
		
		List<Island> islandList = new ArrayList<>();
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		Queue<Integer> queue = new ArrayDeque<>();
		int count = 0;
		
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				if(!visited[y][x] && matrix[y][x] == 1) {
					queue.add(getKey(x, y));
					visited[y][x] = true;
					islandList.add(getOneIsland(matrix, visited, queue, ++count));
				}
			}
		}
		
		return islandList;
	}
	static Island getOneIsland(int[][] matrix, boolean[][] visited, Queue<Integer> queue, int islandCount) {
		
		Island island = new Island();
		
		while(!queue.isEmpty()) {
			int element = queue.remove();
			int y = element / 1000;
			int x = element % 1000;
			
			matrix[y][x] = islandCount;
			int count = 0;
						
			for(int i = 0; i < 4; i++) {
				int newX = x + direction.get(i).get(0);
				int newY = y + direction.get(i).get(1);
				
				if(!canGo(newX, newY, matrix)) {
					count++;
				}else if(matrix[newY][newX] > 0) {
					count++;
					if(!visited[newY][newX]) {
						visited[newY][newX] = true;
						queue.add(getKey(newX, newY));	
						
					}
				}
			}
			
			if(0 <= count && count < 4) {
				island.edgeLand.add(getKey(x, y));
			}
		}
		
		return island;
	}
	static boolean canGo(int x, int y, int[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
	
	static int getKey(int x, int y) {
		return 1000 * y + x;
	}
	static int makeDistanceInfo(int start, int end, int distance) {
		return distance * 1000 + start * 10 + end;
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
	static class Island{
		Set<Integer> edgeLand;

		public Island() {
			this.edgeLand = new HashSet<>();
		}
	}
}