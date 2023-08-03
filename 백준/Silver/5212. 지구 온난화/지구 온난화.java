import java.io.*;
import java.util.*;
public class Main {
	static Set<List<Integer>> changeSet;
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0), Arrays.asList(0,1), 
			Arrays.asList(-1,0),Arrays.asList(0,-1));
	static Integer maxX, maxY, minX, minY;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		
		char[][] map = makeMap(height, width, br);
		
		changeSet = new HashSet<>();
		changeMap(map);
		
		Set<Integer> xset = new HashSet<>();
		Set<Integer> yset = new HashSet<>();
		
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[0].length; x++) {
				if(map[y][x] == 'X') {
					xset.add(x);
					yset.add(y);
				}
			}
		}
		
		List<Integer> xBound = new ArrayList<Integer>(Arrays.asList(Integer.MAX_VALUE, maxX = Integer.MIN_VALUE));
		List<Integer> yBound = new ArrayList<Integer>(Arrays.asList(Integer.MAX_VALUE, maxX = Integer.MIN_VALUE));

		changeMinMax(xset, xBound);
		changeMinMax(yset, yBound);
		
		printMap(map, xBound, yBound, bw);
		bw.flush();
	}
	static void printMap(char[][] map ,List<Integer> xBound, List<Integer> yBound, BufferedWriter bw) throws IOException {
		for(int y = yBound.get(0); y <= yBound.get(1); y++) {
			for(int x = xBound.get(0); x <= xBound.get(1); x++) {
				bw.write(map[y][x]);
			}
			bw.write("\n");
		}
	}
	static void changeMinMax(Set<Integer> set, List<Integer> bound) {
		for(Integer e : set) {
			if(e < bound.get(0)) {
				bound.set(0, e);
			}
			if(e > bound.get(1)) {
				bound.set(1, e);
			}
		}
	}
	static char[][] makeMap(int height, int width, BufferedReader br) throws IOException{
		char[][] map = new char[height][width];
		for(int y = 0; y < height; y++) {
			map[y] = br.readLine().trim().toCharArray();
		}
		return map;
	}
	static boolean canGo(int x, int y, char[][] map) {
		return (0 <= x && x < map[0].length) && (0 <= y && y < map.length);
	}
	static void changeMap(char[][] map) {
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[0].length; x++) {
				if(map[y][x] == 'X') {
					checkChangedPoint(map, x, y);
				}
			}
		}
		
		for(List<Integer> point : changeSet) {
			int x = point.get(0);
			int y = point.get(1);
			map[y][x] = '.';
		}
	}
	static void checkChangedPoint(char[][] map, int x, int y) {
		int count = 0;
		for(int i = 0; i < 4; i++) {
			int nextX = x + direction.get(i).get(0);
			int nextY = y + direction.get(i).get(1);
			if(!canGo(nextX, nextY, map) || map[nextY][nextX] == '.') {
				count++;
			}
		}
		if(count >= 3) {
			changeSet.add(Arrays.asList(x, y));
		}
	}
}