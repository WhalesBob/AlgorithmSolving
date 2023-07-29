import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        char[] numberArray = br.readLine().trim().toCharArray();
        System.out.println(solution(numberArray, k));
    }
    static StringBuilder solution(char[] numberArray, int k){
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int index = 0;
        for(; index < numberArray.length; index++){
            int value = numberArray[index] - '0';
            while(!deque.isEmpty() && deque.peekLast() < value && k > 0){
                deque.pollLast();
                k--;
            }
            deque.addLast(value);
        }

        for(int i = 0; i < k; i++){
            deque.pollLast();
        }

        StringBuilder answer = new StringBuilder();
        while(!deque.isEmpty()){
            answer.append(deque.pollFirst());
        }
        return answer;
    }
}