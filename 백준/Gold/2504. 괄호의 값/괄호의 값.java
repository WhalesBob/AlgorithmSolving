import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		
		int length = temp.length();
		int result = 0;
		int val = 1;
		for(int i = 0; i < length; i++) {
			
			if(temp.charAt(i) == '(') {
				
				if(i == length - 1) {
					result = 0;
					break;
				}
				
				val *= 2;
				stack.push('(');
			}
			
			else if(temp.charAt(i) == ')') {
				
				if(stack.isEmpty() || stack.peek() != '(') {
					result = 0;
					break;
				}
				
				else if(temp.charAt(i-1) == '(') {
					result += val;
				}
				stack.pop();
				val /= 2;
				
				
			}
			
			else if(temp.charAt(i) =='[') {
				
				if(i == length - 1) {
					result = 0;
					break;
				}
				
				val *= 3;
				stack.push('[');
			}
			
			else if(temp.charAt(i) == ']') {
				
				if(stack.isEmpty() || stack.peek() != '[') {
					result = 0;
					break;
				}
				
				else if(temp.charAt(i-1) == '[') {
					result += val;
				}
				
				stack.pop();
				val /= 3;
			}
			
		}
		
		if(!stack.isEmpty()) {
			System.out.println(0);
		}
		else {
			System.out.println(result);
		}
		
	}

}
