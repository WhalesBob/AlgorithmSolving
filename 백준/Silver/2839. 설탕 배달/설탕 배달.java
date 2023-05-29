import java.util.Scanner;

public class Main {
    static final int INFINITY = 9_999_999;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n <= 5){
            System.out.println((n == 3 || n == 5) ? 1 : -1);
            return;
        }

        int[] array = new int[n+1];
        for(int i = 1; i < array.length; i++){
            array[i] = INFINITY;
        }

        array[0] = 0;
        array[3] = array[5] = 1;
        for(int i = 6; i < array.length; i++){
            array[i] = Math.min(array[i-3], array[i-5]) + 1;
        }
        System.out.println(array[n] <= INFINITY ? array[n] : -1);
    }
}