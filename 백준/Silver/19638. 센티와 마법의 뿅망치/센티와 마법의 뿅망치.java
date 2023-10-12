import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int population = Integer.parseInt(st.nextToken());
		int centiHeight = Integer.parseInt(st.nextToken());
		int limit = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> Integer.compare(b, a));
		
		for(int i = 0; i < population; i++) {
			queue.add(Integer.parseInt(br.readLine()));
		}
		
		for(int i = 0; i < limit; i++) {
			if(queue.peek() < centiHeight) {
				System.out.printf("YES\n%d", i);
				return;
			}
			
			int height = queue.poll();
			
			if(height == 1) {
				queue.add(1);
				break;
			}
			
			queue.add(height >> 1);
		}
		
		if(queue.peek() < centiHeight) {
			System.out.printf("YES\n%d", limit);
		}else {
			System.out.printf("NO\n%d", queue.peek());
		}
	}
}