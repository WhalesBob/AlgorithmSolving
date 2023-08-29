import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] dp = new int[n+1];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		
		while(!queue.isEmpty() && dp[n] == 0) {
			int element = queue.remove();
			if(element + 1 <= n && (dp[element + 1] > element + 1 || dp[element + 1] == 0)) {
				dp[element + 1] = dp[element] + 1;
				queue.add(element + 1);
			}
			if(element * 2 <= n && (dp[element * 2] > element + 1 || dp[element * 2] == 0)) {
				dp[element * 2] = dp[element] + 1;
				queue.add(element * 2);
			} 
			if(element * 3 <= n && (dp[element * 3] > element + 1 || dp[element * 3] == 0)) {
				dp[element * 3] = dp[element] + 1;
				queue.add(element * 3);
			}
		}
		System.out.println(dp[n]);
	}
}
