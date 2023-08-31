import java.io.*;
import java.util.*;

public class Main {
    static int min;
    static int[][] matrix;
    static int[] people;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        min = Integer.MAX_VALUE;
        int size = Integer.parseInt(br.readLine());
        people = new int[size];

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < size; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        matrix = makeMatrix(br, size);

        for(int r = 0; r < size; r++) {
            combination(new boolean[size], 0, size, r);
        }

        System.out.print(min == Integer.MAX_VALUE ? -1 : min);
    }
    static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
        int[][] matrix = new int[size][size];
        for(int y = 0; y < size; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int count = Integer.parseInt(st.nextToken());
            for(int i = 0; i < count; i++){
                int x = Integer.parseInt(st.nextToken()) - 1;
                matrix[y][x] = 1;
            }
        }
        return matrix;
    }
    static void combination(boolean[] visited, int start, int n, int r) {
        if(r == 0) {
            if(isConnected(visited)) {
                int value = getMinimum(visited);
                if(min > value) {
                    min = value;
                }
            }

            return;
        }

        for(int i = start; i < n; i++) {
            visited[i] = true;
            combination(visited, i + 1, n, r-1);
            visited[i] = false;
        }
    }
    static int getMinimum(boolean[] visited) {
        int trueCount = 0, falseCount = 0;
        for(int i = 0; i < visited.length; i++) {
            if(visited[i]) {
                trueCount += people[i];
            }else {
                falseCount += people[i];
            }
        }

        return Math.abs(trueCount - falseCount);
    }
    static boolean isConnected(boolean[] visited) {
        int trueCount = 0, falseCount = 0;
        for(int i = 0; i < visited.length; i++) {
            if(visited[i]) {
                trueCount++;
            }else {
                falseCount++;
            }
        }
        Queue<Integer> queue = new ArrayDeque<>();

        int trueStart = -1, falseStart = -1;
        for(int i = 0; i < visited.length && (trueStart == -1 || falseStart == -1); i++) {
            if(trueStart == -1 && visited[i]) {
                trueStart = i;
            }
            if(falseStart == -1 && !visited[i]) {
                falseStart = i;
            }
        }

        boolean[] counted = new boolean[visited.length];

        return (trueStart == -1 || isSame(trueCount, trueStart, visited, counted, queue, true))
                && (falseStart == -1 || isSame(falseCount, falseStart, visited, counted, queue, false));
    }
    static boolean isSame(int targetCount, int start, boolean[] visited, boolean[] counted,Queue<Integer> queue, boolean target) {
        queue.clear();
        counted[start] = true;
        queue.add(start);
        int count = 1;

        while(!queue.isEmpty()) {
            int element = queue.remove();
            for(int i = 0; i < matrix.length; i++) {
                if(!counted[i] && matrix[element][i] == 1 && visited[i] == target) {
                    counted[i] = true;
                    count++;
                    queue.add(i);
                }
            }
        }

        return count == targetCount;
    }
}