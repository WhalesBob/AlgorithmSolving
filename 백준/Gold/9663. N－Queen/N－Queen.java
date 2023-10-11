import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int result;
	static int full;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		full = (1 << N) - 1;
		
		dfs(0, 0, 0, 0);

		System.out.println(result);

	}

	static void dfs(int left, int center, int right, int cnt) {

		if (cnt == N) {
			result++;
			return;
		}
		
		left <<= 1;
		right >>= 1;
		
		int board = (left | center | right);
		
		if((board & full) == full) {
			return;
		}
		
		for (int c = 0; c < N; c++) {
			int bit = 1 << c;
			
			if(!((board & bit) != 0)) {
				dfs(left | bit, center | bit, right | bit, cnt+1);
			}
		}

	}

}
