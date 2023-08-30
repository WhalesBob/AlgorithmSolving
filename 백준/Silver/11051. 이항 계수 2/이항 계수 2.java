
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[][] arr = new int[N + 1][N + 1];
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < i + 1; j++) {
				if (j == 0 || i == j) {
					arr[i][j] = 1;
				} else {
					arr[i][j] = (arr[i - 1][j - 1]%10007 + arr[i - 1][j]%10007)%10007;
				}
			}
		}
//		for(int i=0;i<N+1;i++) {
//			for(int j=0;j<N+1;j++) {
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
		System.out.println(arr[N][K] % 10007);
	}
}
