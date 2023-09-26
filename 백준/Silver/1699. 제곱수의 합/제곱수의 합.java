import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(solution(n));
	}
	static int solution(int n) {
		int[] array = new int[n + 1];
		array[1] = 1;
		
		for(int i = 2; i <= n; i++) {
			int min = i;
			for(int j = 1; j <= Math.sqrt(i); j++) {
				int value = array[i - j * j] + 1;
				if(value < min) {
					min = value;
				}
			}
			array[i] = min;
		}
		return array[n];
	}
}