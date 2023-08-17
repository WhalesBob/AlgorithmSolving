import java.io.*;
import java.util.*;

public class Solution {
	static int currentX, currentY;
	static Map<Character, List<Integer>> directMap;
	static Map<Character, Character> carMap;
	static char currentDirect;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		directMap = new HashMap<>();
		carMap = new HashMap<>();
		directMap.put('U', Arrays.asList(0,-1));
		carMap.put('U', '^');
		directMap.put('D', Arrays.asList(0,1));
		carMap.put('D', 'v');
		directMap.put('L', Arrays.asList(-1,0));
		carMap.put('L', '<');
		directMap.put('R', Arrays.asList(1,0));
		carMap.put('R', '>');
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int height = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			
			char[][] matrix = makeMatrix(br, height, width);
			int inst = Integer.parseInt(br.readLine());
			char[] instructions = makeInstructions(br, inst);
			
			doInstructions(matrix, instructions);
			
			bw.write(String.format("#%d ", test_case));
			for(int y = 0; y < matrix.length; y++) {
				for(int x = 0; x < matrix[0].length; x++) {
					bw.write(Character.toString(matrix[y][x]));
				}
				bw.write("\n");
			}
			bw.flush();
		}
	}
	static void doInstructions(char[][] matrix, char[] inst){

		for(char c : inst) {
			switch(c) {
				case 'S': shoot(matrix); break;
				default:
					int newX = currentX + directMap.get(c).get(0);
					int newY = currentY + directMap.get(c).get(1);
					
					if(canGo(newX, newY, matrix) && matrix[newY][newX] == '.') {
						matrix[currentY][currentX] = '.';
						currentX = newX; currentY = newY;
					}
					matrix[currentY][currentX] = carMap.get(c);
					currentDirect = c;
			}
		}
	}
	static void shoot(char[][] matrix) {
		int newX = currentX, newY  = currentY;
		
		outer : while(true) {
			newX += directMap.get(currentDirect).get(0);
			newY += directMap.get(currentDirect).get(1);
			
			if(!canGo(newX, newY, matrix)) {
				break;
			}
			
			if(matrix[newY][newX] != '.' && matrix[newY][newX] != '-') {
				if(matrix[newY][newX] == '*') {
					matrix[newY][newX] = '.';
				}
				break outer;
			}
			
		}
	}
	static char[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
		Set<Character> set = new HashSet<>(Arrays.asList('^','v','<','>'));
		char[][] matrix = new char[height][];
		for(int y = 0; y < height; y++) {
			matrix[y] = br.readLine().trim().toCharArray();
			for(int x = 0; x < width; x++) {
				if(set.contains(matrix[y][x])) {
					currentY = y; currentX = x;
					findKey:for(char key : carMap.keySet()) {
						if(carMap.get(key) == matrix[y][x]) {
							currentDirect = key;
							break findKey;
						}
					}
				}
			}
		}
		return matrix;
	}
	static char[] makeInstructions(BufferedReader br, int size) throws IOException {
		return br.readLine().trim().toCharArray();
	}
	static boolean canGo(int x, int y, char[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
}