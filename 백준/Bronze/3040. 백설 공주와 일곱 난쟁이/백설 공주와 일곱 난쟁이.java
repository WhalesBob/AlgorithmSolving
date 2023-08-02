import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] nine = new int[9];
		int sum = 0;
		for(int i = 0 ; i < 9;i++) {
			nine[i] = sc.nextInt();
			sum+=nine[i];
		}
		int test = sum;
		for(int i = 0 ; i < 9 ; i ++) {
			boolean finded=false;
			test = sum;
			test -= nine[i];
			for(int j = i+1 ; j < 9;j++) {
				test -= nine[j];
				if(test == 100)	{
					nine[i]=-1;
					nine[j]=-1;
					finded=true;
				}
				test += nine[j];
			}
			if(finded==true) break;
		}
		for(int i = 0 ; i < 9 ; i ++) {
			if(nine[i]==-1) continue;
			else System.out.println(nine[i]);
		}
	}
}