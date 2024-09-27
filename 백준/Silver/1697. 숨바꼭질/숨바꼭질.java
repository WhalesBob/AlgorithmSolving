import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int[] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        array = new int[100001];
        array[N] = 1;
        System.out.println(findMinimumApproach(N,K)-1);
    }
    static int findMinimumApproach(int start,int destination){

        if(destination < start){
            return start - destination + 1;
        }

        ArrayDeque<Integer> indexQueue = new ArrayDeque<>();
        indexQueue.addLast(start);
        while(array[destination] == 0 || indexQueue.isEmpty()){
            // 인덱스 넣고, 큐에 집어넣기.
            // 큐에서 하나씩 빼서 처리하기.
            int index = indexQueue.removeFirst();
            if(index * 2 < array.length && array[index * 2] == 0){
                array[index * 2] = array[index] + 1;
                indexQueue.addLast(index * 2);
            }

            if(index + 1 < array.length && array[index + 1] == 0){
                array[index + 1] = array[index] + 1;
                indexQueue.addLast(index + 1);
            }

            if(index - 1 >= 0 && array[index - 1] == 0){
                array[index - 1] = array[index] + 1;
                indexQueue.addLast(index - 1);
            }
        }
        return array[destination];
    }
}