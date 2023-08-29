import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] array = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		int count = 0;
		Arrays.sort(array);
		for(int i = 0; i < array.length; i++) {
			int left = 0, right = array.length - 1;
			while(left < right) {
				int value = array[left] + array[right];
				if(value > array[i]) {
					right--;
				}else if(value < array[i]) {
					left++;
				}else {
					if(left != i && right != i) {
						count++;
						break;
					}else if(left == i){
						left++;
					}else {
						right--;
					}
				}
			}
		}
		System.out.println(count);
	}
}