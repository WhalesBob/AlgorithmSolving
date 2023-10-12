import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        
        // 가로, 대각, 세로
        int[][][] dp = new int[N][N][3];
        
        dp[0][1][0] = 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        
        int[][] check = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            for(int j = 2; j < N; j++) {
                
            	if(map[i][j] == 1) {
            		continue;
            	}
            	
                // 가로로 들어오는경우
                if(map[i][j-1] == 0) {
                    dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
                }
                
                // 대각으로 들어오는 경우
                if(i > 0 && map[i-1][j-1] == 0 && map[i][j-1] == 0 && map[i-1][j] == 0) {
                    dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
                }
                
                // 세로로 들어오는 경우
                if(i > 0 && map[i-1][j] == 0) {
                    dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];
                }
                
                check[i][j] = dp[i][j][0] + dp[i][j][1] + dp[i][j][2]; 
            }
        }
        
//        for(int i = 0; i < N; i++) {
//            for(int j = 0; j < N; j++) {
//                System.out.print(check[i][j] + " ");
//            }
//            System.out.println("");
//        }
        
        
        System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);

    }

}