import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static int[] check;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		check = new int[M];
		perm(1, 0);
		
		System.out.println(sb.toString());
		
	}
	
	static void perm(int start ,int cnt) {
		
		if(cnt == M) {
			for(int i = 0; i < M; i++) {
				sb.append(check[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start; i <= N; i++) {
			check[cnt] = i;
			perm(i, cnt + 1);
		}
		
	}

}
