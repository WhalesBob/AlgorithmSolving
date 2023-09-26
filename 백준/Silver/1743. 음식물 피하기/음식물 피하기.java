import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0),
            Arrays.asList(0,1), Arrays.asList(-1,0), Arrays.asList(0,-1));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());

        char[][] matrix = makeMatrix(br, height, width, count);
        System.out.println(solution(matrix));
    }
    static int solution(char[][] matrix){
        Queue<Integer> queue = new ArrayDeque<>();
        int max = 0;

        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[0].length; x++){
                if(matrix[y][x] == '#'){
                    int value = getTrashWeight(queue, matrix, x, y);
                    if(max < value){
                        max = value;
                    }
                }
            }
        }
        return max;
    }
    static int getTrashWeight(Queue<Integer> queue ,char[][] matrix, int inputX, int inputY){
        queue.clear();
        queue.add(getKey(inputY, inputX));
        int count = 1;
        matrix[inputY][inputX] = '.';

        while(!queue.isEmpty()){
            int key = queue.remove();
            int x = key % 1000;
            int y = key / 1000;

            for(int i = 0; i < 4; i++){
                int newX = x + direction.get(i).get(0);
                int newY = y + direction.get(i).get(1);
                if(canGo(newX, newY, matrix) && matrix[newY][newX] == '#'){
                    count++;
                    matrix[newY][newX] = '.';
                    queue.add(getKey(newY, newX));
                }
            }
        }
        return count;
    }
    static boolean canGo(int x, int y, char[][] matrix){
        return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
    }
    static int getKey(int y, int x){
        return 1000 * y + x;
    }
    static char[][] makeMatrix(BufferedReader br, int height, int width, int count) throws IOException {
        char[][] matrix = new char[height][width];
        for (char[] array : matrix) {
            Arrays.fill(array, '.');
        }

        for(int i = 0; i < count; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;

            matrix[y][x] = '#';
        }
        return matrix;
    }
}