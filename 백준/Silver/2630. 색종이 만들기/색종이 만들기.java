import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int whitePaperCount;
    static int bluePaperCount;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = makeMap(br,N);
        whitePaperCount = 0;
        bluePaperCount = 0;

        checkPaper(0,0,N);
        System.out.println(whitePaperCount);
        System.out.println(bluePaperCount);
    }
    static int[][] makeMap(BufferedReader br, int size) throws IOException{
        int[][] map = new int[size][size];
        for(int i = 0; i < size; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j < size; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return map;
    }
    static void checkPaper(int startX, int startY, int paperSize){
        int paper = checkAllSamePaper(startX,startY,paperSize);
        if(paper == 0){
            whitePaperCount++;
            return;
        }

        if(paper == 1){
            bluePaperCount++;
            return;
        }

        int halfSize = paperSize / 2;
        checkPaper(startX, startY, halfSize);
        checkPaper(startX + halfSize, startY, halfSize);
        checkPaper(startX, startY + halfSize, halfSize);
        checkPaper(startX + halfSize, startY + halfSize, halfSize);
    }
    static int checkAllSamePaper(int startX, int startY, int paperSize){
        int firstPaper = map[startY][startX];
        for(int y = startY; y < startY + paperSize; y++){
            for(int x = startX; x < startX + paperSize; x++){
                if(map[y][x] != firstPaper){
                    return 2;
                }
            }
        }
        return firstPaper;
    }
}