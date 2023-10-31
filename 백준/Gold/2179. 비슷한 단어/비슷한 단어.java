import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Trie trie = new Trie();
        int max = -1;

        List<String> inputList = new ArrayList<>();
        for(int i = 0; i < n; i++){
            inputList.add(br.readLine().trim());
        }

        trie.insert(inputList.get(0));
        String before = "";
        String after = "";

        for(int i = 1; i < inputList.size(); i++){
            String input = inputList.get(i);
            int result = trie.insert(input);
            if(max < result){
                max = result;
                after = input;
                before = trie.getBeforeWord(max, after);
            }else if(max == result){
                String compare = trie.getBeforeWord(result, input);
                if(inputList.indexOf(compare) < inputList.indexOf(before)){
                    before = compare;
                    after = input;
                }
            }
        }

        System.out.printf("%s\n%s\n", before, after);

    }
    static class Node{
        LinkedHashMap<Character, Node> childNode = new LinkedHashMap<>();
    }

    static class Trie{
        Node rootNode = new Node();
        StringBuilder builder = new StringBuilder();

        int insert(String str){

            Node node = this.rootNode;
            int count = 0;

            for(int i = 0; i < str.length(); i++){
                while(i < str.length() && node.childNode.containsKey(str.charAt(i))){
                    count++;
                    node = node.childNode.get(str.charAt(i++));
                }

                if(i >= str.length()){
                    break;
                }
                node.childNode.put(str.charAt(i), new Node());
                node = node.childNode.get(str.charAt(i));

            }
            node.childNode.put('\0', new Node());
            return count;
        }

        String getBeforeWord(int n, String after){
            builder.delete(0, builder.length());
            Node node = this.rootNode;

            for(int i = 0; i < n; i++){
                node = node.childNode.get(after.charAt(i));
                builder.append(after.charAt(i));
            }

            for(Node child=node; child.childNode.size() != 0; child = child.childNode.get(findFirst(child.childNode))){
                Character firstKey = findFirst(child.childNode);
                if(firstKey == '\0'){
                    break;
                }
                builder.append(firstKey);
            }

            return builder.toString();
        }
        static Character findFirst(LinkedHashMap<Character, Node> map){
            return map.keySet().stream().findFirst().orElse(null);
        }
    }
}