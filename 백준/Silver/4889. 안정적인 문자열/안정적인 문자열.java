import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseCount = 0;
        while(true){
            String input = br.readLine();
            if(input.contains("-")){
                break;
            }

            caseCount++;
            System.out.printf("%d. %d\n", caseCount, getStableString(input));
        }
    }

    private static int getStableString(String input) {
        Stack<Character> stack = new Stack<>();
        for(char c : input.toCharArray()){
            boolean needPush = (c == '{') || (stack.isEmpty() || stack.peek() == '}');
            if(needPush){
                stack.push(c);
            }else{
                stack.pop();
            }
        }

        int count = 0;
        while(!stack.isEmpty()){
            char top = stack.pop();

            if(top == '{' && stack.peek() == '}'){
                count += 2;
            }else if((top == '{' && stack.peek() == '{') || (top == '}' && stack.peek() == '}')){
                count ++;
            }

            stack.pop();
        }

        return count;
    }
}