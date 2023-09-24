import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] array = makeArray(br, n);
		max = Integer.MIN_VALUE;
		
		makePermutation(array, 0, array.length, array.length);
		System.out.println(max);
	}
	static void makePermutation(int[] array, int depth, int n, int r) {
		if(depth == r) {
			int value = takeDiff(array);
			if(max < value) {
				max = value;
			}
			
			return;
		}
		
		for(int i = depth; i < n; i++) {
			swap(array, depth, i);
			makePermutation(array, depth + 1, n, r);
			swap(array, depth, i);
		}
	}
	static int takeDiff(int[] array) {
		int sum = 0;
		for(int i = 0; i < array.length-1; i++) {
			sum += Math.abs(array[i] - array[i+1]);
		}
		return sum;
	}
	static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	} 
	static int[] makeArray(BufferedReader br, int size) throws IOException {
		int[] array = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i < size; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		return array;
	}
}