import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[] array = makeArray(br, n);
		
		System.out.println(getMaxLength(array, m));
	}
	static int getMaxLength(int[] array, int cousinCount) {
		int left = 0, right = array[array.length - 1];
		int answer = 0;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			if(isAvailable(array, mid, cousinCount)) {
				answer = mid;
				left = mid + 1;
			}else {
				right = mid - 1;
			}
		}
		
		return answer;
	}
	static boolean isAvailable(int[] snacks, int length, int cousinCount) {
		int count = 0;
		int snackIndex = snacks.length - 1;
		int[] copy = snacks.clone();
		
		while(count < cousinCount && snackIndex >= 0) {
			if(copy[snackIndex] >= length) {
				count++;
				copy[snackIndex] -= length;
			}else {
				snackIndex--;
			}
		}
		
		return cousinCount <= count;
	}
	static int[] makeArray(BufferedReader br, int size) throws IOException{
		int[] array = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i < size; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
        Arrays.sort(array);
		return array;
	}
}