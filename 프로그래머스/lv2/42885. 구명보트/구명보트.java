import java.util.*;

class Solution {
    public int solution(int[] people, int limit){
        if(people.length == 1){
            return 1;
        }
        
        Arrays.sort(people);
        int count = 0;

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int person : people) {
            arrayDeque.addLast(person);
        }

        while(!arrayDeque.isEmpty()){
            int small = arrayDeque.removeFirst();
            count++;
            if(arrayDeque.isEmpty()){
                break;
            }
            int big;
            do{
                big = arrayDeque.removeLast();
                count++;
            }while(small + big > limit && !arrayDeque.isEmpty());

            if(small + big <= limit){
                count--;
            }
        }
        return count;
    }
}