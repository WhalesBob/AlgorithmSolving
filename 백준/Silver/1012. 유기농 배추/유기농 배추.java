import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int cabbageGroupCount;
    static List<List<Integer>> direct = List.of(List.of(-1,0),
                                                List.of(0,-1),
                                                List.of(1,0),
                                                List.of(0,1));
    public static void main(String[] args) throws IOException {

        cabbageGroupCount = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            calculate(br);
        }
    }
    static void calculate(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int xLength = Integer.parseInt(st.nextToken());
        int yLength = Integer.parseInt(st.nextToken());
        int cabbageCount = Integer.parseInt(st.nextToken());

        int[][] cabbageGround = makeGround(br,xLength,yLength,cabbageCount);
        searchGround(cabbageGround);
        System.out.println(cabbageGroupCount);
        cabbageGroupCount = 0;
    }
    static int[][] makeGround(BufferedReader br,int xLength, int yLength, int cabbageCount) throws IOException{
        int[][] ground = new int[yLength][xLength];
        for(int i = 0; i < cabbageCount; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ground[y][x] = 1;
        }
        return ground;
    }
    static void searchGround(int[][] cabbageGround){
        for(int y = 0; y < cabbageGround.length; y++){
            for(int x = 0; x < cabbageGround[0].length; x++){
                if(cabbageGround[y][x] == 1){
                    findByDFS(x,y,cabbageGround);
                    cabbageGroupCount++;
                }
            }
        }
    }
    static void findByDFS(int x, int y, int[][] cabbageGround){
        // 처음에 없으면 여기서 해야 한다.

        cabbageGround[y][x] = 0;
        for (List<Integer> forDirection : direct) {
            int moveY = y + forDirection.get(1);
            int moveX = x + forDirection.get(0);
            if (isInBound(moveX, moveY, cabbageGround) && cabbageGround[moveY][moveX] == 1) {
                findByDFS(moveX, moveY, cabbageGround);
            }
        }
    }
    static boolean isInBound(int x, int y, int[][] cabbageGround){
        return (0 <= y && y < cabbageGround.length) && (0 <= x && x < cabbageGround[0].length);
    }
}