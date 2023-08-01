import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
	static final int INFINITY = 999_999_999;
	static Set<Integer> primeNumberSet;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		primeNumberSet = makePrimeNumber();
		
		for(int test_case = 1; test_case <= t; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			System.out.println(getConvertCount(start, end));
		}
	}
	static Set<Integer> makePrimeNumber(){
		boolean[] visited = new boolean[10001];
		for(int i = 2; i < visited.length; i++) {
			visited[i] = true;
		}
		
		for(int i = 2; (i * i) <= 10000; i++) {
			if(visited[i]) {
				for(int j = i * i; j <= 10000; j += i) {
					visited[j] = false;
				}
			}
		}
		
		Set<Integer> primeSet = new HashSet<>();
		Stream.iterate(1000,  a -> a + 1).limit(9000).forEach(x -> {
			if(visited[x]) {
				primeSet.add(x);
			}
		});
		return primeSet;
	}
	static int getConvertCount(int start, int end) {
		Map<Integer, Integer> primeNumberMap = new HashMap<>();
		for(Integer n : primeNumberSet) {
			primeNumberMap.put(n, INFINITY);
		}
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		primeNumberMap.replace(start, 0);
		int count = 1;
		while(!queue.isEmpty()) {
			int queueSize = queue.size();
			for(int i = 0; i < queueSize; i++) {
				Integer element  = queue.remove();
				Set<Integer> nextPrimeNumber = convertedNumber(element);
				for(Integer next : nextPrimeNumber) {
					Integer value = primeNumberMap.get(next);
					if(count < value) {
						primeNumberMap.replace(next, count);
						queue.add(next);
					}
				}
			}
			
			count++;
		}
		
		return primeNumberMap.get(end);
	}

	static Set<Integer> convertedNumber(int number){
		Set<Integer> set = new HashSet<>();
		char[] numberArray = Integer.toString(number).toCharArray();
		for(int i = 0; i < 4; i++) {
			char temp = numberArray[i];
			int convertNum = i == 0 ? 1 : 0;
			for(;convertNum < 10; convertNum++) {
				int convert = convertedNum(numberArray, convertNum, i);
				if(primeNumberSet.contains(convert)) {
					set.add(convert);
				}
			}
			numberArray[i] = temp;
		}
		return set;
	}
	static int convertedNum(char[] numberArray,int changeNumber, int index) {
		numberArray[index] = (char)(changeNumber + '0');
		StringBuilder builder = new StringBuilder();
		for(char c : numberArray) {
			builder.append(c);
		}
		return Integer.parseInt(builder.toString());
	}
}