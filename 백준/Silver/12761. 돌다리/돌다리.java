import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_VALUE = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int aCong = Integer.parseInt(st.nextToken());
        int bCong = Integer.parseInt(st.nextToken());

        int current = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new ArrayDeque<>();
        int[] jumpCount = new int[100_001];

        Arrays.fill(jumpCount, MAX_VALUE);
        jumpCount[current] = 0;
        queue.add(current);
        int[] justGoArray = new int[]{aCong, -1 * aCong, bCong, -1 * bCong,1, -1};
        int[] jumpArray = new int[]{aCong, bCong};

        while(!queue.isEmpty() && jumpCount[dest] == MAX_VALUE){
            int position = queue.remove();

            for(int cong : jumpArray){
                if(canPowerJump(jumpCount, position, cong)){
                    jumpCount[position * cong] = jumpCount[position] + 1;
                    queue.add(position * cong);
                }
            }

            for(int cong : justGoArray){
                if(canJustGo(jumpCount, position, cong)){
                    jumpCount[position + cong] = jumpCount[position] + 1;
                    queue.add(position + cong);
                }
            }
        }

        System.out.println(jumpCount[dest]);
    }
    static boolean canPowerJump(int[] count, int position, int toJump){
        return (position * toJump) < count.length
                && count[position * toJump] > count[position] + 1;
    }
    static boolean canJustGo(int[] count, int position, int toGo){
        return 0 <= (position+ toGo) && (position + toGo) < count.length
                && count[position + toGo] > count[position] + 1;
    }
}