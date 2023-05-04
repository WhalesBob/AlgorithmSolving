import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<List<Integer>> direction = List.of(List.of(-1,0),List.of(0,-1),List.of(1,0),List.of(0,1));
    static int startX,startY;
    static boolean finalExist = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        char[][] map = getMap(br, width, height);

        for(int h = 0; h < height; h++){
            for(int w = 0; w < width; w++){
                startY = h; startX = w;
                isCircleExistOnMapByDFS(map, h, w, 0, new boolean[height][width]);
                if(finalExist){
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
    }
    static char[][] getMap(BufferedReader br, int width, int height) throws IOException {
        char[][] map = new char[height][width];
        for(int h = 0; h < height; h++){
            String oneLine = br.readLine().trim();
            for(int w = 0; w < width; w++){
                map[h][w] = oneLine.charAt(w);
            }
        }
        return map;
    }
    static void isCircleExistOnMapByDFS(char[][] map, int y, int x, int count, boolean[][] visited){
        if(x == startX && y == startY && count >= 4){
            finalExist = true;
        }
        char getSymbol = map[y][x];
        if(count != 0){
            visited[y][x] = true;
        }

        for(int i = 0; i < 4; i++){
            int nextX = x + direction.get(i).get(0);
            int nextY = y + direction.get(i).get(1);
            if(!finalExist && canGo(map, getSymbol, nextY, nextX,visited) && initBoolean(count,nextY,nextX)){
                isCircleExistOnMapByDFS(map, nextY, nextX, count+1, visited);
            }
        }
    }
    static boolean canGo(char[][] map,char getSymbol, int nextY, int nextX, boolean[][] visited){
        boolean canPhysicallyGo = (0 <= nextX && nextX < map[0].length) && (0 <= nextY && nextY < map.length);
        if(!canPhysicallyGo){
            return false;
        }

        return (map[nextY][nextX] == getSymbol) && !visited[nextY][nextX];
    }
    static boolean initBoolean(int count, int nextY, int nextX){
        return count != 1 || nextY != startY || nextX != startX;
    }
}