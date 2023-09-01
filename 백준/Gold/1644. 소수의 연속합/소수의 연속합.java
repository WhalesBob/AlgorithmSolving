import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
        if(n == 1){
			System.out.println(0);
			return;
		}
		
		List<Integer> primeList = getPrimeNumber(n);
		
		int count = 0;

		if(primeList.get(primeList.size() - 1) == n) {
			count++;
		}
		
		int foundCountValue = 1;
		int maxCount = getMaxAddCount(primeList, n);
		
		int right = primeList.size() - 1;
		boolean found = false;
		while(foundCountValue <= maxCount) {
			int left = 0;
			if(!found) right = primeList.size() - 1;
			foundCountValue++;
			while(left <= right) {
				found = false;
				int mid = (left + right) / 2;
				int result = isAvailable(primeList, mid, foundCountValue, n);
				if(result == 0) {
					count++; 
					right = mid - 1;
					found = true;
					break;
				}else if(result == 1) {
					right = mid - 1;
				}else {
					left = mid + 1;
				}
			}
		}
		System.out.println(count);
	}
	static int isAvailable(List<Integer> list, int lastIndex, int elementCount, int targetValue) {
		int sum = 0;
		int count = 0;
		if(lastIndex > list.size() -1) {
			lastIndex = list.size() - 1;
		}
		for(int i = lastIndex; i > lastIndex - elementCount && i >= 0; i--) {
			sum += list.get(i);
			count++;
		}
		
		if(sum == targetValue && count == elementCount) {
			return 0;
		}
		return sum > targetValue ? 1 : 2;
	}
	static List<Integer> getPrimeNumber(int n){
		boolean[] isPrime = new boolean[n+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false; isPrime[1] = false;
		
		for(int i = 2; i * i <= n; i++) {
			for(int j = i * 2; j <= n; j += i) {
				isPrime[j] = false;
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 2; i <= n; i++) {
			if(isPrime[i]) {
				list.add(i);
			}
		}
		return list;
	}
	static int getMaxAddCount(List<Integer> list, int targetValue) {
		int sum = 0;
		for(int i = 0; i < list.size(); i++) {
			sum += list.get(i);
			if(sum > targetValue) {
				return i;
			}
		}
		return list.size() -1;
	}
} 