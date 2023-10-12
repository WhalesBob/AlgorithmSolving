import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		long [] dp = new long[N + 5];

		dp[1] = 1;
		dp[2] = 3;
		
		for (int i = 3; i < N + 1; i++) {
			if(i % 2 == 0) {
				dp[i] = (dp[i-1] * 2 + 1) % 10007;
			}
			if(i % 2 == 1) {
				dp[i] = (dp[i-1] * 2 - 1) % 10007;
			}
		}
		
		System.out.println(dp[N]);
	}

}
