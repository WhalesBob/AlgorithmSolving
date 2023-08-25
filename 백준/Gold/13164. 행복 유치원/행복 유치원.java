import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] array = makeArray(br, n);
		List<Integer> diffList = makeDiff(array);
		int sum = 0;
		
		for(int i = 0; i < diffList.size() - (k - 1); i++) {
			sum += diffList.get(i);
		}
		
		System.out.println(sum);
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
	static List<Integer> makeDiff(int[] array){
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < array.length - 1; i++) {
			list.add(array[i+1] - array[i]);
		}
		Collections.sort(list);
		return list;
	}
}