import java.io.*;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			int input = Integer.parseInt(br.readLine());
			bw.write(getResult(input) + "\n");
		}
		bw.flush();
	}
	static long getResult(int n) {
		long[][] dp = new long[n][10];
		if(n == 1) {
			return 10;
		}
		
		Arrays.fill(dp[0], 1);

		for(int y = 1; y < n; y++) {
			for(int x = 0; x < 10; x++) {
				for(int k = x; k < 10; k++) {
					dp[y][x] += dp[y-1][k];
				}
			}
		}
		return Arrays.stream(dp[n-1]).sum();
	}
}