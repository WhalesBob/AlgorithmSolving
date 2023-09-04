import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int length = Integer.parseInt(br.readLine());
        char[] input = br.readLine().trim().toCharArray();

        int after = 2 * n + 1;
        int before = 0;
        int count = 0;

        char[] compare = new char[after];
        compare[0] = 'I';

        for(int i = 1; i < after; i+=2){
            compare[i] = 'O';
            compare[i+1] = 'I';
        }

        boolean beforeSame = false;
        while(after < input.length){
            beforeSame = beforeSame ? isPartialSame(input, after) : isAllSame(input, compare, before, after);
            if(beforeSame){
                count++;
                after++;
                before++;
            }

            if(after < input.length){
                after++;
                before++;
            }
        }

        beforeSame = beforeSame ? isPartialSame(input, after) : isAllSame(input, compare, before, after);
        if(beforeSame){
            count++;
        }
        System.out.println(count);
    }
    static boolean isAllSame(char[] input, char[] compare, int before, int after){
        for(int i = before; i < after; i++){
            if(input[i] != compare[i-before]){
                return false;
            }
        }
        return true;
    }
    static boolean isPartialSame(char[] input, int after){
        return input[after-2] == 'O' && input[after-1] == 'I';
    }
}