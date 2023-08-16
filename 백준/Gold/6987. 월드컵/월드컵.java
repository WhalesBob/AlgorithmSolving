import java.io.*;
import java.util.*;

public class Main {
	static boolean possible;
	static int[] drawCount;
	static boolean notFive;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		drawCount = new int[4];
		int[][][] input = makeMatrix(br);
		int[] result = new int[4];
		
		for(int i = 0; i < 4; i++) {
			Arrays.sort(input[i], (o1,o2) -> (o1[0] == o2[0]) ? Integer.compare(o2[2], o1[2]) : Integer.compare(o2[0], o1[0]));
			possible = false;
			checkPossible(input[i], 0, 1);
			result[i] = possible ? 1 : 0;
		}
		
		System.out.printf("%d %d %d %d", result[0], result[1], result[2], result[3]);
	}
	static int[][][] makeMatrix(BufferedReader br) throws IOException{
		int[][][] third = new int[4][][];
		
		for(int i = 0; i < 4; i++) {
			third[i] = new int[6][3];
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int y = 0; y < 6; y++) {
				for(int x = 0; x < 3; x++) {
					third[i][y][x] = Integer.parseInt(st.nextToken());
				}
				if(third[i][y][1] > 0) {
					drawCount[i] += third[i][y][1];
				}
			}
		}
		
		return third;
	}
	static void checkPossible(int[][] matrix, int index, int opponent) {
		if(index >= 5) {
			boolean isAllZero = true;
			checkAll:for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix[0].length; j++) {
					if(matrix[i][j] != 0) {
						isAllZero = false;
						break checkAll;
					}
				}
			}
			
			if(isAllZero) {
				possible = true;
			}
			
			return;
		}
		
		for(int i = 0; i <= 2 && !possible; i++) {
			if(matrix[index][i] > 0 && matrix[opponent][2-i] > 0) {
				int[][] copy = copyMatrix(matrix);
				copy[index][i]--;
				copy[opponent][2-i]--;
				if(opponent + 1 < 6) {
					checkPossible(copy, index, opponent + 1);
				}
				else {
					checkPossible(copy, index + 1, index + 2);
				}
			}
		}
	}
	static int[][] copyMatrix(int[][] matrix){
		int[][] copy = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < copy.length; i++) {
			for(int j = 0; j < copy[0].length; j++) {
				copy[i][j] = matrix[i][j];
			}
		}
		return copy;
	}
}