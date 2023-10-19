import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int size = Integer.parseInt(st.nextToken());
        int want = Integer.parseInt(st.nextToken());

        int[] array = new int[size];
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < array.length; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getAnswer(array, want));
    }
    static int getAnswer(int[] array, int want){
        int left = 0, right = 0, min = Integer.MAX_VALUE;
        int ryan = array[0] == 1 ? 1 : 0;

        while(left <= right && right < array.length){
            if(ryan >= want){
                if((right - left + 1) < min){
                    min = right - left + 1;
                }

                if(array[left] == 1){
                    ryan--;
                }
                left++;
            }else {
                if (right + 1 < array.length && array[right + 1] == 1) {
                    ryan++;
                }
                right++;
            }
        }

        while(ryan >= want){
            if(left - right + 1 > min){
                min = left - right + 1;
            }

            if(array[left] == 1){
                ryan--;
            }
            left++;
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}