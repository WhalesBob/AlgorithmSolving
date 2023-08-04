import java.util.*;

public class Solution {
	public static void main(String args[]) throws Exception{
		
		Scanner sc = new Scanner(System.in);
		int T = 10;
        int numbers = 8;
        int n;

        for(int test_case = 1; test_case <= T; test_case++){
            Queue<Integer> queue = new ArrayDeque<>();
            sc.nextInt();

            for(int i = 0; i < numbers; i++){
                queue.add(sc.nextInt());
            }

            int index = 0;
            do{
                n = queue.remove() - (index++ % 5 + 1);
                n = Math.max(n, 0);
                queue.add(n);
            }while(n != 0);

            System.out.printf("#%d ",test_case);
            for(int i = 0; i < 8; i++){
                System.out.printf("%d ",queue.remove());
            }
            System.out.println("");
        }
    }
}