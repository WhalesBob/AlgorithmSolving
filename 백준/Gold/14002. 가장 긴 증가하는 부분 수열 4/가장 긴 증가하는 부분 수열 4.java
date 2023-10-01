import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> maxUpdatedList;
     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[] array = makeArray(br, n);
        maxUpdatedList = new ArrayList<>();

        updateLargestSubArray(array);
        bw.write(maxUpdatedList.size() + "\n");
        List<Integer> resultList = maxUpdatedList.get(maxUpdatedList.size() - 1);
        for(Integer r : resultList){
            bw.write(r + " ");
        }
        bw.flush();
    }
    static void updateLargestSubArray(int[] array) {
        int[] smallValueArray = new int[array.length];
        smallValueArray[0] = array[0];
        maxUpdatedList.add(new ArrayList<>(Arrays.asList(array[0])));

        for(int i = 1; i < array.length; i++) {
            int targetIndex = getBinarySearch(smallValueArray, array[i]);

            if(targetIndex == -1) {
                continue;
            }
            smallValueArray[targetIndex] = array[i];

            if(maxUpdatedList.size() <= targetIndex){
                List<Integer> list = maxUpdatedList.get(targetIndex - 1);
                list.add(array[i]);
                maxUpdatedList.add(list);
            }else{
                List<Integer> list;

                if(targetIndex == 0){
                    list = new ArrayList<>();
                }else if(maxUpdatedList.get(targetIndex - 1).size() <= targetIndex){
                    list = maxUpdatedList.get(targetIndex - 1);
                }else{
                    list = new ArrayList<>(maxUpdatedList.get(targetIndex - 1).subList(0, targetIndex));
                }
                list.add(array[i]);
                maxUpdatedList.set(targetIndex, list);
            }
        }
    }
    static int[] makeArray(BufferedReader br, int size) throws IOException{
        int[] array = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        for(int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        return array;
    }
    static int getBinarySearch(int[] smallValueArray, int value) {
        int left = 0, right = maxUpdatedList.size()-1;
        if(smallValueArray[right] < value) {
            return right + 1;
        }

        int answer = right;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(isAvailable(smallValueArray, mid, value)) {
                answer = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return smallValueArray[answer] == value ? -1 : answer;
    }
    static boolean isAvailable(int[] array, int targetIndex, int value) {
        return array[targetIndex] >= value;
    }
}