import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] inputList = makeList(br, n);
		long[] sumArray = makeSumArray(inputList);
		
		for(int i = 0; i < m; i++) {
			long sum = 0;
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			long answer = sumArray[end] - sumArray[start-1];
			
			bw.write(answer + "\n");
		}
		bw.flush();
	}
	static int[] makeList(BufferedReader br, int n) throws IOException{
		int[] array  = new int[n];
		String[] input = br.readLine().trim().split(" ");
		for(int i = 0; i < n; i++) {
			array[i] = Integer.parseInt(input[i]);
		}
		return array;
	}
	static long[] makeSumArray(int[] array) {
		long[] sumArray = new long[array.length + 1];
		sumArray[0] = 0;
		for(int i = 1; i < sumArray.length; i++) {
			sumArray[i] = sumArray[i-1] + array[i-1];
		}
		return sumArray;
	}
}