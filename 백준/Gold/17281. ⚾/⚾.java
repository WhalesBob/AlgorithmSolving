import java.io.*;
import java.util.*;

public class Main {
	static int max;
	static Hitter[] hitters;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		hitters = makeHitters(br, n);
		
		int[] indexArray = new int[8];
		for(int i = 0; i < 8; i++) {
			indexArray[i] = i + 1;
		}
		max = Integer.MIN_VALUE;
		
		permutation(indexArray, 0, 8, 8, n);
		System.out.println(max);
	}
	static void permutation(int[] arr, int depth, int n, int r, int inning) {
		if(depth == r) {
			int value = getScore(arr, inning);
			if(max < value) {
				max = value;
			}
			return;
		}
		
		for(int i = depth; i < n; i++) {
			swap(arr, depth, i);
			permutation(arr, depth + 1, n, r, inning);
			swap(arr, depth, i);
		}
	}
	static int getScore(int[] indexArray, int inning) {
		int[] hitterArray = getRealIndex(indexArray);
		
		int hitterIndex = 0;
		int score = 0;
		for(int i = 0; i < inning; i++) {
			Queue<Integer> list = new ArrayDeque<>();
			int outCount = 0;
			while(outCount < 3) {
				Hitter hitter = hitters[hitterArray[hitterIndex]];
				
				if(hitter.scoring[i] == 0) {
					outCount++;
				}else {
					if(list.size() > 0) {
						int size = list.size();
						for(int e = 0; e < size; e++) {
							int element = list.remove();
							list.add(element + hitter.scoring[i]);
						}
					}
					
					list.add(hitter.scoring[i]);
					
					while(!list.isEmpty() && list.peek() >= 4) {
						score++;
						list.remove();
					}
				}
				
				hitterIndex = (hitterIndex + 1) % 9;
			}
		}
		
		return score;
	}
	static int[] getRealIndex(int[] indexArray) {
		int[] hitterIndex = new int[9];
		for(int i = 0; i < 3; i++) {
			hitterIndex[i] = indexArray[i];
		}
		hitterIndex[3] = 0;
		for(int i = 3; i < 8; i++) {
			hitterIndex[i + 1] = indexArray[i];
		} 
		return hitterIndex;
	}
	static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	static Hitter[] makeHitters(BufferedReader br, int size) throws IOException{
		Hitter[] hitter = new Hitter[9];
		for(int i = 0; i < hitter.length; i++) {
			hitter[i] = new Hitter(size);
		}
		
		for(int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int h = 0; h < 9; h++) {
				hitter[h].scoring[i] = Integer.parseInt(st.nextToken());
			}
		}
		return hitter;
	}
	static class Hitter{
		int[] scoring;

		public Hitter(int size) {
			this.scoring = new int[size];
		}
	}
}