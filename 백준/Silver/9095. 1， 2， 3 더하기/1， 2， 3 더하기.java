import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());

            int[] dp = new int[n + 1];
            for(int i = 1; i <= 3 && i < dp.length; i++){
                dp[i] = 1;
            }

            for (int num = 2; num <= n; num++) {
                for(int plus = 1; plus <= 3 && num - plus >= 1; plus++){
                    dp[num] += dp[num-plus];
                }
            }
            System.out.println(dp[n]);
        }
    }
}