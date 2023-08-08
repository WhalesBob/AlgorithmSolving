import java.io.*;
import java.util.*;
 
public class Solution {
    static int max, min;
    static final Character[] operation = new Character[] {'+','-','*','/'};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int test_case = 1; test_case <= T; test_case++) {
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
             
            int n = Integer.parseInt(br.readLine());
            List<Integer> charNumList = makeList(br);
            int[] numberArray = makeArray(br, n);
             
            int count = 0;
            for(int i = 0; i < charNumList.size(); i++) {
                count += charNumList.get(i);
            }
             
            duplicatePermutation(numberArray, new ArrayList<>(), charNumList, count);
            System.out.printf("#%d %d\n", test_case, max-min);
        }
    }
    static void duplicatePermutation(int[] numberArray,ArrayList<Character> charList, List<Integer> charNumList, int count) {
        if(count == 0) {
            process(numberArray, charList);
            return;
        }
         
         
        for(int i = 0; i < 4; i++) {
            if(charNumList.get(i) > 0) {
                charList.add(operation[i]);
                charNumList.set(i, charNumList.get(i) - 1);
                duplicatePermutation(numberArray, charList, charNumList, count-1);
                charList.remove(charList.size()-1);
                charNumList.set(i, charNumList.get(i) + 1);
            }
        }
    }
    static List<Integer> makeList(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        List<Integer> list = new ArrayList<>();
         
        for(int i = 0; i < 4; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        return list;
    }
     
    static int[] makeArray(BufferedReader br, int size) throws IOException {
        int[] arr = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }
 
    static void process(int[] numberArray, List<Character> charList) {
        int result = numberArray[0];
        for (int i = 1; i < numberArray.length; i++) {
            char c = charList.get(i-1);
            switch (c) {
                case '+':
                    result += numberArray[i];
                    break;
                case '-':
                    result -= numberArray[i];
                    break;
                case '*':
                    result *= numberArray[i];
                    break;
                case '/':
                    result /= numberArray[i];
                    break;
            }
        }
         
        if(result < min) {
            min = result;
        }
        if(result > max) {
            max = result;
        }
    }
}