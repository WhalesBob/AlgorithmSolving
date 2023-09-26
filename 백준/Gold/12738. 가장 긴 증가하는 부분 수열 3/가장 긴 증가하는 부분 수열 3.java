import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int maxUpdatedLength;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] array = makeArray(br, n);
		maxUpdatedLength = 0;
		
		updateLargestSubArray(array);
		System.out.println(maxUpdatedLength + 1);
	}
	static void updateLargestSubArray(int[] array) {
		int[] smallValueArray = new int[array.length];
		smallValueArray[0] = array[0];
		
		for(int i = 1; i < array.length; i++) {
			int targetIndex = getBinarySearch(smallValueArray, array[i]);
			
			if(targetIndex == -1) {
				continue;
			}
			smallValueArray[targetIndex] = array[i];
		}
		
	}
	static int[] makeArray(BufferedReader br, int size) throws IOException{
		int[] array = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		for(int i = 0; i < size; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		return array;
	}
	static int getBinarySearch(int[] smallValueArray, int value) {
		int left = 0, right = maxUpdatedLength;
		if(smallValueArray[right] < value) {
			maxUpdatedLength ++;
			return right + 1;
		}
		
		int answer = right;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			if(isAvailable(smallValueArray, mid, value)) {
				answer = mid;
				right = mid - 1;
			}else {
				left = mid + 1;
			}
		}
		return smallValueArray[answer] == value ? -1 : answer;
	}
	static boolean isAvailable(int[] array, int targetIndex, int value) {
		return array[targetIndex] >= value;
	}
}