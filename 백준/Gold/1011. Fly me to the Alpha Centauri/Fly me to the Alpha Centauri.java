import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int cases = Integer.parseInt(br.readLine());
        for(int i = 0; i < cases; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken()),y = Integer.parseInt(st.nextToken());
            int distance = Math.abs(y-x);
            int n = (int)Math.ceil(Math.sqrt(distance));
            bw.write(((n*n - n < distance) ? (2 * n) - 1 : (2 * n) - 2) + "\n");
        }
        bw.flush();
    }
}
