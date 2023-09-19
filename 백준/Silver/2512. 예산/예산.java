import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] array = makeArray(br, n);
		
		int totalBudget = Integer.parseInt(br.readLine());
		System.out.println(getMaxOneBudget(array, totalBudget));
	}
	static int getMaxOneBudget(int[] array, int totalBudget) {
		int left = 1, right = Arrays.stream(array).max().getAsInt();
		int answer = left;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			if(isAvailable(array, mid, totalBudget)) {
				answer = mid;
				left = mid + 1;
			}else {
				right = mid - 1;
			}
		}
		
		return answer;
	}
	static boolean isAvailable(int[] array, int budget, int totalBudget) {
		int sum = 0;
		for(int i = 0; i < array.length; i++) {
			sum += (array[i] >= budget) ? budget : array[i];
		}
		
		return sum <= totalBudget;
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