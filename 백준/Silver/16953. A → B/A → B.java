import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		if(a == b) {
			System.out.println(0);
		}else {
			System.out.println(bfs(a, b));
		}
	}

	static long bfs(long a, long b) {
		Queue<Long> queue = new LinkedList<>();
		queue.add(a);
		int count = 1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				long element = queue.remove();
				if(element * 10 + 1 == b || element * 2 == b) {
					return count + 1;
				}
				if(element * 10 + 1 < b) {
					queue.add(element * 10 + 1);
				}
				if(element * 2 < b) {
					queue.add(element * 2);
				}
			}
			count++;
		}
		return -1;
	}
}