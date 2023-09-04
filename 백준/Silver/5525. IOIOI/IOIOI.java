import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int length = Integer.parseInt(br.readLine());
        char[] input = br.readLine().trim().toCharArray();

        int index = 2 * n + 1;
        CustomStack<Character> stack = new CustomStack<>();

        Character[] compare = new Character[index];
        compare[0] = 'I';

        for(int i = 1; i < index; i+=2){
            compare[i] = 'O';
            compare[i+1] = 'I';
        }

        int count = 0;

        for(int i = 0; i < index; i++){
            stack.add(input[i]);
        }

        while(index < input.length){
            if(stack.haveInStack(compare)){
                count++;
                stack.add(input[index++]);
            }

            if(index < input.length){
                stack.add(input[index++]);
            }
        }

        if(stack.haveInStack(compare)){
            count++;
        }

        System.out.println(count);
    }
    static class CustomStack<T> extends Stack<T> {
        Character getElementInStack(int index){
            Object get = elementData[this.size() - index - 1];
            if(get instanceof Character){
                return (Character)get;
            }
            return null;
        }
        public boolean haveInStack(Character[] compare){
            for(int i = 0; i < compare.length; i++){
                if(!compare[i].equals(getElementInStack(i))){
                    return false;
                }
            }
            return true;
        }
    }
}