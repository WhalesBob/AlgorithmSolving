import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int maxUpdatedLength;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[] array = makeArray(br, n);

        Node[] resultArray = updateLargestSubArray(array);
        bw.write((maxUpdatedLength + 1) + "\n");
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for(Node node = resultArray[maxUpdatedLength]; node != null; node = node.previous){
            deque.addFirst(node.data);
        }

        for(Integer element : deque){
            bw.write(element + " ");
        }

        bw.flush();
    }
    static Node[] updateLargestSubArray(int[] array) {

        Node[] smallValueArray = new Node[array.length];
        smallValueArray[0] = new Node(array[0], null);

        for(int i = 1; i < array.length; i++) {
            int targetIndex = getBinarySearch(smallValueArray, array[i]);

            if(targetIndex == -1) {
                continue;
            }
            smallValueArray[targetIndex] = new Node(array[i], targetIndex == 0 ? null : smallValueArray[targetIndex - 1]);

        }
        return smallValueArray;
    }
    static int[] makeArray(BufferedReader br, int size) throws IOException{
        int[] array = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        for(int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        return array;
    }
    static int getBinarySearch(Node[] smallValueArray, int value) {
        int left = 0, right = maxUpdatedLength;
        if(smallValueArray[right].data < value) {
            maxUpdatedLength++;
            return right + 1;
        }

        int answer = right;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(isAvailable(smallValueArray, mid, value)) {
                answer = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return smallValueArray[answer].data == value ? -1 : answer;
    }
    static boolean isAvailable(Node[] array, int targetIndex, int value) {
        return array[targetIndex].data >= value;
    }
    static class Node{
         int data;
         Node previous;

        public Node(int data, Node previous) {
            this.data = data;
            this.previous = previous;
        }
    }
}