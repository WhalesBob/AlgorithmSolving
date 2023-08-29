import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] RGBTable = makeRGBTable(br,n);
        System.out.println(getMinimumValue(RGBTable,n));
    }
    static int[][] makeRGBTable(BufferedReader br, int size) throws IOException{
        int[][] RGBTable = new int[size][3];

        for(int i = 0; i < size; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            RGBTable[i][0] = Integer.parseInt(st.nextToken());
            RGBTable[i][1] = Integer.parseInt(st.nextToken());
            RGBTable[i][2] = Integer.parseInt(st.nextToken());
        }
        return RGBTable;
    }
    static int getMinimumValue(int[][] RGBTable, int size){
        int[][] minTable = new int[size][3];
        System.arraycopy(RGBTable[0], 0, minTable[0], 0, 3);

        for(int i = 1; i < size; i++){
            for(int j = 0; j <= 2; j++){
                int compareA = (j+1) % 3;
                int compareB = (j+2) % 3;
                minTable[i][j] = Math.min(minTable[i-1][compareA], minTable[i-1][compareB]) + RGBTable[i][j];
            }
        }
        return getMinimum(minTable[size-1][0],minTable[size-1][1],minTable[size-1][2]);
    }
    static int getMinimum(int a, int b, int c){
        if(a < b && a < c){
            return a;
        }else{
            return Math.min(b, c);
        }
    }
}