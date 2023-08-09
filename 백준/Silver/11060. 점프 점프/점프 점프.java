import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 999_999_999;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] array = makeArray(br, n);
        int[] countArray = new int[n];
        Arrays.fill(countArray, INF);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        countArray[0] = 0;

        while(!queue.isEmpty()){
            int curIdx = queue.remove();

            for(int index = curIdx; index < countArray.length && index <= curIdx + array[curIdx]; index++) {
                if (countArray[index] > countArray[curIdx] + 1) {
                    countArray[index] = countArray[curIdx] + 1;
                    queue.add(index);
                }
            }
        }
        System.out.println(countArray[countArray.length - 1] != INF ? countArray[countArray.length - 1] : -1);
    }
    static int[] makeArray(BufferedReader br, int size) throws IOException{
        int[] array = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        for(int i = 0; i < size; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
        return array;
    }
}