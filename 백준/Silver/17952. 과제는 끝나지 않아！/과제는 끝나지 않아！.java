import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); 
		int totalScore = 0;
		
		Stack<Work> stack = new Stack<>(); 
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int have = Integer.parseInt(st.nextToken());
			if(have != 0) { 
				int score = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				stack.add(new Work(score, time));
			}
			
			if(!stack.isEmpty()) { 
				stack.peek().time--;
				if(stack.peek().time == 0) { 
					Work pop = stack.pop();
					totalScore += pop.score;
				}
			}
		}
		
		System.out.println(totalScore);
	}
	
	static class Work{
		int score;
		int time;
		
		public Work(int score, int time) {
			this.score = score;
			this.time = time;
		}
	}
}