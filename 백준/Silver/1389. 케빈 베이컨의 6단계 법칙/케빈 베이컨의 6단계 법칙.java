import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int userCount = Integer.parseInt(st.nextToken());
        int friendCount = Integer.parseInt(st.nextToken());

        long[][] matrix = makeMatrix(br, userCount, friendCount);
        System.out.println(getAnswer(matrix));
    }
    static long getAnswer(long[][] matrix){
        for(int k = 1; k < matrix.length; k++){
            for(int s = 1; s < matrix.length; s++){
                for(int d = 1; d < matrix.length; d++){
                    long value = Math.min(matrix[s][d], matrix[s][k] + matrix[k][d]);
                    if(matrix[s][d] > value){
                        matrix[s][d] = matrix[d][s] = value;
                    }
                }
            }
        }
        
        long min = Long.MAX_VALUE;
        int minIndex = -1;
        for(int y = 1; y < matrix.length; y++){
            int sum = 0;
            for(int x = 1; x < matrix.length; x++){
                sum += matrix[y][x];
            }

            if(sum < min){
                min = sum;
                minIndex = y;
            }
        }

        return minIndex;
    }
    static long[][] makeMatrix(BufferedReader br, int size, int friendCount) throws IOException {
        long[][] matrix = new long[size + 1][size + 1];

        for(int y = 1; y < matrix.length; y++){
            Arrays.fill(matrix[y], INF);
            matrix[y][y] = 0;
        }

        for(int i = 0; i < friendCount; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            matrix[a][b] = matrix[b][a] = 1;
            
        }
        return matrix;
    }
}