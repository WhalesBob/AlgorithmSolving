import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a,b,c;
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a=(int)Math.pow(Integer.parseInt(st.nextToken()), 2);
			b=(int)Math.pow(Integer.parseInt(st.nextToken()), 2);
			c=(int)Math.pow(Integer.parseInt(st.nextToken()), 2);
			if(a==0 && b==0 && c ==0)
				break;
			else if(a==b+c || b==c+a || c==a+b)
				System.out.println("right");
			else
				System.out.println("wrong");
		}
	}
}