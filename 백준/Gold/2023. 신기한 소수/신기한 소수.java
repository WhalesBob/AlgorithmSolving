import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static BufferedWriter bw;
    static ArrayList<Integer> primeNumber;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = sc.nextInt();
        int size = n % 2 == 0 ? n/2 : n/2 + 1;
        boolean[] isPrime = new boolean[(int)Math.pow(10,size) + 1];
        Arrays.fill(isPrime,true);

        isPrime[0] = isPrime[1] = false;
        for(int i = 2; (i*i) < isPrime.length; i++){
            for(int j = i*i ; j < isPrime.length; j+=i){
                if(isPrime[j]){
                    isPrime[j] = false;
                }
            }
        }
        primeNumber = new ArrayList<>();
        for(int i = 2; i < isPrime.length; i++){
            if(isPrime[i]){
                primeNumber.add(i);
            }
        }

        int[] firstPrimeNumber = {2,3,5,7};
        for(int i = 0; i < 4; i++){
            dfs(new StringBuilder(Integer.toString(firstPrimeNumber[i])), n);
        }
        bw.flush();
    }
    static void dfs(StringBuilder builder, int limitSize) throws IOException {
        if(builder.length() == limitSize){
            bw.write(builder + "\n");
            return;
        }
        for(int i = 1; i < 10; i += 2){
            int number = Integer.parseInt(builder.toString() + i);
            if(primeNumber.contains(number) || isPrimeNumber(number)){
                dfs(new StringBuilder(Integer.toString(number)), limitSize);
            }
        }
    }
    static boolean isPrimeNumber(int n){
        for (int toDivide : primeNumber) {
            if (Math.sqrt(n) >= toDivide && n % toDivide == 0) {
                return false;
            }
        }
        return true;
    }
}