import java.io.*;
import java.util.*;

public class Main {
    static Set<List<Integer>> zeroSet;
    static int realMax;
    static int realMin = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        zeroSet = new HashSet<>();
        int[][] matrix = makeMatrix(br);
        if(needBinarySearch(matrix)) {
            realMax = findNumbersByBinarySearch(matrix);
        }
        putNumbersByOrdinaryMethod(matrix);
        printMatrix(matrix);
    }
    static int findNumbersByBinarySearch(int[][] matrix){
        int left = realMax + 1;
        int right = realMin + 20000;
        int answer = 0;

        while(left <= right){
            int mid = ((left + right ) / 2);
            int result = isAvailable(matrix, mid);
            if(result == 0){
                return mid;
            }else if(result == 1){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return answer;
    }
    static int isAvailable(int[][] matrix, int value){
        int[][] cloneMatrix = makeCloneMatrix(matrix);
        boolean isDownRight = cloneMatrix[4][4] == 0;
        for(List<Integer> zeroTuple : zeroSet){
            int x = zeroTuple.get(0);
            int y = zeroTuple.get(1);
            cloneMatrix[y][x] = findAddNumbers(x, y, cloneMatrix, value);
            if(x == 2 && y == 2){
                cloneMatrix[4][4] += cloneMatrix[y][x];
                cloneMatrix[4][0] += cloneMatrix[y][x];
            }else{
                if(isDownRight){
                    cloneMatrix[4][4] += cloneMatrix[y][x];
                }else{
                    cloneMatrix[4][0] += cloneMatrix[y][x];
                }
            }
            cloneMatrix[4][x] += cloneMatrix[y][x];
            cloneMatrix[y][4] += cloneMatrix[y][x];
        }
        List<Integer> maxMinTuple = getMaxMin(cloneMatrix);
        int max = maxMinTuple.get(0);
        int min = maxMinTuple.get(1);
        if(max == min && max == value){
            return 0;
        }
        int seeValue = isDownRight ? cloneMatrix[4][4] : cloneMatrix[4][0];
        return seeValue < value ? 1 : 2;
    }
    static List<Integer> getMaxMin(int[][] matrix){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int y = 1; y < 5; y++){
            if(matrix[y][4] < min){
                min = matrix[y][4];
            }
            if(matrix[y][4] > max){
                max = matrix[y][4];
            }
        }
        for(int x = 0; x < 4; x++){
            if(matrix[4][x] < min){
                min = matrix[4][x];
            }
            if(matrix[4][x] > max){
                max = matrix[4][x];
            }
        }
        return Arrays.asList(max, min);
    }
    static int[][] makeCloneMatrix(int[][] matrix){
        int[][] newMatrix = new int[5][5];
        for(int i = 0; i < 5; i++){
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, 5);
        }
        return newMatrix;
    }

    static void putNumbersByOrdinaryMethod(int[][] matrix){
        for(List<Integer> zeroTuple : zeroSet){
            int zeroX = zeroTuple.get(0);
            int zeroY = zeroTuple.get(1);
            matrix[zeroY][zeroX] = findAddNumbers(zeroX, zeroY, matrix, realMax);
        }
    }
    static int findAddNumbers(int x, int y, int[][] matrix, int sum){
        int maxOne = Math.max(matrix[y][4], matrix[4][x]);
        if(x == y){
            return sum - Math.max(maxOne, matrix[4][4]);
        }else if(y == (4-x)){
            return sum - Math.max(maxOne, matrix[4][0]);
        }
        return sum - maxOne;
    }
    static void printMatrix(int[][] matrix){
        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 3; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    static int[][] makeMatrix(BufferedReader br) throws IOException{
        int[][] matrix = new int[5][5];
        int sum = 0;
        for(int y = 1; y <= 3; y++){
            sum = 0;
            String[] input =  br.readLine().trim().split(" ");
            for(int x = 1; x <= 3; x++){
                matrix[y][x] = Integer.parseInt(input[x-1]);
                if(matrix[y][x] == 0){
                    zeroSet.add(Arrays.asList(x,y));
                }
                sum += matrix[y][x];
            }
            matrix[y][4] = sum;
            changeMaxMin(sum, sum);
        }

        for(int x = 1; x <= 3; x++){
            sum = 0;
            for(int y = 1; y <= 3; y++){
                sum += matrix[y][x];
            }
            matrix[4][x] = sum;
            changeMaxMin(sum, sum);
        }

        for(int i = 1; i <= 3; i++){
            matrix[4][4] += matrix[i][i];
            matrix[4][0] += matrix[i][4-i];
        }
        int max = Math.max(matrix[4][4], matrix[4][0]);
        int min = Math.min(matrix[4][4], matrix[4][0]);
        changeMaxMin(max, min);
        return matrix;
    }
    static boolean needBinarySearch(int[][] matrix){
        return matrix[4][4] == 0 || matrix[4][0] == 0;
    }
    static void changeMaxMin(int max, int min){
        realMax = Math.max(realMax, max);
        if(min > 0){
            realMin = Math.min(realMin, min);
        }
    }
}