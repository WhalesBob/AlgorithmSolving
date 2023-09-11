import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long sum = 0;
        int max = -1;
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < n; i++){
            int value = Integer.parseInt(st.nextToken());
            sum += value;
            if(max < value){
                max = value;
            }
        }

        if(sum == 1){
            System.out.println("Happy");
            return;
        }

        System.out.println((sum / 2) >= max ? "Happy" : "Unhappy");
    }
}