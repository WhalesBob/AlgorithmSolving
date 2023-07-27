import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Node> nodeList = new ArrayList<>();
        while(true){
            String input = br.readLine();
            if(input == null || input.equals("")){
                break;
            }

            String sortedString = sortString(input);
            Node findNode = nodeList.stream().filter(x -> x.sortedStr.equals(sortedString)).findFirst().orElse(null);
            if(findNode != null){
                findNode.stringQueue.add(input);
                findNode.size++;
            }else {
                Node newNode = new Node(sortedString, input);
                nodeList.add(newNode);
            }
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(nodeList);

        for(int i = 0; i < 5 && !queue.isEmpty(); i++) {
            Node picked = queue.poll();
            picked.printNode();
        }
    }
    static String sortString(String input){
        List<Character> list = new ArrayList<>();
        for(int i = 0; i < input.length(); i++){
            list.add(input.charAt(i));
        }

        StringBuilder builder = new StringBuilder();
        list.stream().sorted().forEach(builder::append);
        return builder.toString();
    }

    static class Node implements Comparable<Node>{
        String sortedStr;
        PriorityQueue<String> stringQueue;
        int size;

        public Node(String sortedStr, String inputStr) {
            this.sortedStr = sortedStr;
            this.stringQueue = new PriorityQueue<>();
            this.stringQueue.add(inputStr);
            this.size = 1;
        }

        @Override
        public int compareTo(Node o) {
            if(this.size == o.size){
                assert this.stringQueue.peek() != null;
                assert o.stringQueue.peek() != null;
                return this.stringQueue.peek().compareTo(o.stringQueue.peek());
            }
            return Integer.compare(o.size, this.size);
        }

        public void printNode(){
            StringBuilder builder = new StringBuilder();
            Set<String> set = new HashSet<>();
            while(!this.stringQueue.isEmpty()){
                String s = this.stringQueue.poll();
                if(!set.contains(s)){
                    builder.append(s).append(" ");
                }
                set.add(s);
            }
            builder.append(".");
            System.out.printf("Group of size %d: %s\n", this.size, builder);
        }
    }
}