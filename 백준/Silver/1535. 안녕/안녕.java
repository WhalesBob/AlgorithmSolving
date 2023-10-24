import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int max = 0;
	static int[] loseHpArray;
	static int[] joyArray;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int personCount = Integer.parseInt(br.readLine());
		loseHpArray = makeArray(br, personCount);
		joyArray = makeArray(br, personCount);
		
		dfs(100, 0, 0);
		System.out.println(max);
	}
	static int[] makeArray(BufferedReader br, int size) throws IOException{
		int[] array = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		for(int i = 0; i < size; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		return array;
	}
	static void dfs(int hp, int joy, int index) {
		if(index == joyArray.length) {
			if(joy > max) {
				max = joy;
			}
			
			return;
		}
		
		if(hp > loseHpArray[index]) {
			dfs(hp - loseHpArray[index], joy + joyArray[index], index + 1);
		}
		
		dfs(hp, joy, index + 1);
	}
}