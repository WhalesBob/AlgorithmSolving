import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(0,-1),Arrays.asList(1,0),
			Arrays.asList(0,1), Arrays.asList(-1,0));
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			String input = br.readLine().trim();
			System.out.println(getMinimumSize(input));
		}
	}
	static int getMinimumSize(String input) {
		char[] inputArray = input.toCharArray();
		Set<Integer> xset = new HashSet<>();
		Set<Integer> yset = new HashSet<>();
		
		int currentX = 0, currentY = 0;
		xset.add(currentX);
		yset.add(currentY);
		int currentDirect = 0;
		
		for(char c : inputArray) {
			if(c == 'F') {
				currentX += direction.get(currentDirect).get(0);
				currentY += direction.get(currentDirect).get(1);
			}else if(c == 'B') {
				currentX -= direction.get(currentDirect).get(0);
				currentY -= direction.get(currentDirect).get(1);
			}else if(c == 'L') {
				currentDirect = (4 + currentDirect - 1) % 4; 
			}else if(c == 'R') {
				currentDirect = (currentDirect + 1) % 4;
			}
			
			xset.add(currentX);
			yset.add(currentY);
		}
		
		List<Integer> xBound = getMaxMin(xset);
		List<Integer> yBound = getMaxMin(yset);
		
		return (int)(Math.abs(xBound.get(1) - xBound.get(0)) * Math.abs(yBound.get(1) - yBound.get(0)));
	}
	static List<Integer> getMaxMin(Set<Integer> set){
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(Integer element : set) {
			if(max < element) {
				max = element;
			}
			if(min > element) {
				min = element;
			}
		}
		return Arrays.asList(min, max);
	}
}