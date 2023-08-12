import java.io.*;
import java.util.*;

public class Main {
    static int min;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            char[] charArray = br.readLine().trim().toCharArray();
            int k = Integer.parseInt(br.readLine());
            Map<Character, Node> charIndexMap = makeCharMap(charArray, k);

            if(charIndexMap.isEmpty()){
                System.out.println(-1);
                continue;
            }

            getMinimumLength(charIndexMap, k);
            System.out.printf("%d %d\n", min, max);
        }
    }
    static void getMinimumLength(Map<Character, Node> map, int k){
        for(char c : map.keySet()){
            List<Integer> list = map.get(c).indexList;
            for(int i = 0; i < list.size() - k + 1; i++){
                int length = list.get(i + k - 1)- list.get(i) + 1;
                if(min > length){
                    min = length;
                }
                if(max < length){
                    max = length;
                }
            }
        }
    }

    static Map<Character,Node> makeCharMap(char[] charArray, int k){
        Map<Character, Node> map = new HashMap<>();

        for(int i = 0; i < charArray.length; i++){
            char c = charArray[i];
            if(map.containsKey(c)){
                map.get(c).add(i);
            }else{
                map.put(c, new Node(c, i));
            }
        }
        Set<Character> deleteSet = new HashSet<>();
        for(Character c : map.keySet()){
            if(map.get(c).count < k){
                deleteSet.add(c);
            }
        }

        for(Character c : deleteSet){
            map.remove(c);
        }
        return map;
    }
    static class Node{
        char data;
        int count;
        List<Integer> indexList;

        public Node(char data, int index) {
            this.data = data;
            this.count = 1;
            this.indexList = new ArrayList<>();
            this.indexList.add(index);
        }

        public void add(int index){
            this.count++;
            this.indexList.add(index);
        }
    }
}