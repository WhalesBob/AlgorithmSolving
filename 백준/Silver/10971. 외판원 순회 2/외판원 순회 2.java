import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] adjacencyMatrix;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        min = 999_999_999;
        adjacencyMatrix = makeAdjacencyMatrix(br,n);

        for(int start = 1; start < adjacencyMatrix.length; start++){
            makeMinimumTSP(start,new boolean[adjacencyMatrix.length],start,0);
        }
        System.out.println(min);
    }
    static int[][] makeAdjacencyMatrix(BufferedReader br, int n) throws IOException {
        int[][] matrix = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j = 1; j <= n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return matrix;
    }
    static void makeMinimumTSP(int start ,boolean[] visited, int current, int weight){
        visited[current] = true;
        if(isAllVisited(visited)){
            if(adjacencyMatrix[current][start] != 0){
                min = Math.min(weight + adjacencyMatrix[current][start], min);
            }
            return;
        }

        if(weight >= min){
            return;
        }

        for(int toVisit = 1; toVisit < visited.length; toVisit++){
            boolean isNotCurrent = (toVisit != current);
            boolean notVisit = !visited[toVisit];
            boolean canGo = (adjacencyMatrix[current][toVisit] != 0);

            if(isNotCurrent && notVisit && canGo){
                int newWeight = weight + adjacencyMatrix[current][toVisit];
                makeMinimumTSP(start, makeBooleanClone(visited), toVisit, newWeight);
            }
        }

    }
    static boolean isAllVisited(boolean[] visited){
        for(int i = 1; i < visited.length; i++){
            if(!visited[i]){
                return false;
            }
        }
        return true;
    }
    static boolean[] makeBooleanClone(boolean[] visited){
        boolean[] result = new boolean[visited.length];
        System.arraycopy(visited, 1, result, 1, result.length - 1);
        return result;
    }
}