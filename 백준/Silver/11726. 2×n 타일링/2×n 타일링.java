import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] array = new int[n];
		if(n <= 2) {
			System.out.println(n);
			return;
		}
		array[0] = 1;
		array[1] = 2;
		
		for(int i = 2; i < n; i++) {
			array[i] = (array[i-1] + array[i-2]) % 10007;
		}
		System.out.println(array[n-1]);
	}
}