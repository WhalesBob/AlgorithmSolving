import java.io.*;
import java.util.*;

public class Main {
    static int[] root;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        root = new int[n+1];
        for(int i = 1; i < root.length; i++){
            root[i] = i;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            queue.add(new Node(start, end, value));
        }


        int sum = 0;
        while(!queue.isEmpty() && !isAllSameRoot()) {
            Node node = queue.remove();

            if(find(node.start) != find(node.end)){
                sum += node.value;
                union(node.start, node.end);
            }
        }
        System.out.println(sum);
    }
    static boolean isAllSameRoot(){
        for(int i = 2; i < root.length; i++){
            if(root[i] != root[i-1]){
                return false;
            }
        }
        return true;
    }
    static int find(int a){
        if(root[a] == a){
            return a;
        }

        root[a] = find(root[a]);
        return root[a];
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        root[y] = x;
    }

    static class Node implements Comparable<Node>{
        int start;
        int end;
        int value;

        public Node(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }
}