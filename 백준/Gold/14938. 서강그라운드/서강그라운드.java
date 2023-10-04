import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int[] itemArray;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int numberOfRegion = Integer.parseInt(st.nextToken());
        int findRange = Integer.parseInt(st.nextToken());
        int roadCount = Integer.parseInt(st.nextToken());

        long[][] matrix = makeLongMatrix(br, numberOfRegion, roadCount);


        for(int k = 1; k < matrix.length; k++){
            for(int i = 1; i < matrix.length; i++){
                for(int j = 1; j < matrix.length; j++){
                    if(i != k && k != j && i != j){
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    }
                }
            }
        }

        int max = -1;

        for(int i = 1; i <= numberOfRegion; i++){
            int value = itemArray[i];
            for(int j = 1; j <= numberOfRegion; j++){
                if(i != j && matrix[i][j] <= findRange){
                    value += itemArray[j];
                }
            }

            if(max < value){
                max = value;
            }
        }
        System.out.println(max);
    }
    static long[][] makeLongMatrix(BufferedReader br, int size, int roadCount) throws IOException{
        long[][] matrix = new long[size + 1][size + 1];
        itemArray = new int[size + 1];

        for(int i = 0; i < matrix.length; i++){
            Arrays.fill(matrix[i], INF);
            matrix[i][i] = 0;
        }

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 1; i < itemArray.length; i++){
            itemArray[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < roadCount; i++){
            st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(matrix[start][end] > weight){
                matrix[start][end] = matrix[end][start] = weight;
            }
        }
        return matrix;
    }
}