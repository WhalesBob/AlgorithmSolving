import java.io.*;
import java.util.*;

public class Main {
    static List<Point> chickenList;
    static List<Point> houseList;
    static int min;
    static final int INF = 999_999_999;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        chickenList = new ArrayList<>();
        houseList = new ArrayList<>();
        min = Integer.MAX_VALUE;

        int[][] matrix = makeMatrix(br, n);
        combination(new boolean[chickenList.size()], 0, chickenList.size(), m );
        System.out.println(min);
    }

    static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
        int[][] matrix = new int[size][size];
        for(int y = 0; y < size; y++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int x = 0; x < size; x++){
                matrix[y][x] = Integer.parseInt(st.nextToken());
                if(matrix[y][x] == 2){
                    chickenList.add(new Point(x,y));
                }
                if(matrix[y][x] == 1){
                    houseList.add(new Point(x,y));
                }
            }
        }
        return matrix;
    }
    static void combination(boolean[] visited, int start, int n, int r){
        if(r == 0){
            int value = getChickenLength(visited);
            if(min > value){
                min = value;
            }
            return;
        }

        for(int i = start; i < n; i++){
            visited[i] = true;
            combination(visited, i+1, n, r-1);
            visited[i] = false;
        }
    }
    static int getChickenLength(boolean[] visited){
        int[] lengthArray = new int[houseList.size()];
        Arrays.fill(lengthArray, INF);

        for(int i = 0; i < chickenList.size(); i++){
            if(visited[i]){
                Point c = chickenList.get(i);
                for(int h = 0; h < houseList.size(); h++){
                    Point house = houseList.get(h);
                    int length = Math.abs(c.x - house.x) + Math.abs(c.y - house.y);
                    if(lengthArray[h] > length){
                        lengthArray[h] = length;
                    }
                }
            }
        }

        return Arrays.stream(lengthArray).sum();
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