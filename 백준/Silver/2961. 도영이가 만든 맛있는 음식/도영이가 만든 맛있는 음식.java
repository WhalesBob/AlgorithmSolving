
import java.io.IOException;
import java.util.Scanner;

//2961번 도영이가 만든 맛있는 음식

public class Main {
	static long[][] arr;
	static long answer=1000000000;
	static int n;
	public static void find(int a,int cnt, long sum, long sum1) {
		if(a==n) {
			if(cnt!=0) { 
				answer = ( (Math.abs(answer) > Math.abs(sum1-sum)) ? (Math.abs(sum1-sum)) : (Math.abs(answer)) );
			}
			return;
		}
		else {
			find(a+1,cnt,sum,sum1);
			sum=sum*arr[a][0];
			sum1=sum1+arr[a][1];
			find(a+1,cnt+1,sum,sum1);
		}
		return;
	}
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr= new long[n][2];
		for(int i = 0 ; i < n ; i++) {
			arr[i][0]= sc.nextLong();
			arr[i][1]= sc.nextLong();
		}
		find(0,0,1,0);
		System.out.println(answer);
	}
}