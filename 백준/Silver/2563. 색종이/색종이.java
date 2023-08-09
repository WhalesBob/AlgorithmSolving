import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Set<Point> set = new HashSet<>();
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			addSet(x, y, set);
		}
		System.out.println(set.size());
	}
	
	static void addSet(int x, int y, Set<Point> set) {
		for(int dy = 0; dy < 10; dy++) {
			for(int dx = 0; dx < 10; dx++) {
				set.add(new Point(x + dx, y + dy));
			}
		}
	}
	static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			if(obj instanceof Point) {
				Point compare = (Point)obj;
				return this.x == compare.x && this.y == compare.y;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return (1000 * x ) + y;
		}
	}
}