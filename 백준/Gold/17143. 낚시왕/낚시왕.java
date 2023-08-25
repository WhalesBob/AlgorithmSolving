import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> startPoint;
    static Set<Shark> sharkSet;
    static Shark[][] sharkMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int sharkCount = Integer.parseInt(st.nextToken());

        sharkSet = new HashSet<>();
        sharkMap = makeMatrix(br, height, width, sharkCount);
        startPoint = Arrays.asList(sharkMap.length - 1, 0, 0, sharkMap[0].length - 1);

        int total = 0;
        for(int person = 0; person < width; person++) {
            total += catchShark(person);
            moveShark();
        }

        System.out.println(total);
    }
    static void moveShark() {
        Set<Shark> removeSet = new HashSet<>();
        for(Shark s : sharkSet) {
            updateOneShark(s, sharkMap);
        }
        int height = sharkMap.length;
        int width = sharkMap[0].length;
        sharkMap = new Shark[height][width];

        for(Shark s : sharkSet) {
            int y = s.currentY;
            int x = s.currentX;

            if(sharkMap[y][x] == null){
                sharkMap[y][x] = s;
            }else if(sharkMap[y][x] != null && sharkMap[y][x].big < s.big) {
                removeSet.add(sharkMap[y][x]);
                sharkMap[y][x] = s;
            }else {
            	removeSet.add(s);
            }
        }

        for(Shark s : removeSet) {
            sharkSet.remove(s);
        }
    }
    static void updateOneShark(Shark s, Shark[][] matrix) {
        int distanceFromStart = getDistanceFromStart(s);
        int toGo = s.speed + distanceFromStart;
        int toDivide = (s.dirIndex < 2) ? matrix.length - 1 : matrix[0].length - 1;

        int quotient = toGo / toDivide;
        int rest = toGo % toDivide;

        if(quotient % 2 == 1) {
            int add = s.dirIndex >= 2 ? 2 : 0;
            s.dirIndex = (((s.dirIndex + 1) % 2)) + add;
        }

        if(s.dirIndex < 2) {
            s.currentY = Math.abs(startPoint.get(s.dirIndex) - rest);
        }else {
            s.currentX = Math.abs(startPoint.get(s.dirIndex) - rest);
        }
    }
    static int getDistanceFromStart(Shark s) {
        if(s.dirIndex < 2) {
            return Math.abs(startPoint.get(s.dirIndex) - s.currentY);
        }
        return Math.abs(startPoint.get(s.dirIndex) - s.currentX);
    }
    static int catchShark(int person) {
        for(int y = 0; y < sharkMap.length; y++) {
            if(sharkMap[y][person] != null) {
                return removeShark(y, person);
            }
        }
        return 0;
    }
    static int removeShark(int y, int x) {
        Shark removed = sharkMap[y][x];
        sharkSet.remove(removed);
        sharkMap[y][x] = null;
        return removed.big;
    }
    static Shark[][] makeMatrix(BufferedReader br, int height, int width, int sharkCount) throws IOException{
        Shark[][] matrix = new Shark[height][width];
        for(int i = 0; i < sharkCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int big = Integer.parseInt(st.nextToken());

            matrix[y][x] =  new Shark(x,y, speed, dir, big);
            sharkSet.add(matrix[y][x]);
        }
        return matrix;

    }

    static class Shark{
        int currentX;
        int currentY;
        int speed;
        int dirIndex;
        int big;

        public Shark(int x, int y,int speed, int dirIndex, int big) {
            this.currentX = x;
            this.currentY = y;
            this.speed = speed;
            this.dirIndex = dirIndex;
            this.big = big;
        }
    }
}