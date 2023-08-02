import java.io.*;
import java.util.Scanner;

public class Main {
	static BufferedWriter bw;
	public static void main(String[] args) throws IOException{
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = sc.nextInt();
		int[] arr = new int[n];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}
		combination(arr, new boolean[n], 0, n, r);
		bw.flush();
	}
	
	static void combination(int[] arr, boolean[] visited, int start, int n, int r) throws IOException{
		if(r == 0) {
			printArr(arr, visited);
			return;
		}
		
		for(int i = start; i < n; i++) {
			visited[i] = true;
			combination(arr, visited, i + 1, n, r-1);
			visited[i] = false;
		}
	}
	static void printArr(int[] arr, boolean[] visited) throws IOException {
		for(int i = 0; i < arr.length; i++) {
			if(visited[i]) {
				bw.write(arr[i] + " ");
			}
		}
		bw.write("\n");
	}
}