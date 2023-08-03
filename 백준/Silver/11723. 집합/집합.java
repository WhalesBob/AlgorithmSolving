import java.io.*;
import java.util.*;

public class Main {
    static int bitMask;
    public static void main(String[] args) throws IOException {
        bitMask = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String operate = st.nextToken();
            int item = 0;
            if (!(operate.equals("all") || operate.equals("empty"))) {
                item = Integer.parseInt(st.nextToken());
            }
            operation(bw, operate, item);
        }
        bw.flush();
    }
    static void operation(BufferedWriter bw,String operate, int item) throws IOException{
        switch (operate){
            case "add":
                bitMask |= (1 << item);
                break;
            case "remove":
                bitMask &= ~(1 << item);
                break;
            case "check":
                if((bitMask & (1 << item)) == (1 << item)){
                    bw.write("1\n");
                }else{
                    bw.write("0\n");
                }
                break;
            case "toggle":
                bitMask ^= (1 << item);
                break;
            case "all":
                bitMask = (int)Math.pow(2,21) - 2;
                break;
            case "empty":
                bitMask = 0;
        }
    }
}