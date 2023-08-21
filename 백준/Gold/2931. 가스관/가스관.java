import java.io.*;
import java.util.*;

public class Main {
	static int startX, startY;
	static int currentX, currentY, findX, findY;
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1, 0), Arrays.asList(0, 1), Arrays.asList(-1, 0),
			Arrays.asList(0, -1));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int height = Integer.parseInt(st.nextToken()); 
		int width = Integer.parseInt(st.nextToken());

		char[][] matrix = makeMatrix(br, height, width); 
		boolean findFirst = false; 
		currentX = startX; 
		currentY = startY;
		int directIndex = 0; 
		
		List<Character> tryBlock = new ArrayList<>(Arrays.asList('|', '-', '1','2','3','4','+'));
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];

		for (; directIndex < 4; directIndex++) { 
			int newX = currentX + direction.get(directIndex).get(0);
			int newY = currentY + direction.get(directIndex).get(1);
			if (canGo(newX, newY, matrix) && tryBlock.contains(matrix[newY][newX])) {
				break;
			}
		}

		if (directIndex == 4) {
			findFirst = true; 
			for (int i = 0; i < 4; i++) {
				findX = currentX + direction.get(i).get(0); 
				findY = currentY + direction.get(i).get(1);

				if (canGo(findX, findY, matrix) && haveNext(findX, findY, matrix)) { 
					directIndex = i;
					break;
				}
			}
		}
		
		visited[currentY][currentX] = true;

		while (!findFirst) { 
			int newX = currentX + direction.get(directIndex).get(0);
			int newY = currentY + direction.get(directIndex).get(1); 

			if (matrix[newY][newX] == '.') { 
				findX = newX;
				findY = newY;
				break; 
			}

			switch (matrix[newY][newX]) { 
			case '1': 
				if (directIndex == 2)
					directIndex = 1;
				if (directIndex == 3)
					directIndex = 0;
				break;
			case '2':
				if (directIndex == 1) directIndex = 0;
				if (directIndex == 2) directIndex = 3;
				break;
			case '3':
				if (directIndex == 0)
					directIndex = 3;
				if (directIndex == 1)
					directIndex = 2;
				break;
			case '4':
				if (directIndex == 0)
					directIndex = 1;
				if (directIndex == 3)
					directIndex = 2;
				break;
			}
			visited[currentY][currentX] = true;
			currentX = newX;
			currentY = newY; 
		}


		for (Character block : tryBlock) {
			matrix[findY][findX] = block; 
			boolean[][] clone = cloneMatrix(visited);
			if (canGoToZ(directIndex, currentX, currentY, matrix, clone) && checkVisit(matrix, clone)) { 
				System.out.printf("%d %d %c\n", findY + 1, findX + 1, block);
				break;
			}
		}
	}

	static char[][] makeMatrix(BufferedReader br, int height, int width) throws IOException {
		char[][] matrix = new char[height][];
		for (int y = 0; y < matrix.length; y++) {
			matrix[y] = br.readLine().trim().toCharArray();
			for (int x = 0; x < width; x++) {
				if (matrix[y][x] == 'M') {
					startX = x;
					startY = y;
				}
			}
		}
		return matrix;
	}

	static boolean canGo(int x, int y, char[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}

	static boolean haveNext(int x, int y, char[][] matrix) {
		for (int i = 0; i < 4; i++) {
			int newX = x + direction.get(i).get(0);
			int newY = y + direction.get(i).get(1);

			if (canGo(newX, newY, matrix) && matrix[newY][newX] != '.') {
				return true;
			}
		}
		return false;
	}

	static boolean canGoToZ(int firstDirect, int startX, int startY, char[][] matrix, boolean[][] visited) {
		int direct = firstDirect, x = startX, y = startY;
		
		while(true) {			
			visited[y][x] = true;
			int nextX = x + direction.get(direct).get(0);
			int nextY = y + direction.get(direct).get(1);
			
			if(!canGo(nextX, nextY, matrix)) {
				return false;
			}
			
			if(matrix[nextY][nextX] == 'Z') {
				visited[nextY][nextX] = true;
				return true;
			}
			
			if(isBlocked(direct, nextX, nextY, matrix) || (matrix[nextY][nextX] != '+' && visited[nextY][nextX])) {
				return false;
			}
		
			switch (matrix[nextY][nextX]) { 
			case '1': 
				if (direct == 2)
					direct = 1;
				if (direct == 3)
					direct = 0;
				break;
			case '2':
				if (direct == 1) direct = 0;
				if (direct == 2) direct = 3;
				break;
			case '3':
				if (direct == 0)
					direct = 3;
				if (direct == 1)
					direct = 2;
				break;
			case '4':
				if (direct == 0)
					direct = 1;
				if (direct == 3)
					direct = 2;
				break;
			}
			
			
			x = nextX; y = nextY;
		}
	}
	static boolean isBlocked(int direct, int x, int y, char[][] matrix) {
		if(!canGo(x,y,matrix)) {
			return true;
		}
		if(matrix[y][x] == '+') {
			return false;
		}
		return (direct % 2 == 1 && matrix[y][x] == '-') || (direct % 2 == 0 && matrix[y][x] == '|');	
	}
	static boolean checkVisit(char[][] matrix, boolean[][] visited) {
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				if(matrix[y][x] != '.' && !visited[y][x]) {
					return false;
				}
			}
		}
		return true;
	}
	static boolean[][] cloneMatrix(boolean[][] matrix) {
		boolean[][] clone = new boolean[matrix.length][matrix[0].length];
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				clone[y][x] = matrix[y][x];
			}
		}
		return clone;
	}
}