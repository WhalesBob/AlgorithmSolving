import java.io.*;
import java.util.*;

public class Main {
    static int count;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        count = 0;

        Map<Integer, Stack<Integer>> soundMap = new HashMap<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int prat = Integer.parseInt(st.nextToken());
            int sound = Integer.parseInt(st.nextToken());

            if(!soundMap.containsKey(prat)) {
                soundMap.put(prat, new Stack<>());
            }

            Stack<Integer> stack = soundMap.get(prat);
            modifyMap(stack, sound);

            if(!stack.isEmpty() && stack.peek() == sound){
                continue;
            }

            count++;
            stack.push(sound);
        }

        System.out.println(count);
    }
    static void modifyMap(Stack<Integer> stack, int sound){
        while(!stack.isEmpty() && stack.peek() > sound){
            count++;
            stack.pop();
        }
    }
}