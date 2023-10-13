import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		
		int min = 0;
		int mid = 0;
		
		while(min < max) {
			mid = (min + max) / 2;
			
			long count = 0;
			
			for(int i = 0; i < N; i++) {
				
				if(arr[i] > mid) {
					count += arr[i] - mid;
				}
			}
			
			if(count >= M) {
				min = mid + 1;
			}
			else {
				max = mid;
			}
		}
		
		System.out.println(min - 1);
		
	}

}
