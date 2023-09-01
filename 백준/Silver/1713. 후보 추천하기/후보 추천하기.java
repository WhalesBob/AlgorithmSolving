import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int suggestCount = sc.nextInt();

        PriorityQueue<Person> queue = new PriorityQueue<>();

        int[] personNumber = new int[suggestCount];
        for(int i = 0; i < personNumber.length; i++){
            personNumber[i] = sc.nextInt();
        }

        Person[] haveArray = new Person[101];

        for(int i = 0; i < suggestCount; i++){
            int target = personNumber[i];
            if(haveArray[target] != null && haveArray[target].suggestCount != 0){
                queue.remove(haveArray[target]);
            }else{
                if(queue.size() == n){
                    Person removedPerson = queue.remove();
                    removedPerson.suggestCount = 0;
                }

                if(haveArray[target] == null){
                    haveArray[target] = new Person(target);
                }
                haveArray[target].time = i;
            }

            haveArray[target].suggestCount++;
            queue.add(haveArray[target]);
        }

        List<Integer> result = new ArrayList<>();
        queue.forEach(p -> result.add(p.data));
        Collections.sort(result);

        StringJoiner joiner = new StringJoiner(" ");
        for(Integer i : result) {
            joiner.add(Integer.toString(i));
        }
        System.out.println(joiner);
    }
    static class Person implements Comparable<Person>{
        int data;
        int time;
        int suggestCount;

        public Person(int data) {
            this.data = data;
            this.suggestCount = 0;
        }

        @Override
        public int compareTo(Person o) {
            if(o.suggestCount == this.suggestCount){
                return Integer.compare(this.time, o.time);
            }
            return Integer.compare(this.suggestCount, o.suggestCount);
        }
    }
}