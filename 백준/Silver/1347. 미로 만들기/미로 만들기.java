import java.io.*;
import java.util.*;

public class Main {
	static int minX, minY, maxX, maxY;
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(0,1), 
			Arrays.asList(-1,0), Arrays.asList(0,-1), Arrays.asList(1,0));
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int size = Integer.parseInt(br.readLine());
		
		char[] input = br.readLine().toCharArray();
		char[][] result = getMap(input);
		
		for(int y = 0; y < result.length; y++) {
			for(int x = 0; x < result[0].length; x++) {
				bw.write(result[y][x] + "");
			}
			bw.write("\n");
		}
		
		bw.flush();
	}
	static char[][] getMap(char[] input){
		Set<Integer> placeSet = new HashSet<>();
		minX = minY = 50;
		maxX = maxY = 50;
		int x = 50, y = 50, d = 0;
		
		placeSet.add(getKey(x,y));
		
		for(char c : input) {
			switch(c) {
				case 'F':
					x += direction.get(d).get(0);
					y += direction.get(d).get(1);
					
					updateMaxMin(x, y);
					placeSet.add(getKey(x, y));
					break;
				case 'R':
					d = (d + 1) % 4;
					break;
				case 'L':
					d = (d + 3) % 4;
					break;
			}		
		}
		
		char[][] result = new char[maxY - minY + 1][maxX - minX + 1];
		for(char[] row : result) {
			Arrays.fill(row, '#');
		}
		
		for(Integer key : placeSet) {
			int placeY = (key / 1000) - minY;
			int placeX = (key % 1000) - minX;
			
			result[placeY][placeX] = '.';
		}
		
		return result;
	}
	static void updateMaxMin(int x, int y) {
		if(x < minX) {
			minX = x;
		}
		
		if(x > maxX) {
			maxX = x;
		}
		
		if(y < minY) {
			minY = y;
		}
		
		if(y > maxY) {
			maxY = y;
		}
	}
	
	static int getKey(int x, int y) {
		return 1000 * y + x;
	}
}