import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] matrix = makeMatrix(br,n);
        List<Queue<Integer>> queueList = makeQueueList(matrix);
        PriorityQueue<List<Integer>> priorityQueue = priorityQueueInit(queueList);
        int value = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            value = getNumberFromHeap(priorityQueue, queueList);
        }
        System.out.println(value);
    }
    static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
        int[][] matrix = new int[size][size];
        for(int i = 0; i < size; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j < size; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return matrix;
    }
    static List<Queue<Integer>> makeQueueList(int[][] matrix){
        List<Queue<Integer>> queueList = new ArrayList<>();
        for(int x = 0; x < matrix[0].length; x++){
            Queue<Integer> queue = new LinkedList<>();
            for(int y = matrix.length - 1; y >= 0; y--){
                queue.add(matrix[y][x]);
            }
            queueList.add(queue);
        }
        return queueList;
    }
    static PriorityQueue<List<Integer>> priorityQueueInit(List<Queue<Integer>> queueList){
        PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue<>((a,b) -> Integer.compare(b.get(1),a.get(1)));

        for(int i = 0; i < queueList.size(); i++){
            priorityQueue.add(Arrays.asList(i, queueList.get(i).remove()));
        }
        return priorityQueue;
    }
    static int getNumberFromHeap(PriorityQueue<List<Integer>> priorityQueue, List<Queue<Integer>> queueList){
        List<Integer> element = priorityQueue.remove();
        int removedIndex = element.get(0);
        if(queueList.get(removedIndex).size() > 0){
            Integer toAdd = queueList.get(removedIndex).remove();
            priorityQueue.add(Arrays.asList(removedIndex, toAdd));
        }
        return element.get(1);
    }
}