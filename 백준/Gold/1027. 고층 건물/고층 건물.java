import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] array = makeArray(br,n);
        max = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++){
            int count = 0;
            count += countLeft(array, i);
            count += countRight(array, i);

            if(count > max) {
                max = count;
            }
        }
        System.out.println(max);
    }
    static int countLeft(int[] array, int index){
        int count = 0;
        double maxGradient = Integer.MIN_VALUE;
        for(int i = index - 1; i >= 0; i--){
            double gradient = getGradient(index - i, array[index], array[i]);
            if(gradient > maxGradient){
                maxGradient = gradient;
                count++;
            }
        }

        return count;
    }
    static int countRight(int[] array, int index){
        int count = 0;
        double maxGradient = Integer.MIN_VALUE;
        for(int i = index + 1; i < array.length; i++){
            double gradient = getGradient(i - index, array[index], array[i]);
            if(gradient > maxGradient){
                maxGradient = gradient;
                count++;
            }
        }
        return count;
    }
    static double getGradient(int dx, int indexY, int seeY){
        return ((double)(seeY - indexY) / (double)dx);
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