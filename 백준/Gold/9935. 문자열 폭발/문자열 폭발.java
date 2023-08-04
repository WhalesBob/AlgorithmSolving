import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String inputString = sc.next();
		String compareString = sc.next();
		
		String result = getLastString(inputString, compareString);
		System.out.println(result.length() == 0 ? "FRULA" : result);
	}
	static String getLastString(String input, String compare) {		
		CustomStack<Character> inputStack = new CustomStack<>();
		CustomStack<Character> compareStack = new CustomStack<>();
		
		for(char c : compare.toCharArray()) {
			compareStack.add(c);
		}
		
		for(char c : input.toCharArray()) {
			inputStack.add(c);
			if(CustomStack.containsInStack(inputStack, compareStack)) {
				for(int i = 0; i < compareStack.size() && !inputStack.isEmpty(); i++) {
					inputStack.pop();
				}
			}
		}
		
		return inputStack.getAllandMakeString();
	}
}
class CustomStack<E> extends Stack<E>{
	
	Character getElementUsingIndex(int index){
		if(this.elementData[index] instanceof Character) {
			return (char)this.elementData[index];
		}
		return null;
	}
	static boolean containsInStack(CustomStack stack, CustomStack compare) {
		
		if(stack.size() < compare.size()) {
			return false;
		}
		
		for(int i = 0; i < compare.size() && i < stack.size(); i++) {
			int compareIndex = compare.size() - i - 1;
			int stackIndex = stack.size() - i - 1;
			if(!stack.getElementUsingIndex(stackIndex).equals(compare.getElementUsingIndex(compareIndex))) {
				return false;
			}
		}
		
		return true;
	}
	
	String getAllandMakeString() {
		StringBuilder builder = new StringBuilder();
		while(!this.isEmpty()) {
			builder.append(this.pop());
		}
		return builder.reverse().toString();
	}
}