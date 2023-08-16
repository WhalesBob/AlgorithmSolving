import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] rice = makeArray(br,n);

        Map<Integer, Integer> riceMap = new HashMap<>();
        for(int i = 0; i < k; i++){
            if(riceMap.containsKey(rice[i])){
                riceMap.replace(rice[i], riceMap.get(rice[i]) + 1);
            }else{
                riceMap.put(rice[i], 1);
            }
        }
        int max = riceMap.size();
        for(int i = 0; i < rice.length; i++){
            int add = (k + i) % rice.length;

            riceMap.replace(rice[i], riceMap.get(rice[i]) - 1);
            if(riceMap.get(rice[i]) == 0){
                riceMap.remove(rice[i]);
            }

            if(riceMap.containsKey(rice[add])){
                riceMap.replace(rice[add], riceMap.get(rice[add]) + 1);
            }else{
                riceMap.put(rice[add], 1);
            }

            int currentHave = riceMap.size();
            if(!riceMap.containsKey(c)){
                currentHave++;
            }

            if(max < currentHave){
                max = currentHave;
            }
        }
        System.out.println(max);
    }
    static int[] makeArray(BufferedReader br, int size) throws IOException{
        int[] array = new int[size];
        for(int i = 0; i < size; i++){
            array[i] = Integer.parseInt(br.readLine());
        }
        return array;
    }
}