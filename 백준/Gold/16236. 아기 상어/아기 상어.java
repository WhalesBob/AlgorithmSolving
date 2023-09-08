import java.io.*;
import java.util.*;

public class Main {
    static int currentX, currentY;
    static int currentLevel, currentEat;
    static List<List<Integer>> direction = Arrays.asList(Arrays.asList(0,-1),
            Arrays.asList(-1,0), Arrays.asList(1,0), Arrays.asList(0,1));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] matrix = makeMatrix(br,size);

        currentLevel = 2;

        System.out.println(getAnswer(matrix));
    }
    static int getAnswer(int[][] matrix){
        int time = 0;
        Queue<Long> queue = new ArrayDeque<>();
        Set<Long> set = new HashSet<>();

        while(true){
            int foodTime = findFoodTime(matrix, queue, set);
            if(foodTime == -1){
                break;
            }

            time += foodTime;
        }
        return time;
    }
    static int findFoodTime(int[][] matrix, Queue<Long> queue, Set<Long> set){
        queue.clear();
        set.clear();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        queue.add(getKey(currentX, currentY));
        visited[currentY][currentX] = true;
        matrix[currentY][currentX] = 0;

        int time = 0;
        while(!queue.isEmpty()){
            time++;
            int queueSize = queue.size();
            for(int s = 0; s < queueSize; s++) {
                long position = queue.remove();
                int x = (int)(position % 1000);
                int y = (int)(position / 1000);

                for(int i = 0; i < 4; i++){
                    int nextX = x + direction.get(i).get(0);
                    int nextY = y + direction.get(i).get(1);

                    if(canGo(nextX, nextY, matrix)){
                        if(visited[nextY][nextX]){
                            continue;
                        }

                        if(canEat(nextX, nextY, matrix)){
                            set.add(getKey(nextX, nextY));
                        }

                        if(canBypass(nextX, nextY, matrix)){
                            visited[nextY][nextX] = true;
                            queue.add(getKey(nextX, nextY));
                        }
                    }
                }
            }
            if(!set.isEmpty()){
                long element = set.stream().min(Long::compare).get();

                currentY = (int)(element / 1000);
                currentX = (int)(element % 1000);
                currentEat ++;
                matrix[currentY][currentX] = 9;
                if(currentEat == currentLevel){
                    currentLevel++;
                    currentEat = 0;
                }
                return time;
            }
        }

        return -1;
    }
    static boolean canEat(int x, int y, int[][] matrix){
        return 0 < matrix[y][x] && matrix[y][x] < currentLevel;
    }
    static boolean canBypass(int x, int y, int[][] matrix){
        return matrix[y][x] <= currentLevel;
    }
    static long getKey(int x, int y){
        return 1000L * y + x;
    }
    static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
        int[][] matrix = new int[size][size];
        for(int y = 0; y < size; y++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int x = 0; x < size; x++){
                matrix[y][x] = Integer.parseInt(st.nextToken());
                if(matrix[y][x] == 9){
                    currentY = y;
                    currentX = x;
                }
            }
        }
        return matrix;
    }
    static boolean canGo(int x, int y, int[][] matrix){
        return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
    }
}