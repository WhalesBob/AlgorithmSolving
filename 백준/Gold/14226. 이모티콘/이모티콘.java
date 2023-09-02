import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Queue<Node> queue = new ArrayDeque<>();

        Node[] array = new Node[2 * n + 1];
        array[1] = new Node(1,0,0);
        queue.add(array[1]);

        while(!queue.isEmpty()){
            Node node = queue.remove();

            if(compare(array, node, node.data, 2)){
                array[node.data * 2] = makeMinNode(node.data * 2, node.time + 2, node.data);
                queue.add(array[node.data * 2]);
            }

            boolean have = compare(array, node, node.have, 1);
            for(int i = 1; (node.data + node.have * i) < array.length && node.data != 1; i++){
                if(compare(array, node, node.have * i, i)){
                    array[node.data + node.have * i] = makeMinNode(node.data + node.have * i, node.time + i, node.have);
                }
            }

            if(have){
                queue.add(array[node.data + node.have]);
            }

            if(compare(array, node, -1, 1)){
                array[node.data - 1] = makeMinNode(node.data - 1, node.time + 1, node.have);
                queue.add(array[node.data - 1]);
            }
        }

        System.out.println(array[n].time);
    }
    static Node makeMinNode(int data, int time, int have){
        if(data < time){
            time = data; have = 1;
        }
        return new Node(data, time, have);
    }
    static boolean compare(Node[] array, Node current, int diffInArray, int expectedDiff){
        if(1 < current.data + diffInArray && current.data + diffInArray < array.length){
            return array[current.data + diffInArray ] == null || array[current.data + diffInArray].time >= current.time + expectedDiff;
        }
        return false;
    }
    static class Node{
        int data;
        int time;
        int have;

        public Node(int data, int time, int have) {
            this.data = data;
            this.time = time;
            this.have = have;
        }
    }
}