import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static Character oneOddKey;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Character, Integer> charMap = makeCharMap(sc);
        oneOddKey = null;
        if(haveOnlyOneOrNoOdd(charMap)){
            System.out.println(makePalindrome(charMap));
        }else{
            System.out.println("I'm Sorry Hansoo");
        }
    }
    static Map<Character, Integer> makeCharMap(Scanner sc){
        char[] inputArray = sc.next().trim().toCharArray();
        Map<Character, Integer> charMap = new HashMap<>();
        for(char c : inputArray){
            try{
                Integer value = charMap.get(c);
                charMap.replace(c, value + 1);
            }catch(NullPointerException e){
                charMap.put(c, 1);
            }
        }
        return charMap;
    }
    static boolean haveOnlyOneOrNoOdd(Map<Character, Integer> map){
        int oddCount = 0;
        for(Character key : map.keySet()){
            if(map.get(key) % 2 == 1){
                oneOddKey = key;
                oddCount++;
            }
        }
        return oddCount <= 1;
    }
    static String makePalindrome(Map<Character, Integer> map){
        ArrayDeque<Character> deque = new ArrayDeque<>();
        if(oneOddKey != null){
            deque.addLast(oneOddKey);
            int value = map.get(oneOddKey);
            map.replace(oneOddKey, value - 1);
        }

        List<Character> sortedKey = map.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        for(Character c : sortedKey){
            int value = map.get(c);
            for(int i = 0; i < value / 2; i++){
                deque.addFirst(c);
                deque.addLast(c);
            }
        }
        StringBuilder builder = new StringBuilder();
        deque.forEach(builder::append);
        return builder.toString();
    }
}