import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().trim().toCharArray();
        int[] intInput = new int[input.length];

        for(int i = 0; i < input.length; i++){
            intInput[i] = input[i] - '0';
        }

        int[] dp = new int[input.length];

        if(input.length == 1 || intInput[0] == 0){
            System.out.println(intInput[0] == 0 ? 0 : 1 );
            return;
        }


        dp[0] = 1;
        int value = makeCombine(intInput, 1);
        if(value < 10){
            dp[1] = dp[0];
        }else if(value % 10 == 0){
            if(value > 20){
                System.out.println(0);
                return;
            }

            dp[1] = dp[0];
        }else if(value > 26){
            dp[1] = 1;
        }else{
            dp[1] = 2;
        }

        for(int i = 2; i < dp.length; i++){
            int combine = makeCombine(intInput, i);
            if(combine % 10 == 0){
                if(combine == 0 || combine > 20){
                    System.out.println(0);
                    return;
                }

                dp[i] = dp[i-2];
            }else if(combine > 26 || combine < 10){
                dp[i] = dp[i-1];
            }else{
                dp[i] = (dp[i-1] + dp[i-2]) % 1_000_000;
            }
        }

        System.out.println(dp[dp.length - 1]);
    }
    static int makeCombine(int[] input, int index){
        return (input[index-1] * 10 + input[index]);
    }
}