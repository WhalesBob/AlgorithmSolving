import java.io.*;
import java.util.*;

public class Main {
	static long min;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		min = Integer.MAX_VALUE;
		Node[] list = makeList(br, n);
		for(int r = 1; r <= n; r++) {
			combination(list, new boolean[n], 0, n, r);
		}
		System.out.println(min);
		
	}
	static void combination(Node[] arr, boolean[] visited, int start, int n, int r) {
		if(r == 0) {
			long bit = 0, sour = 1;
			for(int i = 0; i < arr.length; i++) {
				if(visited[i]) {
					bit += arr[i].bitter;
					sour *= arr[i].sour;
				}
			}
			if(min > Math.abs(bit - sour)) {
				min = (long)Math.abs(bit - sour);
			}
			return;
		}
		
		for(int i = start; i < n; i++) {
			visited[i] = true;
			combination(arr, visited, i+1, n, r-1);
			visited[i] = false;
		}
	}
	static Node[] makeList(BufferedReader br, int size) throws IOException{
		Node[] list = new Node[size];
		for(int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int sour = Integer.parseInt(st.nextToken());
			int bitter = Integer.parseInt(st.nextToken());
			
			list[i] = new Node(sour, bitter);
		}
		return list;
	}
	static class Node{
		int sour;
		int bitter;
		
		public Node(int sour, int bitter) {
			this.sour = sour;
			this.bitter = bitter;
		}
	}
}