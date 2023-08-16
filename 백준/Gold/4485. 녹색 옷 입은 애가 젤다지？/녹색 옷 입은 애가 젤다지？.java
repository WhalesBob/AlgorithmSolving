import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0), Arrays.asList(0,1), 
			Arrays.asList(-1,0), Arrays.asList(0,-1));
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = 0;
		Queue<Node> queue = new ArrayDeque<>();
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) {
				break;
			}
			
			int[][] matrix = makeMatrix(br, n);
			System.out.printf("Problem %d: %d\n", ++count, getMinimum(queue,matrix));
		}
	}
	static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
		int[][] matrix = new int[size][size];
		for(int y = 0;  y < size; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int x = 0; x < size; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		return matrix;
	}
	static int getMinimum(Queue<Node> queue,int[][] matrix) {
		int[][] record = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < record.length; i++) {
			Arrays.fill(record[i], INF);
		}
		record[0][0] = matrix[0][0];
		queue.add(new Node(0,0));
		
		while(!queue.isEmpty()) {
			Node element = queue.remove();
			for(int i = 0; i < 4; i++) {
				int newX = element.x + direction.get(i).get(0);
				int newY = element.y + direction.get(i).get(1);
				if(canGo(newX, newY, matrix)) {
					boolean less = matrix[newY][newX] + record[element.y][element.x] < record[newY][newX];
					if(less) {
						record[newY][newX] = matrix[newY][newX] + record[element.y][element.x];
						queue.add(new Node(newX, newY));
					}
				}
			}
		}
		return record[record.length-1][record[0].length-1];
	}
	
	static int[][] copyMatrix(int[][] matrix){
		int[][] copy = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				copy[i][j] = matrix[i][j];
			}
		}
		return matrix;
	}
	static boolean canGo(int x, int y, int[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
	
	static class Node{
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return this.x * 10000 + this.y;
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			if(obj instanceof Node) {
				Node o = (Node)obj;
				return (this.x == o.x) && (this.y == o.y);
			}
			return false;
		}
	}
}