import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < count; i++){
            String[] str = scanner.nextLine().split(" ");
            int r = Integer.parseInt(str[0]);
            int n = Integer.parseInt(str[1]);

            BigInteger a = multiply(n-r+1,n);
            BigInteger b = multiply(1,r);
            System.out.println(a.divide(b));
        }
    }
    static BigInteger multiply(int n, int m){
        BigInteger answer = BigInteger.ONE;
        for(int i = n; i <= m; i++){
            answer = answer.multiply(BigInteger.valueOf(i));
        }
        return answer;
    }
}