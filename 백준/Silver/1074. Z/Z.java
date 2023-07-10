import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        answer = 0;
        findValue(r,c,0,0,N);
        System.out.println(answer);
    }
    static void findValue(int y,int x,int startX, int startY, int N){
        if(N == 0){
            return;
        }

        int halfValue = (int)Math.pow(2,N-1);

        boolean lessY = (y < startY + halfValue);
        boolean lessX = (x < startX + halfValue);
        boolean firstQuarter = lessX && lessY;
        boolean secondQuarter = !lessX && lessY;
        boolean thirdQuarter = lessX && !lessY;

        if(firstQuarter){
            findValue(y,x,startX,startY,N-1);
        }else if(secondQuarter){
            answer += (int)Math.pow(halfValue,2);
            findValue(y,x,startX + halfValue,startY, N-1);
        }else if(thirdQuarter){
            answer += (int)Math.pow(halfValue,2) * 2;
            findValue(y,x,startX, startY + halfValue, N-1);
        }else{
            answer += (int)Math.pow(halfValue,2) * 3;
            findValue(y,x,startX + halfValue, startY + halfValue,N-1);
        }
    }
}