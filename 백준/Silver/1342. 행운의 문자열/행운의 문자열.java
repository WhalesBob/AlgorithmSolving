import java.util.*;

public class Main {
    static Set<Long> haveSet;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] input = sc.next().toCharArray();
        int[] convert = new int[input.length];
        Map<Character, Integer> map = new HashMap<>();
        int value = 1;
        haveSet = new HashSet<>();

        for(int i = 0; i < input.length; i++){
            if(!map.containsKey(input[i])){
                map.put(input[i], value++);
            }

            convert[i] = map.get(input[i]);
        }

        if(value == 11){
            System.out.println(3_628_800);
            return;
        }

        permutation(convert, 0, convert.length, convert.length);
        System.out.println(haveSet.size());
    }
    static long convertToLong(int[] arr){
        long sum = 0;

        for(int i = arr.length - 1; i >= 0; i--){
            sum += (arr[i] * Math.pow(10, arr.length - i));
        }
        return sum;
    }
    static void permutation(int[] arr, int depth, int n, int r){
        if(depth == r){
            if(isLuckString(arr)){
                long value = convertToLong(arr);
                haveSet.add(value);
            }

            return;
        }

        for(int i = depth; i < n; i++){
            swap(arr, depth, i);
            permutation(arr, depth + 1, n, r);
            swap(arr, depth, i);
        }
    }
    static boolean isLuckString(int[] arr){
        for(int i = 1; i < arr.length - 1; i++){
            if(arr[i-1] == arr[i] || arr[i] == arr[i+1]){
                return false;
            }
        }
        return true;
    }
    static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}