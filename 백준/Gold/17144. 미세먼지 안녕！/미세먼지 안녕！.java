import java.io.*;
import java.util.*;

public class Main {
    static List<Point> conditioner;
    static Queue<Integer> queue;
    static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0),
            Arrays.asList(0,1), Arrays.asList(-1,0), Arrays.asList(0,-1));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        conditioner = new ArrayList<>();
        queue = new ArrayDeque<>();

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int time = Integer.parseInt(st.nextToken());

        int[][] matrix = makeMatrix(br,height, width);

        for(int i = 0; i < time; i++){
            spread(matrix);
            Point up = conditioner.get(0);
            Point down = conditioner.get(1);
            rotate(matrix, 0,0, matrix[0].length - 1, up.y, true);
            rotate(matrix, 0, down.y, matrix[0].length - 1, matrix.length - 1, false);
        }

        int count = 0;
        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[0].length; x++){
                if(matrix[y][x] != -1){
                    count += matrix[y][x];
                }
            }
        }
        System.out.println(count);
    }
    static void rotate(int[][] matrix, int startX, int startY, int endX, int endY, boolean up){
        queue.clear();
        int dirIndex = 0;
        int x = startX + 1,y = up ? endY : startY;
        int addIndex = up ? -1 : 1;
        queue.add(matrix[y][x]);
        matrix[y][x] = 0;

        while(true){
            int newX = x + direction.get(dirIndex).get(0);
            int newY = y + direction.get(dirIndex).get(1);

            if(inBound(newX, newY, startX, startY, endX, endY)){
                if(matrix[newY][newX] == -1){
                    break;
                }
                queue.add(matrix[newY][newX]);
                matrix[newY][newX] = queue.remove();
                x = newX; y = newY;
            }
            else {
                dirIndex = (dirIndex + addIndex + 4) % 4;
            }
        }
    }
    static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
        int[][] matrix = new int[height][width];
        for(int y = 0; y < height; y++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < width; x++){
                matrix[y][x] = Integer.parseInt(st.nextToken());
                if(matrix[y][x] == -1){
                    conditioner.add(new Point(x,y));
                }
            }
        }
        return matrix;
    }
    static void spread(int[][] matrix){
        int[][] spreadMatrix = new int[matrix.length][matrix[0].length];

        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[0].length; x++){
                if(matrix[y][x] > 0){
                    spreadOne(matrix, spreadMatrix, x,y);
                }
            }
        }

        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[0].length; x++){
                matrix[y][x] += spreadMatrix[y][x];
            }
        }
    }
    static void spreadOne(int[][] matrix, int[][] resultMatrix, int x, int y){
        int origin = matrix[y][x];
        for(int i = 0; i < 4; i++){
            int newX = x + direction.get(i).get(0);
            int newY = y + direction.get(i).get(1);
            if(canGo(newX, newY, matrix) && matrix[newY][newX] != -1){
                int value = origin / 5;
                resultMatrix[newY][newX] += value;
                matrix[y][x] -= value;
            }
        }
    }
    static boolean canGo(int x, int y, int[][] matrix){
        return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
    }
    static boolean inBound(int x, int y, int startX, int startY, int endX, int endY){
        return (startX <= x && x <= endX) && (startY <= y && y <= endY);
    }
    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}