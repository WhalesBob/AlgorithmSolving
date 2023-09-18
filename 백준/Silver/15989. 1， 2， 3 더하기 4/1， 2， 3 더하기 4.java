import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long[][] matrix = new long[4][10_001];
        for(int i = 0; i <= 10000; i++){
            matrix[1][i] = 1;
        }
        matrix[2][2] = 1; matrix[3][3] = 1; matrix[2][3] = 1;

        for(int i = 4; i <= 10000; i++){
            matrix[2][i] = matrix[1][i-2] + matrix[2][i-2];
            matrix[3][i] = (matrix[1][i-3] + matrix[2][i-3] + matrix[3][i-3]);
        }
        

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            int c = Integer.parseInt(br.readLine());
            bw.write((matrix[1][c] + matrix[2][c] + matrix[3][c]) + "\n");
        }

        bw.flush();
    }
}