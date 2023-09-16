import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static List<char[][]> numberInfo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] matrix = makeMatrix(br, 5, n / 5);

        StringBuilder answer = new StringBuilder();
        int xIndex = 0;
        putNumberInfo();

        while(xIndex < matrix[0].length){
            if(matrix[0][xIndex] == '#'){
                int result = getNumberInfo(matrix, xIndex);
                int number = result / 1_000_000;
                int nextIndex = result % 1_000_000;

                answer.append(number);
                xIndex = nextIndex;
            }else{
                xIndex ++;
            }
        }

        System.out.println(answer);
    }
    static int getNumberInfo(char[][] matrix, int firstIndex){
        if(matrix[0].length - firstIndex >= 2){
            for(int i = 2; i <= 9; i++){
                if(isNumberMatches(matrix, firstIndex, i)){
                    return makeInfo(i, firstIndex + 3);
                }
            }

            if (isNumberMatches(matrix, firstIndex, 0)) return makeInfo(0, firstIndex + 3);
        }
        if (isNumberMatches(matrix, firstIndex, 1)) return makeInfo(1, firstIndex + 1);
        return -1;
    }
    static int makeInfo(int info, int nextIndex){
        return info * 1_000_000 + nextIndex;
    }
    static boolean isNumberMatches(char[][] matrix, int firstIndex, int targetIndex){
        char[][] target = numberInfo.get(targetIndex);
        for(int y = 0; y < target.length; y++){
            for(int x = 0; x < target[0].length; x++){
                if(target[y][x] != matrix[y][firstIndex + x]){
                    return false;
                }
            }
        }
        return true;
    }
    static void putNumberInfo(){
        numberInfo = new ArrayList<>();

        numberInfo.add(new char[][]{{'#','#','#'}, {'#','.','#'}, {'#','.','#'}, {'#','.','#'}, {'#','#','#'}});
        numberInfo.add(new char[][]{{'#'},{'#'},{'#'},{'#'},{'#'}});
        numberInfo.add(new char[][]{{'#','#','#'}, {'.','.','#'}, {'#','#','#'}, {'#','.','.'}, {'#','#','#'}});
        numberInfo.add(new char[][]{{'#','#','#'}, {'.','.','#'}, {'#','#','#'}, {'.','.','#'}, {'#','#','#'}});
        numberInfo.add(new char[][]{{'#','.','#'}, {'#','.','#'}, {'#','#','#'}, {'.','.','#'}, {'.','.','#'}});
        numberInfo.add(new char[][]{{'#','#','#'}, {'#','.','.'}, {'#','#','#'}, {'.','.','#'}, {'#','#','#'}});
        numberInfo.add(new char[][]{{'#','#','#'}, {'#','.','.'}, {'#','#','#'}, {'#','.','#'}, {'#','#','#'}});
        numberInfo.add(new char[][]{{'#','#','#'}, {'.','.','#'}, {'.','.','#'}, {'.','.','#'}, {'.','.','#'}});
        numberInfo.add(new char[][]{{'#','#','#'}, {'#','.','#'}, {'#','#','#'}, {'#','.','#'}, {'#','#','#'}});
        numberInfo.add(new char[][]{{'#','#','#'}, {'#','.','#'}, {'#','#','#'}, {'.','.','#'}, {'#','#','#'}});
    }
    static char[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
        char[][] array = new char[height][width];
        char[] input = br.readLine().trim().toCharArray();

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int index = y * width + x;
                array[y][x] = input[index];
            }
        }
        return array;
    }
}