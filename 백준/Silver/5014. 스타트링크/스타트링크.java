import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_VALUE = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int totalHeight = Integer.parseInt(st.nextToken());
        int current = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[] countArray = new int[totalHeight + 1];
        Arrays.fill(countArray, MAX_VALUE);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(current);
        countArray[current] = 0;

        while(!queue.isEmpty()){
            int c = queue.remove();
            if(c + u < countArray.length && countArray[c] + 1 < countArray[c + u]){
                countArray[c + u] = countArray[c] + 1;
                queue.add(c + u);
            }
            if(c - d >= 1 && countArray[c] + 1 < countArray[c - d]){
                countArray[c - d] = countArray[c] + 1;
                queue.add(c - d);
            }
        }

        if(countArray[destination] == MAX_VALUE){
            System.out.println("use the stairs");
        }else{
            System.out.println(countArray[destination]);
        }
    }
}