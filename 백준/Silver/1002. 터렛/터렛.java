import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < inputCase; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            playGame(x1,y1,r1,x2,y2,r2);
        }
    }

    static void playGame(int x1,int y1, int r1, int x2, int y2, int r2){
        System.out.println(howToCircleExist(x1,y1,r1,x2,y2,r2));
    }

    static int howToCircleExist(int x1,int y1,int r1, int x2, int y2, int r2){
        if(isSameCircle(x1,y1,r1,x2,y2,r2)){
            return -1;
        }
        if(isOneHaveAnother(x1,y1,r1,x2,y2,r2) || isTwoCircleDistinct(x1,y1,r1,x2,y2,r2)){
            return 0;
        }
        if(isTwoShareOnePoint(x1,y1,r1,x2,y2,r2)){
            return 1;
        }
        return 2;
    }

    static boolean isSameCircle(int x1,int y1,int r1, int x2, int y2, int r2){
        return (x1 == x2) && (y1 == y2) && (r1 == r2);
    }

    static boolean isOneHaveAnother(int x1,int y1,int r1, int x2, int y2, int r2){
        // 두 점 사이의 거리 + 작은 쪽의 반지름이 큰 쪽의 반지름 보다 작아야 한다.
        double distanceSquare = distanceSquareBetweenTwoPoint(x1,y1,x2,y2);
        return distanceSquare < Math.pow(r1-r2,2);
    }
    static boolean isTwoShareOnePoint(int x1,int y1,int r1, int x2, int y2, int r2){
        double distanceSquare = distanceSquareBetweenTwoPoint(x1,y1,x2,y2);
        return (distanceSquare == Math.pow(r2- r1,2)) || (distanceSquare == Math.pow(r1 + r2,2));
    }
    static boolean isTwoCircleDistinct(int x1,int y1,int r1, int x2, int y2, int r2){
        double distanceSquare = distanceSquareBetweenTwoPoint(x1,y1,x2,y2);
        return distanceSquare > Math.pow(r1 + r2,2);
    }
    static double distanceSquareBetweenTwoPoint(int x1, int y1, int x2, int y2){
        return Math.pow(x1-x2,2) + Math.pow(y1-y2,2);
    }
}