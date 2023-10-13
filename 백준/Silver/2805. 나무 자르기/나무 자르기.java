import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] array = makeArray(br, n);
		System.out.println(getMaximumHeight(array, m));
	}
	static int getMaximumHeight(int[] array, int needLength) {
		int left = 0, right = getMax(array);
		int answer = 0;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			if(isAvailable(array, mid, needLength)) {
				answer = mid;
				left = mid + 1;
			}else {
				right = mid - 1;
			}
		}
		return answer;
	}
	static boolean isAvailable(int[] array, int targetHeight, int need) {
		long sum = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] > targetHeight) {
				sum += (array[i] - targetHeight);
			}
		}
		
		return sum >= need;
	}
	static int getMax(int[] array) {
		int max = -1;
		for(int i = 0; i < array.length; i++) {
			if(max < array[i]) {
				max = array[i];
			}
		}
		return max;
	}
	static int[] makeArray(BufferedReader br, int size) throws IOException{
		int[] array = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i < size; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		return array;
	}
}