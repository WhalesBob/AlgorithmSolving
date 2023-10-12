import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static final int mod = 1_000_000_007;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long[] fact = makeFactorialRemainderArray(4_000_000, mod);
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		long answer = mul(mul(fact[n], power(fact[n-r], mod - 2)), power(fact[r], mod - 2));
		System.out.println(answer);
	}
	static long mul(long a, long b) {
		return (a * b) % mod;
	}
	static long power(long n, int r) {
		if(r == 0) return 1;
		
		long a = power(n, r / 2);
		long b = (a * a) % mod;
		return (r % 2 == 0 ? b : (b * n) % mod);
	}
	static long[] makeFactorialRemainderArray(int max, int quotient) {
		long[] array = new long[max + 1];
		array[0] = 1;
		
		for(int i = 1; i <= max; i++) {
			array[i] = (array[i-1] * i) % quotient;
		}
		return array;
	}
}