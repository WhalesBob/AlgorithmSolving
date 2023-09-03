import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            int n = Integer.parseInt(br.readLine());
            int[] array = makeArray(br, n);

            long profit = 0;

            int currentMax = array[array.length - 1];

            for(int i = array.length - 2; i >= 0; i--){
                if(array[i] >= currentMax){
                    currentMax = array[i];
                }else{
                    profit += (currentMax - array[i]);
                }
            }

            bw.write(profit + "\n");
        }
        bw.flush();
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