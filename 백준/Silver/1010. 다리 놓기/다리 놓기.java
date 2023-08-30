
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int s = sc.nextInt()+1;
			int e = sc.nextInt()+1;
//			System.out.println(s+" "+e);
			int[][] arr = new int[s][e];
			for(int i=0;i<e;i++) {
				arr[1][i]=i;
			}
//			for(int i=0;i<s;i++) {
//				for(int j=0;j<e;j++) {
//					System.out.print(arr[i][j]+ " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			for (int i = 2; i < s; i++) {
				for (int j = 1; j < e; j++) {
//					System.out.println(i+" "+j);
					if (i == j)
						arr[i][j] = 1;
					else if(i<j) {
						arr[i][j]=arr[i-1][j-1]+arr[i][j-1];
					}
				}
			}
//			for(int i=0;i<s;i++) {
//				for(int j=0;j<e;j++) {
//					System.out.print(arr[i][j]+ " ");
//				}
//				System.out.println();
//			}
			System.out.println(arr[s-1][e-1]);
		}
	}

}
