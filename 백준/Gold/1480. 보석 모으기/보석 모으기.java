import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int[][][] dp;
	static int jewelCount, bagCount, bagWeight;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		jewelCount = Integer.parseInt(st.nextToken());
		bagCount = Integer.parseInt(st.nextToken());
		bagWeight = Integer.parseInt(st.nextToken());
		
		int[] array = makeArray(br, jewelCount);
		
		dp = new int[1 << jewelCount][11][21];
		System.out.println(dfs(array, 0, 0, bagWeight));
	}
	static int[] makeArray(BufferedReader br, int size) throws IOException{
		int[] array = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i < size; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		return array;
	}
	static int dfs(int[] array, int visit, int index, int leftWeight) {
		if(visit == (1 << jewelCount) - 1 || index == bagCount) {
			return 0;
		}
		
		if(dp[visit][index][leftWeight] != 0) {
			return dp[visit][index][leftWeight];
		}
		
		for(int i = 0; i < jewelCount; i++) {
			if((visit & (1 << i)) > 0) {
				continue;
			}
			
			if(leftWeight >= array[i]) {
				dp[visit][index][leftWeight] = Math.max(dp[visit][index][leftWeight], dfs(array, visit | (1 << i), index, leftWeight - array[i]) + 1);
			}else {
				dp[visit][index][leftWeight] = Math.max(dp[visit][index][leftWeight], dfs(array, visit, index + 1, bagWeight));
			}
		}
		return dp[visit][index][leftWeight];
	}
}