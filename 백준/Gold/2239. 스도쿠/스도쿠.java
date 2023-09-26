import java.io.*;

public class Main {
	static int[][] matrix;
	BufferedWriter bw;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] box = makeBoxMatrix(br);
		getCompleteMatrix(0, 0, box);
	}
	static void printAllMatrix() {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		try {
			for(int y = 0; y < 9; y++) {
				for(int x = 0; x < 9; x++) {
					bw.write(Integer.toString(matrix[y][x]));
				}
				bw.write("\n");
			}
			bw.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	static void getCompleteMatrix(int currentY, int currentX, int[][] box) {
		int x = currentX, y = currentY;
		
		while(x < 9 && y < 9) {
			if(matrix[y][x] == 0) {
				for(int n = 1; n <= 9; n++) {
					matrix[y][x] = n;
					
					if(isAllOK(x, y, box, n)) {
						checkVisited(box, x, y, n);
						getCompleteMatrix(y, x, box);
						checkNotVisited(box, x, y, n);
					}
				}
				matrix[y][x] = 0;
				return;
			}else {
				if(x == 8) {
					y++;
					x = 0;
				}else {
					x++;
				}
			}
		}
		
		if(x >= 9 || y >= 9) {
			printAllMatrix();
			System.exit(0);
		}
	}
	static void checkVisited(int[][] box, int x, int y, int value) {
		matrix[y][9] |= (1 << value);
		matrix[9][x] |= (1 << value);
		
		box[y / 3][x / 3] |= (1 << value);
	}
	static void checkNotVisited(int[][] box, int x, int y, int value) {
		matrix[y][9] &= ~(1 << value);
		matrix[9][x] &= ~(1 << value);
		
		box[y / 3][x / 3] &= ~(1 << value);
	}
	static boolean isAllOK(int x, int y, int[][] box, int value) {
		return isXOK(x, y, value) && isYOK(x, y, value) && isBoxOK(x, y, box, value);
	}
	static boolean isXOK(int x, int y, int value) {
		return (matrix[y][9] & (1 << value)) == 0;
	}
	static boolean isYOK(int x, int y, int value) {
		return (matrix[9][x] & (1 << value)) == 0;
	}
	static boolean isBoxOK(int x, int y, int[][] box, int value) {
		return (box[y / 3][x / 3] & (1 << value)) == 0;
	}
	
	static int[][] makeBoxMatrix(BufferedReader br) throws IOException{
		matrix = new int[10][10];
		int[][] box = new int[3][3];
		for(int y = 0; y < 9; y++) {
			char[] input = br.readLine().trim().toCharArray();
			for(int x = 0; x < 9; x++) {
				matrix[y][x] = input[x] - '0';
				matrix[y][9] |= (1 << matrix[y][x]);
				matrix[9][x] |= (1 << matrix[y][x]);
				box[y / 3][x / 3] |= (1 << matrix[y][x]);
			}
		}
		return box;
	}
}