import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Meet> meetList = makeMeetList(br,n).stream()
                .sorted(Comparator.comparing(Meet::getEnd)).collect(Collectors.toList());
        System.out.println(maxCount(meetList));
    }
    static List<Meet> makeMeetList(BufferedReader br, int n) throws IOException{
        List<Meet> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Meet(start,end));
        }
        return list;
    }
    static int maxCount(List<Meet> meetList){
        Stack<Meet> stack = new Stack<>();
        stack.add(meetList.get(0));

        for(int i = 1; i < meetList.size(); i++){
            if(!isOverlapped(stack.peek(), meetList.get(i))){
                stack.add(meetList.get(i));
            }
        }
        return stack.size();
    }
    static boolean isOverlapped(Meet A, Meet B){
        if(A.start == A.end || B.start == B.end){
            return (B.start < A.start && A.start < B.end)
                    || (A.start < B.start && B.start < A.end);
        }
        return (B.start <= A.start && A.start < B.end)
                || (A.start <= B.start && B.start < A.end);
    }
    static class Meet{
        int start;
        int end;

        public Meet(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getEnd() {
            return end;
        }
    }
}