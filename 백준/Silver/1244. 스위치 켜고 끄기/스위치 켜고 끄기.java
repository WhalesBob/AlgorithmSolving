import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] number = new int[101];
	static int n;
	public static void boys(int x,int cnt) {
		int t= (x*cnt)-1;
		if(t>(n-1)) return;
		number[t]=(number[t]==0)? 1:0;
		boys(x,cnt+1);
	}
	public static void girls(int x,int cnt) {
		if(((x-cnt)-1)<0 || ((x+cnt)-1)>(n-1) || (number[(x-cnt)-1]!=number[(x+cnt)-1])) {
			number[x-1]=(number[x-1]==0)? 1:0;
			return ;
		}
		number[(x-cnt)-1]=(number[(x-cnt)-1]==0)? 1:0;
		number[(x+cnt)-1]=(number[(x+cnt)-1]==0)? 1:0;
		girls(x,cnt+1);
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		} //입력 끝
		int m = Integer.parseInt(br.readLine()); //학생수
		for(int i = 0 ; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a == 1) boys(b ,1);
			if(a == 2) girls(b ,0);
		}
		int c = 0;
		for(int i = 0 ; i < n ; i++) {
			System.out.print(number[i]);
			c++;
			if(i!=n-1)
				System.out.print(" ");
			if(c==20) {
				c=0;
				System.out.println();
			}
		} //출력
	}
}