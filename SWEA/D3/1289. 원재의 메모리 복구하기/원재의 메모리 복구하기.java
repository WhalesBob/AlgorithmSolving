import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++){
            String data = sc.next();
            System.out.printf("#%d %d\n",test_case,fixBits(data));
        }
    }
    static int fixBits(String data){
        StringBuilder builder = new StringBuilder();
        int count = 0;

        for(int i = 0; i < data.length(); i++){
            builder.append("0");
        }

        for(int i = 0; i < data.length(); i++){
            if(builder.charAt(i) != data.charAt(i)){
                count++;
                fixing(builder,data.substring(i,i+1),i);
            }
        }
        return count;
    }
    static void fixing(StringBuilder builder, String toFix, int index){
        for(int i = index; i < builder.length(); i++){
            builder.replace(i,i+1,toFix);
        }
    }
}