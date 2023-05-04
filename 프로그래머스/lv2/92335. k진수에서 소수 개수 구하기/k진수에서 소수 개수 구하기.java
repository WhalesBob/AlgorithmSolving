import java.util.*;

class Solution {
    public int solution(int n, int k){
        String converted = Integer.toString(n,k);
        StringTokenizer st = new StringTokenizer(converted,"0");

        List<Long> numberList= new ArrayList<>();
        while(st.hasMoreTokens()){
            numberList.add(Long.parseLong(st.nextToken()));
        }

        Collections.sort(numberList);

        long max = numberList.get(numberList.size()-1);
        int maxSqrt = (int)Math.ceil(Math.sqrt((double)max));
        List<Integer> primeNumber = getPrimeNumber(maxSqrt);

        int count = 0;
        for (Long number : numberList) {
            if ((number <= Integer.MAX_VALUE && primeNumber.contains(Integer.parseInt(Long.toString(number))) || isPrimeNumber(number, primeNumber))){
                count++;
            }
        }
        return count;
    }
    static boolean isPrimeNumber(long n, List<Integer> primeNumber){
        if(n == 1){
            return false;
        }

        for(int i = 0; i < primeNumber.size() && n > primeNumber.get(i); i++){
            if(n % primeNumber.get(i) == 0){
                return false;
            }
        }
        return true;
    }
    static List<Integer> getPrimeNumber(int n){
        boolean[] isPrime = new boolean[n+1];
        for(int i = 2; i <= n; i++){
            isPrime[i] = true;
        }

        for(int i = 2; i * i <= n; i++){
            if(isPrime[i]){
                for(int j = i*i; j <= n; j+=i){
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primeList = new ArrayList<>();
        for(int i = 2; i <= n; i++){
            if(isPrime[i]){
                primeList.add(i);
            }
        }
        return primeList;
    }
}