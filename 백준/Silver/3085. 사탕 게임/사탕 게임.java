import java.io.*;
import java.util.*;

public class Main {
    static int max;
    static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0), Arrays.asList(0,1)
            , Arrays.asList(-1,0), Arrays.asList(0, -1));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[][] matrix = makeCharMatrix(br, n);
        max = Integer.MIN_VALUE;
        findMaxValue(matrix);
        System.out.println(max);
    }
    static char[][] makeCharMatrix(BufferedReader br, int size) throws IOException{
        char[][] matrix = new char[size][size];
        for(int i = 0; i < size; i++){
            char[] inputArray = br.readLine().toCharArray();
            for(int j = 0; j < size; j++){
                matrix[i][j] = inputArray[j];
            }
        }
        return matrix;
    }
    static void findMaxValue(char[][] matrix){
        for(int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix.length; x++) {
                int value = getLongValue(x,y, matrix);
                if (max < value) {
                    max = value;
                }
            }
        }
    }
    static int getLongValue(int x, int y, char[][] matrix){
        int max = 0;
        for(int i = 0; i < 4; i++){
            int targetX = x + direction.get(i).get(0);
            int targetY = y + direction.get(i).get(1);

            if(canGo(targetX, targetY, matrix)){
                swap(x, y, targetX, targetY, matrix);
                int value = Math.max(getSuccessXCount(targetX, targetY, matrix), getSuccessYCount(targetX, targetY, matrix));
                if(max < value){
                    max = value;
                }
                swap(x, y, targetX, targetY, matrix);
            }
        }
        return max;
    }


    static int getSuccessXCount(int x, int y, char[][] matrix){
        int value = 0;
        for(int toX = x; toX < matrix.length; toX++){
            if(matrix[y][toX] != matrix[y][x]){
                break;
            }
            value ++;
        }

        for(int toX = x-1; toX >= 0; toX--){
            if(matrix[y][toX] != matrix[y][x]){
                break;
            }
            value ++;
        }
        return value;
    }

    private static int getSuccessYCount(int x, int y, char[][] matrix) {
        int value = 0;
        for(int toY = y; toY < matrix.length; toY++){
            if(matrix[toY][x] != matrix[y][x]){
                break;
            }
            value ++;
        }

        for(int toY = y-1; toY >= 0; toY--){
            if(matrix[toY][x] != matrix[y][x]){
                break;
            }
            value ++;
        }
        return value;
    }


    static boolean canGo(int x, int y, char[][] matrix){
        return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
    }

    static void swap(int x1, int y1, int x2, int y2, char[][] matrix){
        char temp = matrix[y1][x1];
        matrix[y1][x1] = matrix[y2][x2];
        matrix[y2][x2] = temp;
    }
}