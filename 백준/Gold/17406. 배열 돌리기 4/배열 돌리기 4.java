import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static Node[] nodeArray;
    static int allMin;
    static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0),
            Arrays.asList(0,1), Arrays.asList(-1,0), Arrays.asList(0,-1));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());

        int[][] matrix = makeMatrix(br, height, width);
        nodeArray = new Node[count];
        for(int i = 0; i < count; i++){
            st = new StringTokenizer(br.readLine()," ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            nodeArray[i] = new Node(r,c,s);
        }
        allMin = Integer.MAX_VALUE;
        Integer[] indexArray = Stream.iterate(0, a -> a + 1).limit(nodeArray.length).toArray(Integer[]::new);
        permutation(indexArray,matrix,0,indexArray.length, indexArray.length);

        System.out.println(allMin);
    }
    static void permutation(Integer[] arr, int[][] matrix, int depth, int n, int r){
        if(depth == r){
            int value = getRoundMinimum(arr, cloneMatrix(matrix));
            if(allMin > value){
                allMin = value;
            }
            return;
        }

        for(int i = depth; i < n; i++){
            swap(arr, depth, i);
            permutation(arr, matrix,depth + 1, n, r);
            swap(arr, depth, i);
        }
    }
    static void swap(Integer[] arr, int depth, int i){
        Integer temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }
    static int getRoundMinimum(Integer[] arr, int[][] matrix){
        for(Integer i : arr){
            Node node = nodeArray[i];
            roundClockWise(matrix, node.r, node.c, node.s);
        }

        int min = Integer.MAX_VALUE;
        for(int y = 0; y < matrix.length; y++){
            int sum = 0;
            for(int x = 0; x < matrix[0].length; x++){
                sum += matrix[y][x];
            }

            if(sum < min){
                min = sum;
            }
        }
        return min;
    }
    static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
        int[][] matrix = new int[height][width];
        for(int y = 0; y < matrix.length; y++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int x = 0; x < matrix[0].length; x++){
                matrix[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        return matrix;
    }
    static void roundClockWise(int[][] matrix, int r, int c, int s){
        for(int i = s; i >=0 ; i--){
            changeOneLayer(matrix, c-i-1, r-i-1, c+i-1, r+i-1);
        }
    }
    static void changeOneLayer(int[][] matrix, int startX, int startY, int endX, int endY){
        Queue<Integer> queue = new ArrayDeque<>();
        int index = 0;
        int x = startX, y = startY;
        queue.add(matrix[startY][startX]);

        while(index < 4){

            int nextX = x + direction.get(index).get(0);
            int nextY = y + direction.get(index).get(1);
            if(!isInBound(nextX, nextY, startX, startY, endX, endY)){
                index++;
                continue;
            }

            queue.add(matrix[nextY][nextX]);
            matrix[nextY][nextX] = queue.remove();
            x = nextX; y = nextY;
        }
    }
    static boolean isInBound(int x, int y, int startX, int startY, int endX, int endY){
        return (startX <= x && x <= endX) && (startY <= y && y <= endY);
    }
    static int[][] cloneMatrix(int[][] matrix){
        int[][] newMatrix = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix[0].length);
        }
        return newMatrix;
    }
    static class Node{
        int r;
        int c;
        int s;

        public Node(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}