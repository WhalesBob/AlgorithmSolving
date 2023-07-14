import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[][] matrix = makeMatrix(w, h, br);
        System.out.println(getMaxRaining(matrix));
    }
    static int[][] makeMatrix(int w, int h, BufferedReader br) throws IOException{
        int[][] matrix = new int[h][w];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int x = 0; x < w; x++) {
            int height = Integer.parseInt(st.nextToken());
            for(int y = 0; y < height; y++) {
                matrix[y][x] = 1;
            }
        }

        return matrix;
    }
    static int getMaxRaining(int[][] matrix){
        int count = 0;
        for(int y = 0; y < matrix.length; y++){
            for(int x= 0; x < matrix[0].length; x++){
                if (matrix[y][x] == 0 && isThisWaterBlocked(x, y, matrix)){
                    matrix[y][x] = 2;
                    count ++;
                }
            }
        }
        return count;
    }
    static boolean isThisWaterBlocked(int inputX, int inputY, int[][] matrix){
        boolean isLeftBlock = false;
        boolean isRightBlock = false;

        for(int x = 0; x < inputX && !isLeftBlock; x++){
            if(matrix[inputY][x] == 1){
                isLeftBlock = true;
            }
        }

        for(int x = inputX; x < matrix[0].length && !isRightBlock; x++){
            if(matrix[inputY][x] == 1){
                isRightBlock = true;
            }
        }
        return isLeftBlock && isRightBlock;
    }
}