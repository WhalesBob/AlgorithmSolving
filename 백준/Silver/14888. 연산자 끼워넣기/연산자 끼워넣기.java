import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE;
    static int[] num;
    static int[] four=new int[4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        int n=Integer.parseInt(br.readLine());
        num = new int[n];
        st= new StringTokenizer((br.readLine()));
        for(int i =0;i<n;i++) 
            num[i]=Integer.parseInt(st.nextToken());
        st= new StringTokenizer((br.readLine()));
        for(int i =0;i<4;i++) 
            four[i]=Integer.parseInt(st.nextToken());        //입력끝
        find(num[0],n-1,1);
        System.out.printf("%d",max);
        System.out.println();
        System.out.printf("%d",min);
    }
    public static void find(int start,int cnt,int next) { //(결과,카운트)
        if(cnt==0) { //연산끝나면 최대 최소 판단
            if(max<start) max=start; 
            if(min>start) min=start; 
            return;
        }
        for(int i=0;i<4;i++) {
            switch (i) {
            case 0 : if(four[0]>0) {four[0]--;find(start+num[next],cnt-1,++next);four[0]++;next--;}
            case 1 : if(four[1]>0) {four[1]--;find(start-num[next],cnt-1,++next);four[1]++;next--;};
            case 2 : if(four[2]>0) {four[2]--;find(start*num[next],cnt-1,++next);four[2]++;next--;};
            case 3 : if(four[3]>0) {four[3]--;find(start/num[next],cnt-1,++next);four[3]++;next--;};
            }
        }
    }
}