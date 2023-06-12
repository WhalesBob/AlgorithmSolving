import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        NumberPair[] pair = new NumberPair[size];
        for(int i = 0; i < size; i++){
            pair[i] = new NumberPair(Integer.parseInt(st.nextToken()),-1);
        }

        Stack<NumberPair> stack = new Stack<>();

        stack.push(pair[0]);
        for(int i = 1; i < size; i++){
            while(!stack.isEmpty() && stack.peek().number < pair[i].number){
                NumberPair outPair = stack.pop();
                outPair.nge = pair[i].number;
            }
            stack.push(pair[i]);
        }
        printAllNGE(pair);
    }
    static void printAllNGE(NumberPair[] pairs){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < pairs.length; i++){
            builder.append(pairs[i].nge);
            if(i < pairs.length -1){
                builder.append(" ");
            }
        }
        System.out.println(builder);
    }
    static class NumberPair{
        int number;
        int nge;

        public NumberPair(int number, int nge){
            this.number = number;
            this.nge = nge;
        }
    }
}