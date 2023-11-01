import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());

        PriorityQueue<Line> queue = new PriorityQueue<>();
        for(int i = 0; i < count; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            queue.add(new Line(start, end));
        }

        Stack<Line> stack = new Stack<>();

        while(!queue.isEmpty()){
            putInStack(stack, queue.poll());
        }

        long sum = 0;

        while(!stack.isEmpty()){
            Line element = stack.pop();
            sum += element.getLineLength();
        }

        System.out.println(sum);
    }
    static void putInStack(Stack<Line> stack, Line element){
        if(stack.isEmpty() || stack.peek().end < element.start){
            stack.push(element);
            return;
        }

        Line putStack = stack.pop();
        putStack.end = Math.max(element.end, putStack.end);
        stack.push(putStack);
    }
    static class Line implements Comparable<Line>{
        long start;
        long end;

        public Line(long start, long end) {
            this.start = start;
            this.end = end;
        }

        public long getLineLength(){
            return (this.end - this.start);
        }

        @Override
        public int compareTo(Line o) {
            if(this.start == o.start){
                return Long.compare(this.end, o.end);
            }
            return Long.compare(this.start, o.start);
        }
    }
}