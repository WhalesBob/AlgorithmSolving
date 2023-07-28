import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        List<Node> nodeList = new ArrayList<>();
        List<Integer> numberList = makeList(br,n);

        for(Integer element : numberList){
            Node node = nodeList.stream().filter(x -> Math.abs(x.queue.peek() - element) < l).findFirst().orElse(null);
            if(node == null){
                nodeList.add(new Node(element));
            }else{
                node.queue.add(element);
            }
        }
        System.out.println(nodeList.size());
    }
    static List<Integer> makeList(BufferedReader br, int n) throws IOException{
        List<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < n; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }
        return list.stream().sorted().collect(Collectors.toList());
    }
    static class Node{
        int data;
        PriorityQueue<Integer> queue;

        public Node(int data){
            this.data = data;
            this.queue = new PriorityQueue<>();
            this.queue.add(data);
        }
    }
}