import java.util.Scanner;

public class Main {
    static final int MAX = 1_000_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] lazy = new int[3][n+1];
        int[][] absent = new int[2][n+1];
        int[] okay = new int[n+1];

        lazy[0][1] = 1; absent[0][1] = 1; okay[1] = 1;

        for(int i = 2; i <= n; i++){
            okay[i] = (okay[i-1] + getSum(absent, i-1)) % MAX;
            absent[0][i] = okay[i-1];
            absent[1][i] = absent[0][i-1];

            lazy[0][i] = (getSum(lazy, i-1) + getSum(absent, i-1) + okay[i-1]) % MAX;
            lazy[1][i] = lazy[0][i-1];
            lazy[2][i] = lazy[1][i-1];
        }

        int result = (getSum(lazy, n) + getSum(absent, n) + okay[n]) % MAX;
        System.out.println(result);
    }
    static int getSum(int[][] matrix, int x){
        int sum = 0;
        for(int y = 0; y < matrix.length; y++){
            sum += matrix[y][x];
        }
        return sum;
    }
}