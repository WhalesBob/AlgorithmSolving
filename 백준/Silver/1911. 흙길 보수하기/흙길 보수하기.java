import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int waterCount = Integer.parseInt(st.nextToken());
        int woodLength = Integer.parseInt(st.nextToken());
        List<Node> waters = makeList(br, waterCount);
        System.out.println(minimumLength(waters, woodLength));
    }
    static int minimumLength(List<Node> waters, int woodLength){

        int count = 0;
        int currentIndex = waters.get(0).start;

        for(int i = 0; i < waters.size(); i++){
            currentIndex = Math.max(currentIndex, waters.get(i).start);
            int value = (int)Math.ceil((waters.get(i).end - currentIndex) / (double)woodLength);
            count += value;
            currentIndex += woodLength * value;
        }

        return count;

    }

    static List<Node> makeList(BufferedReader br, int size) throws IOException{
        List<Node> list = new ArrayList<>();
        for(int i = 0; i < size; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Node(start, end));
        }

        list.sort(Comparator.comparingInt(a -> a.start));
        return list;
    }
    static class Node{
        int start;
        int end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}