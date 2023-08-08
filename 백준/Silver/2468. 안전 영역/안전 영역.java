import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> direction = List.of(List.of(1,0), List.of(0,1),
            List.of(-1,0), List.of(0,-1));
    static int maxHeight;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] matrix = makeMatrix(br, n);
        Queue<List<Integer>> queue = new ArrayDeque<>();
        Set<List<Integer>> visitedSet = new HashSet<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < maxHeight; i++) {
            int value = getCountOfLand(queue, visitedSet, matrix, i);
            if (max < value) {
                max = value;
            }
            visitedSet.clear();
        }
        System.out.println(max);
    }

    static int[][] makeMatrix(BufferedReader br, int size) throws IOException {
        maxHeight = Integer.MIN_VALUE;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (maxHeight < matrix[i][j]) {
                    maxHeight = matrix[i][j];
                }
            }
        }
        return matrix;
    }

    static int getCountOfLand(Queue<List<Integer>> queue, Set<List<Integer>> visitedSet, int[][] matrix, int n) {

        int count = 0;
        for(int y = 0; y < matrix.length; y++) {
            for(int x = 0; x < matrix[0].length; x++) {
                if(matrix[y][x] > n && !visitedSet.contains(Arrays.asList(x,y))) {
                    bfs(queue, visitedSet, x, y, matrix, n);
                    count++;
                }
            }
        }
        return count;
    }
    static void bfs(Queue<List<Integer>> queue, Set<List<Integer>> visitedSet, int x, int y, int[][] matrix, int n) {
        queue.add(List.of(x,y));

        while(!queue.isEmpty()) {
            List<Integer> element = queue.remove();
            int elementX = element.get(0);
            int elementY = element.get(1);

            for(int i = 0; i < 4; i++) {
                int nextX = elementX + direction.get(i).get(0);
                int nextY = elementY + direction.get(i).get(1);
                List<Integer> point = List.of(nextX, nextY);
                if(!visitedSet.contains(point) && !queue.contains(point) && canGo(nextX, nextY, matrix) && matrix[nextY][nextX] > n) {
                    queue.add(point);
                    visitedSet.add(point);
                }
            }
        }
    }
    static boolean canGo(int x, int y, int[][] matrix) {
        return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
    }
}