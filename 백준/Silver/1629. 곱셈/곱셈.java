import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int maxPow;
    static Map<Long, Long> dpMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        if(A == 1){
            bw.write("1");
        }else{
            maxPow = maxPowNumber(A) - 1;
            dpMap = new HashMap<>();
            bw.write(Long.toString(dividePow(A,B,C)));
        }
        bw.flush();
    }
    static int maxPowNumber(long A){
        double forKnow = A;
        int count = 1;
        while(forKnow < Math.sqrt(Long.MAX_VALUE)){
            forKnow *= A;
            count++;
        }
        return count;
    }
    static long dividePow(long A, long B, long C){
        if(dpMap.containsKey(B)){
            return dpMap.get(B);
        }

        if(B <= maxPow){
            dpMap.put(B,((long)Math.pow(A,B)) % C);
        }else{
            long pow1, pow2;
            if(B % 2 == 1){
                pow1 = (long)Math.floor((double)B/2D);
                pow2 = B - pow1;
            }else{
                pow1 = pow2 = (B/2);
            }
            long before = dividePow(A,pow1,C) % C;
            long after = dividePow(A,pow2,C) % C;
            dpMap.put(B,(before * after) % C);
        }
        return dpMap.get(B);
    }
}