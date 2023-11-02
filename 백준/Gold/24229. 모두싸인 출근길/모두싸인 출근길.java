import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Line> inputQueue = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            inputQueue.add(new Line(start, end));
        }

        ArrayDeque<Line> deque = new ArrayDeque<>();

        while(!inputQueue.isEmpty()){
            putInStack(deque, inputQueue.poll());
        }
        System.out.println(getPossiblePosition(deque));
    }
    static int getPossiblePosition(ArrayDeque<Line> deque){
        Stack<Line> stack = new Stack<>();
        stack.push(deque.removeFirst());
        int max = stack.peek().end;

        while(!deque.isEmpty() && !stack.isEmpty()){
            if(stack.peek().getLineLength() >= deque.peekFirst().start - stack.peek().end){
                stack.add(deque.removeFirst());
                if(max < stack.peek().end){
                    max = stack.peek().end;
                }
            }else{
                stack.pop();
            }
        }

        return max;
    }
    static void putInStack(ArrayDeque<Line> deque, Line element){
        if(deque.isEmpty() || deque.peekLast().end < element.start){
            deque.addLast(element);
            return;
        }

        deque.peekLast().end = Math.max(deque.peekLast().end, element.end);
    }
    static class Line implements Comparable<Line>{
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getLineLength(){
            return this.end - this.start;
        }

        @Override
        public int compareTo(Line o) {
            if(this.start == o.start){
                return Integer.compare(this.end, o.end);
            }
            return Integer.compare(this.start, o.start);
        }
    }
}