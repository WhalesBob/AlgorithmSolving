import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static BufferedWriter bw;
    static int n;
    static int maxDepth;
    static StringJoiner joiner;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = sc.nextInt();
        maxDepth = sc.nextInt();
        joiner = new StringJoiner(" ");

        for(int i = 1; i <= n; i++){
            makePermutation(1, Arrays.asList(i));
        }

        bw.flush();
        bw.close();
    }
    static void makePermutation(int depth, List<Integer> numberList) throws IOException {
        if(depth == maxDepth){
            for (Integer number : numberList) {
                joiner.add(Integer.toString(number));
            }
            bw.write(joiner.toString() + "\n");
            joiner = new StringJoiner(" ");
            return;
        }

        for(int i = 1; i <= n; i++){
            if(!numberList.contains(i)){
                ArrayList<Integer> newList = new ArrayList<>(numberList);
                newList.add(i);
                makePermutation(depth+1, newList);
            }
        }
    }
}