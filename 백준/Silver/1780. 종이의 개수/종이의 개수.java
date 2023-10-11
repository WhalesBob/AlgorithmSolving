import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] matrix = makeMatrix(br, n);

        int[] answer = takePaperCount(matrix, 0, 0, matrix.length, matrix.length);
        System.out.printf("%d\n%d\n%d", answer[0], answer[1], answer[2]);
    }
    static int[] takePaperCount(int[][] matrix, int startX, int startY, int endX, int endY){
        if(isAllSame(matrix, startX, startY, endX, endY)){
            return takeOne(matrix[startY][startX]);
        }

        int thirdY = (endY - startY) / 3;
        int thirdX = (endX - startX) / 3;

        int[] count = new int[3];

        for(int takeY = 0; takeY < 3; takeY++){
            for(int takeX = 0; takeX < 3; takeX++){
                int thirdStartX = startX + thirdX * takeX;
                int thirdStartY = startY + thirdY * takeY;
                int[] result = takePaperCount(matrix, thirdStartX, thirdStartY, thirdStartX + thirdX, thirdStartY + thirdY);

                for(int i = 0; i < 3; i++){
                    count[i] += result[i];
                }
            }
        }
        return count;
    }
    static int[] takeOne(int element){
        switch(element){
            case -1 : return new int[]{1,0,0};
            case 0 : return new int[]{0,1,0};
            case 1 : return new int[]{0,0,1};
        }
        return null;
    }
    static boolean isAllSame(int[][] matrix, int startX, int startY, int endX, int endY){
        int firstElement = matrix[startY][startX];
        for(int y = startY; y < endY; y++){
            for(int x = startX; x < endX; x++){
                if(matrix[y][x] != firstElement){
                    return false;
                }
            }
        }
        return true;
    }
    static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
        int[][] matrix = new int[size][size];
        for(int y = 0; y < matrix.length; y++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            for(int x = 0; x < matrix.length; x++){
                matrix[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        return matrix;
    }
}