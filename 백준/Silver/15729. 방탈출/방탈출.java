import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] originalArray = new int[n];
        int[] toMakeArray = makeArray(br, n);

        int count = 0;

        for(int i = 0; i < originalArray.length; i++){
            if(originalArray[i] != toMakeArray[i]){
                changeArray(originalArray, i);
                count++;
            }
        }

        System.out.println(count);
    }
    static void changeArray(int[] array, int start){
        for(int i = start; i < start + 3 && i < array.length; i++){
            array[i] = (array[i] + 1) % 2;
        }
    }
    static int[] makeArray(BufferedReader br, int size) throws IOException{
        int[] array = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < array.length; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
        return array;
    }
}