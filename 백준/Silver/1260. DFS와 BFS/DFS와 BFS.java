import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    static int[][] adjacencyMatrix;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int vertexCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        adjacencyMatrix = makeAdjacencyMatrix(vertexCount,edgeCount,br);
        visited = new boolean[adjacencyMatrix.length];
        StringJoiner joiner = new StringJoiner(" ");
        dfs(start, joiner);
        bw.write(joiner + "\n");
        joiner = new StringJoiner(" ");
        bfs(start, joiner);
        bw.write(joiner.toString());
        bw.flush();
    }
    static int[][] makeAdjacencyMatrix(int vertexCount, int edgeCount, BufferedReader br) throws IOException {
        int[][] matrix = new int[vertexCount+1][vertexCount+1];
        for(int i = 0; i < edgeCount; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            matrix[start][end] = matrix[end][start] = 1;
        }
        return matrix;
    }
    static void dfs(int node, StringJoiner joiner){
        joiner.add(Integer.toString(node));
        visited[node] = true;
        for(int i = 1; i < adjacencyMatrix.length; i++){
            if(adjacencyMatrix[node][i] == 1 && !visited[i]){
                dfs(i,joiner);
            }
        }
    }
    static void bfs(int start, StringJoiner joiner){
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[adjacencyMatrix.length];
        queue.add(start);
        visited[start] = true;
        while(!queue.isEmpty()){
            int node = queue.remove();
            joiner.add(Integer.toString(node));
            for(int i = 1; i < adjacencyMatrix.length; i++){
                if(adjacencyMatrix[node][i] == 1 && !visited[i]){
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}