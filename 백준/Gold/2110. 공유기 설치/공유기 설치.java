import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        List<Integer> inputList = makeList(br, n);
        System.out.println(getMaxAdjacencyDistance(inputList, c));
    }
    static List<Integer> makeList(BufferedReader br, int size) throws IOException{
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < size; i++){
            list.add(Integer.parseInt(br.readLine()));
        }
        return list.stream().sorted().collect(Collectors.toList());
    }
    static int getMaxAdjacencyDistance(List<Integer> inputList, int numberOfDevice){
        int left = 1;
        int right = inputList.get(inputList.size() -1) - inputList.get(0);
        int answer = left;
        while(left <= right){
            int mid = (left + right) / 2;
            if(isAvailable(inputList, numberOfDevice, mid)){
                answer = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return answer;
    }
    static boolean isAvailable(List<Integer> inputList, int numberOfDevice, int distance){
        int count = 1;
        int currentDevice = inputList.get(0);
        for(int i = 1; i < inputList.size(); i++){
            if(inputList.get(i) - currentDevice >= distance){
                count++;
                currentDevice = inputList.get(i);
            }
        }
        return count >= numberOfDevice;
    }
}