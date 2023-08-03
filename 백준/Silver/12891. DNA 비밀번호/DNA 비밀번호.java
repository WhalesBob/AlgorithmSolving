import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int s = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		
		String inputString = br.readLine().trim();
		
		Map<Character, Integer> necessaryMap = necessaryMap(br);
		Map<Character, Integer> charMap = charMapInit(inputString, p);
		char start, end;
		int count = 0;
		for(int i = 0; i < s - p; i++) {
			
			if(isValid(necessaryMap, charMap)) {
				count++;
			}
			
			start = inputString.charAt(i);
			end = inputString.charAt(i + p);
			replaceNext(charMap, start, end);
		}
		
		if(isValid(necessaryMap, charMap)) {
			count++;
		}
		
		System.out.println(count);
	}
	static boolean isValid(Map<Character, Integer> checkMap, Map<Character, Integer> charMap) {
		
		for(Character c : checkMap.keySet()) {
			Integer have = charMap.get(c);
			if(checkMap.get(c) == 0) {
				continue;
			}
			if(have == null || have < checkMap.get(c)) {
				return false;
			}
		}
		
		return true;
	}
	static Map<Character, Integer> charMapInit(String input, int end) {
		Map<Character, Integer> map = new HashMap<>();
		for(int i = 0; i < end; i++) {
			char c = input.charAt(i);
			if(map.containsKey(c)) {
				map.replace(c, map.get(c) + 1);
			}else {
				map.put(c, 1);
			}
		}
		return map;
	}
	static void replaceNext(Map<Character, Integer> map,char prev, char next) {
		map.replace(prev, map.get(prev) - 1);
		if(map.containsKey(next)) {
			map.replace(next, map.get(next) + 1);
		}else {
			map.put(next, 1);
		}
	}
	static Map<Character, Integer> necessaryMap(BufferedReader br) throws IOException{
		Map<Character, Integer> map = new HashMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		char[] charArray = new char[] {'A','C','G','T'};
		for(int i = 0; i < 4; i++) {
			map.put(charArray[i], Integer.parseInt(st.nextToken()));
		}
		return map;
	}
}