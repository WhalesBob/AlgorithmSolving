import java.io.*;
import java.util.*;

public class Main {
	static long A;
	static long B;
	static int minDepth = -1;
	
	public static void recur(int depth, long a) {
		if(a >= B) {
			if(a == B && (minDepth == -1 || minDepth > depth)) {
				minDepth = depth;
			}
			return;
		}
		recur(depth + 1, a * 2);
		recur(depth + 1, a * 10 + 1);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		recur(1, A);
		System.out.println(minDepth);
	}
}