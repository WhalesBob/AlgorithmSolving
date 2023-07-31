import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		
		int[] array = makeArray(br, count);
		
		int studentCount = Integer.parseInt(br.readLine());
		for(int i = 0; i < studentCount; i++) {
			String[] input = br.readLine().trim().split(" ");
			int sex = Integer.parseInt(input[0]);
			int num = Integer.parseInt(input[1]);
			
			changeNumber(array, sex, num);
		}
		
		for(int i = 1; i < array.length; i++) {
			System.out.print(array[i]);
			if(i % 20 == 0) {
				System.out.println();
			}else if(i < array.length - 1) {
				System.out.print(" ");
			}
		}
	}
	static int[] makeArray(BufferedReader br, int size) throws IOException{
		int[] array = new int[size + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for(int i = 1; i <= size; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		return array;
	}
	static void changeNumber(int[] array, int sex, int index) {
		if(sex == 1) {
			for(int i = index; i < array.length; i+=index) {
				array[i] = (array[i] == 1) ? 0 : 1;
			}
		}
		
		if(sex == 2) {
			int count = getCount(array, index);
			for(int i = index - count; i <= index + count; i++) {
				array[i] = (array[i] == 1) ? 0 : 1;
			}
		}
	}
	static int getCount(int[] array, int index) {
		int count = 0;
		for(count = 1; index - count > 0 && index + count < array.length ; count++) {
			if(array[index - count] != array[index + count]) {
				break;
			}
		}
		return count == 0 ? count : count -1;
	}
}