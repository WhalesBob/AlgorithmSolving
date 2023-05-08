import java.util.*;

class Solution {
    static int[][] adjacencyMatrix;
    static Queue<Integer> queue;
    public int solution(int n, int[][] wires) {
        adjacencyMatrix = makeAdjacencyMatrix(n, wires);
        queue = new LinkedList<>();
        int min = Integer.MAX_VALUE;
        for(int[] cable : wires){
            int dif = takeDifferenceOfTwo(n, cable);
            if(dif < min){
                min = dif;
            }
        }
        return min;
    }
    static int[][] makeAdjacencyMatrix(int n,int[][] wires){
        int[][] matrix = new int[n+1][n+1];

        for(int[] axis : wires){
            int a = axis[0];
            int b = axis[1];

            matrix[a][b] = matrix[b][a] = 1;
        }
        return matrix;
    }
    static int takeDifferenceOfTwo(int n,int[] cable){
        boolean[] visited = new boolean[n+1];
        int a = cable[0], b = cable[1];
        adjacencyMatrix[a][b] = adjacencyMatrix[b][a] = 0;

        List<Integer> countList = new ArrayList<>();

        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                queue.add(i);
                countList.add(bfs(visited));
            }
        }

        adjacencyMatrix[a][b] = adjacencyMatrix[b][a] = 1;
        return Math.abs(countList.get(1) - countList.get(0));
    }
    static int bfs(boolean[] visited){

        int count = 0;
        while(!queue.isEmpty()){
            count++;
            Integer element = queue.remove();
            visited[element] = true;
            for(int i = 1; i < adjacencyMatrix.length; i++){
                if(adjacencyMatrix[element][i] == 1 && !visited[i]){
                    queue.add(i);
                }
            }
        }
        return count;
    }
}