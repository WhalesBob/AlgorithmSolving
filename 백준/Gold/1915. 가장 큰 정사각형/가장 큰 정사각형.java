import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] matrix = makeMatrix(br, n, m);

        System.out.println(getAnswer(matrix));
    }
    static int getAnswer(int[][] matrix){
        int max = 0;
        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[0].length; x++){
                if(matrix[y][x] > 0 && canGo(x-1,y-1, matrix)){
                    int slide = matrix[y-1][x-1];
                    int up = matrix[y-1][x];
                    int left = matrix[y][x-1];

                    matrix[y][x] = Math.min(Math.min(slide, up), left) + 1;
                }

                if(max < matrix[y][x]){
                    max = matrix[y][x];
                }
            }
        }
        return max * max;
    }
    static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
        int[][] matrix = new int[height][width];
        for(int y = 0; y < height; y++){
            char[] input = br.readLine().trim().toCharArray();
            for(int x = 0; x < width; x++){
                matrix[y][x] = input[x] - '0';
            }
        }
        return matrix;
    }
    static boolean canGo(int x, int y, int[][] matrix){
        return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
    }
}