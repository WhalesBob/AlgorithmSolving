import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		char[] charArray = br.readLine().toCharArray();
		char[] toCompare = br.readLine().toCharArray();
		
		int[] piArray = makePi(toCompare);
		
		List<Integer> resultList = kmp(charArray, toCompare, piArray);
		bw.write(resultList.size() + "\n");
		for(Integer r : resultList) {
			bw.write(r + " ");
		}
		
		bw.flush();
		
	}
	static List<Integer> kmp(char[] charArray, char[] toCompare, int[] piArray){
		
		List<Integer> list = new ArrayList<>();
		
		int i = 0, j = 0;
		while(i < charArray.length) {
			while(i < charArray.length && j < toCompare.length) {
				if(charArray[i] == toCompare[j]) {
					i++; j++;
				}else {
					if(j == 0) {
						i++;
					}else {
						j = piArray[j-1];
					}
				}
			}
			
			if(j == toCompare.length) {
				list.add(i - j + 1);
				j = piArray[j-1];
			}
		}
		
		return list;
	}
	static int[] makePi(char[] array) {
		int[] piArray = new int[array.length];
		
		int i = 1, j = 0;
		while(i < array.length && j < array.length) {
			if(array[i] == array[j]) {
				piArray[i] = j + 1;
				i++; j++;
			}else {
				if(j == 0) {
					i++;
				}
				else {
					j = piArray[j-1];
				}
			}
		}
		
		return piArray;
	}
}