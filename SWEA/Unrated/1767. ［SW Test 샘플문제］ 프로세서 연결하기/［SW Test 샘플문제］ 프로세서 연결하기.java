import java.io.*;
import java.util.*;

public class Solution {
	static List<List<Integer>> pointList;
	static int minCount, maxConnect;
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0), Arrays.asList(0,1), Arrays.asList(-1,0), Arrays.asList(0,-1));
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			pointList = new ArrayList<>();
			minCount = Integer.MAX_VALUE;
			maxConnect = 0;
			
			int size = Integer.parseInt(br.readLine().trim());
			int[][] matrix = makeMatrix(br, size);
			
			putLine(matrix, 0, 0, 0);
			System.out.printf("#%d %d\n", test_case, minCount);
		}
	}
	static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
		int[][] matrix = new int[size][size];
		for(int y = 0; y < size; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int x = 0; x < size; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
				if(matrix[y][x] == 1) {
					pointList.add(Arrays.asList(x,y));
				}
			}
		}
		
		return matrix;
	}
	static void putLine(int[][] matrix, int index, int currentPut, int currentConnect) {
		if(index >= pointList.size()) {
			if(maxConnect < currentConnect) {
				minCount = currentPut;
				maxConnect = currentConnect;
			}else if(maxConnect == currentConnect && minCount > currentPut) {
				minCount = currentPut;
			}
			return;
		}
		
		int currentX = pointList.get(index).get(0);
		int currentY = pointList.get(index).get(1);
		
		if(isOutline(currentX, currentY, matrix)) {
			putLine(matrix, index + 1, currentPut, currentConnect + 1);
		}else {
			for(int directIdx = 0; directIdx < 4; directIdx++) {
				if(canPut(currentX, currentY, directIdx, matrix)) {
					int plusAdd = putOrDeleteOneLine(currentX, currentY, directIdx, matrix, true);
					putLine(matrix, index + 1, currentPut + plusAdd, currentConnect + 1);
					putOrDeleteOneLine(currentX, currentY, directIdx, matrix, false);
				}
			}
			
			putLine(matrix, index + 1, currentPut, currentConnect);
		}
	}
	static boolean isOutline(int x, int y, int[][] matrix) {
		return (x == 0 || x == matrix[0].length - 1) || (y == 0 || y == matrix.length - 1);
	}
	static boolean canPut(int x, int y, int directIndex, int[][] matrix) {
		while(true) {
			x = x + direction.get(directIndex).get(0);
			y = y + direction.get(directIndex).get(1);
			
			if(canGo(x, y, matrix)) {
				if(matrix[y][x] != 0) {
					return false;
				}
			}else {
				break;
			}
		}
		
		return true;
	}
	static int putOrDeleteOneLine(int x, int y, int directIdx, int[][] matrix, boolean goPut) {
		int count = 0;
		while(true) {
			x = x + direction.get(directIdx).get(0);
			y = y + direction.get(directIdx).get(1);
			if(!canGo(x, y, matrix)) {
				break;
			}
			
			matrix[y][x] = goPut ? 2 : 0;
			count++;
		}
		return count;
	}
	static boolean canGo(int x, int y, int[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
}