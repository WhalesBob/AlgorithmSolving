import java.io.*;
import java.util.*;

public class Main {
    static final long SEPARATOR = (long)Math.pow(10,10);
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int classCount = Integer.parseInt(br.readLine());

        PriorityQueue<Long> inputQueue = new PriorityQueue<>();
        PriorityQueue<Integer> classQueue = new PriorityQueue<>();

        for(int i = 0; i < classCount; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            inputQueue.add(getKey(start, end));
        }

        while(!inputQueue.isEmpty()){
            inputClass(classQueue, inputQueue.poll());
        }
        System.out.println(classQueue.size());
    }
    static long getKey(int start, int end){
        return (long)(Math.pow(10, 10) * start + end);
    }
    static void inputClass(PriorityQueue<Integer> classQueue, long element){
        int start = (int)(element / SEPARATOR);
        int end = (int)(element % SEPARATOR);

        if(!classQueue.isEmpty() && classQueue.peek() <= start){
            classQueue.poll();
        }
        classQueue.add(end);
    }
}