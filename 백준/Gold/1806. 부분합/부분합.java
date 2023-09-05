import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] array = makeArray(br, n);

        int left = 0, right = 0, sum = 0, min = Integer.MAX_VALUE;
        while(right < array.length && left <= right){
            if(sum < s || right == left){
                sum += array[right++];
            }else{
                if(min > right - left){
                    min = right - left;
                }
                sum -= array[left++];
            }
        }

        while(left <= right && sum >= s){
            if(min > right - left){
                min = right - left;
            }
            sum -= array[left++];
        }
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
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