import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char[] input = br.readLine().trim().toCharArray();
		
		int n = Integer.parseInt(br.readLine());
		
		Stack<Character> stack = new Stack<>();
		ArrayDeque<Character> queue = new ArrayDeque<>();
		
		for(char c : input) {
			queue.addLast(c);
		}
		
		for(int i = 0; i < n; i++) {
			String[] splitInput = br.readLine().trim().split(" ");
			String op = splitInput[0];
			
			switch(op) {
				case "L":
					if(!queue.isEmpty()) stack.add(queue.removeLast());
					break;
				case "D":
					if(!stack.isEmpty()) queue.addLast(stack.pop());
					break;
				case "B":
					if(!queue.isEmpty()) queue.removeLast();
					break;
				case "P":
					char toAdd = splitInput[1].charAt(0);
					queue.addLast(toAdd);
					break;
			}
		}
		
		while(!stack.isEmpty()) {
			queue.addLast(stack.pop());
		}
		
		while(!queue.isEmpty()) {
			bw.write(Character.toString(queue.removeFirst()));
		}
		
		bw.flush();
	}
}