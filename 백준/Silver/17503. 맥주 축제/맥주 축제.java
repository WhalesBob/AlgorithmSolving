import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int days = Integer.parseInt(st.nextToken());
        int needLike = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Beer[] array = makeBeerArray(br, k);

        PriorityQueue<Beer> removeQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.like));
        long sum = 0;
        for(int i = 0; i < days; i++){
            sum += array[i].like;
            removeQueue.add(array[i]);
        }

        int right = days;

        while(right < array.length){
            if(sum >= needLike){
                break;
            }

            Beer remove = removeQueue.remove();
            sum -= remove.like;

            removeQueue.add(array[right]);
            sum += array[right++].like;
        }

        int max = removeQueue.stream().max(Comparator.comparingInt(a -> a.level)).get().level;
        System.out.println(sum < needLike ? -1 : max);
    }
    static Beer[] makeBeerArray(BufferedReader br, int size) throws IOException{
        Beer[] array = new Beer[size];
        for(int i = 0; i < size; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int like = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            array[i] = new Beer(like, level);
        }

        Arrays.sort(array, (a,b) -> {
            if(a.level == b.level){
                return Integer.compare(a.like, b.like);
            }
            return Integer.compare(a.level, b.level);
        });
        return array;
    }
    static class Beer implements Comparable<Beer> {
        int like;
        int level;

        public Beer(int like, int level) {
            this.like = like;
            this.level = level;
        }

        @Override
        public int compareTo(Beer o) {
            return Integer.compare(o.level, this.level);
        }
    }
}