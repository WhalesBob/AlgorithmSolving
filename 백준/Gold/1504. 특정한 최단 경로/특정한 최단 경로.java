import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int vertexCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());

        long[][] matrix = makeMatrix(br, vertexCount, edgeCount);

        for(int k = 1; k < matrix.length; k++){
            for(int i = 1; i < matrix.length; i++){
                for(int j = 1; j < matrix.length; j++){
                    if(matrix[i][j] > matrix[i][k] + matrix[k][j]){
                        matrix[i][j] = matrix[j][i] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }

        st = new StringTokenizer(br.readLine()," ");
        int passOne = Integer.parseInt(st.nextToken());
        int passTwo = Integer.parseInt(st.nextToken());

        long rootOne = matrix[1][passOne] + matrix[passOne][passTwo] + matrix[passTwo][matrix.length - 1];
        long rootTwo = matrix[1][passTwo] + matrix[passTwo][passOne] + matrix[passOne][matrix.length - 1];

        long result = Math.min(rootOne, rootTwo);
        System.out.println(result >= INF ? -1 : result);
    }
    static long[][] makeMatrix(BufferedReader br, int vertexCount, int edgeCount) throws IOException {
        long[][] matrix = new long[vertexCount + 1][vertexCount + 1];
        for(int i = 1; i < matrix.length; i++){
            Arrays.fill(matrix[i], INF);
            matrix[i][i] = 0;
        }

        for(int i = 0; i < edgeCount; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(matrix[a][b] > weight){
                matrix[a][b] = matrix[b][a] = weight;
            }
        }
        return matrix;
    }
}