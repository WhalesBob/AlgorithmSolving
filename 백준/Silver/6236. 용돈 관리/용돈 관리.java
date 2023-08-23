import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int sum;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		sum = 0;
		
		int[] array = makeArray(br, n);
		int left = 1, right = sum, answer = left;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			if(isAvailable(array, mid, m)) {
				answer = mid;
				right = mid - 1;
			}else {
				left = mid + 1;
			}
		}
		System.out.println(answer);
	}
	static int[] makeArray(BufferedReader br, int size) throws IOException{
		int[] array = new int[size];
		for(int i = 0; i < size; i++) {
			array[i] = Integer.parseInt(br.readLine());
			sum += array[i];
		}
		return array;
	}
	static boolean isAvailable(int[] array, int takeMoney, int m) {
		int leftMoney = takeMoney;
		int count = 1;
		for(int i = 0; i < array.length; i++) {
			if(array[i] > takeMoney) {
				return false;
			}
			if(array[i] <= leftMoney) {
				leftMoney -= array[i];
			}else {
				leftMoney = takeMoney;
				count++;
				leftMoney -= array[i];
			}
		}
		return count <= m;
	}
}