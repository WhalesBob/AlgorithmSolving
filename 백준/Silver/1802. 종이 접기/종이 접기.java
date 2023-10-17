import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 0; t < T; t++){
            int[] inputArray = makeArray(br);
            bw.write(canFold(inputArray, 0, inputArray.length - 1) ? "YES\n" : "NO\n");
        }
        
        bw.flush();
    }
    static int[] makeArray(BufferedReader br) throws IOException{
        char[] charArray = br.readLine().trim().toCharArray();
        int[] intArray = new int[charArray.length];
        for(int i = 0; i < intArray.length; i++){
            intArray[i] = charArray[i] - '0';
        }
        return intArray;
    }
    static boolean canFold(int[] array, int start, int end){
        if(start == end){
            return true;
        }

        int mid = (start + end) / 2;
        for(int s = 0; s < mid; s++){
            int e = (end - s);
            if(array[s] == array[e]){
                return false;
            }
        }

        return canFold(array, start, mid - 1);
    }
}