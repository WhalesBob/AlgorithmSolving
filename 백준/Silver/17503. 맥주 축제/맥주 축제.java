import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int days = Integer.parseInt(st.nextToken());
        int needLike = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Beer[] array = makeBeerArray(br, k);

        PriorityQueue<Beer> levelQueue = new PriorityQueue<>();
        PriorityQueue<Beer> removeQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.like));
        long sum = 0, needMax = Long.MAX_VALUE;
        assert days >= 1;
        for(int i = 0; i < days; i++){
            levelQueue.add(array[i]);
            sum += array[i].like;
            removeQueue.add(array[i]);
        }

        int right = days;

        while(right < array.length){
            if(sum >= needLike){
                break;
            }

            Beer remove = removeQueue.remove();

            levelQueue.remove(remove);
            sum -= remove.like;

            levelQueue.add(array[right]);
            removeQueue.add(array[right]);
            sum += array[right++].like;
        }

        System.out.println(sum < needLike ? -1 : levelQueue.peek().level);
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