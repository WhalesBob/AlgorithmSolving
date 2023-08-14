import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			char[] charArray = br.readLine().trim().toCharArray();
			getAndPrintPassword(bw, charArray);
		}
		bw.flush();
	}
	static void getAndPrintPassword(BufferedWriter bw,char[] charArray) throws IOException{
		ArrayDeque<Character> front = new ArrayDeque<>();
		Stack<Character> stack = new Stack<>();
		
		for(char c : charArray) {
			switch(c) {
				case '>':
					if(!stack.isEmpty()) front.addLast(stack.pop());
					break;
				case '<':
					if(!front.isEmpty()) stack.push(front.removeLast());
					break;
				case '-':
					if(!front.isEmpty()) front.removeLast();
					break;
				default:
					front.addLast(c);
			}
		}
		
		while(!stack.isEmpty()) front.add(stack.pop());
		while(!front.isEmpty()) bw.write(Character.toString(front.removeFirst()));
		bw.write("\n");
	}
}