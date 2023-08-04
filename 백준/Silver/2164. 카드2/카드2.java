import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayDeque<Integer> arrayDeque = Stream.iterate(1, x -> x + 1).limit(n).collect(Collectors.toCollection(ArrayDeque::new));
        while(arrayDeque.size() != 1){
            arrayDeque.removeFirst();
            arrayDeque.addLast(arrayDeque.removeFirst());
        }
        System.out.println(arrayDeque.removeFirst());
    }
}