import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        int[] array = new int[size + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= size; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[size + 1];
        dp[1] = array[1];

        for(int i = 2; i < dp.length; i++){
            for(int j = 1; j <= i && j <= size; j++){
                dp[i] = Math.max(dp[i], dp[i - j] + array[j]);
            }
        }

        System.out.println(dp[size]);
    }
}