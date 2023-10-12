import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	
	static BigInteger[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		arr = new BigInteger[N+2];
		arr[0] = new BigInteger("0");
		arr[1] = new BigInteger("1");
		
		
		
		BigInteger result = null;
		// 짝수 일 때
		if(N == 0) {
			result = new BigInteger("0");
		}
		
		else if(N % 2 == 0) {
			result = (fibo(N/2).multiply(fibo(N/2))).add( (fibo(N/2).multiply(new BigInteger("2"))).multiply(fibo((N/2)-1)) );
		}
		
		// 홀수 일 때
		else if (N % 2 == 1){
			result = (fibo((N-1) / 2 + 1).multiply(fibo((N-1) / 2 + 1))).add(fibo((N-1) / 2).multiply(fibo((N-1) / 2)));
		}

		System.out.println(result);
	}
	
	static BigInteger fibo(int n) {

		for(int i = 2; i < n + 1; i++) {
			arr[i] = arr[i-1].add(arr[i-2]);
		}
		
		return arr[n];
	}

}
