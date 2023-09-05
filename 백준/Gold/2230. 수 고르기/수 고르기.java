import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(array);

        int left = 0, right = 0, min = Integer.MAX_VALUE;
        while(right < array.length && left <= right){
            if(left == right){
                right++;
                continue;
            }

            int value = array[right] - array[left];
            if(value >= m){
                if(min > value){
                    min = value;
                }
                left++;
            }else{
                right++;
            }
        }

        while(left < right - 1 && array[right-1] - array[left] >= m){
            int value = array[right-1] - array[left];
            if(min > value){
                min = value;
            }
            left++;
        }
        System.out.println(min);
    }
}