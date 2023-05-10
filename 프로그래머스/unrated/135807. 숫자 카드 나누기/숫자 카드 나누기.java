import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        return Math.max(getMaximumOfResult(arrayA, arrayB), getMaximumOfResult(arrayB, arrayA));
    }
    static int getMaximumOfResult(int[] A, int[] B){
        boolean isDivided;
        Set<Integer> dividerOfMinimum = getDivider(A[0]);
        PriorityQueue<Integer> priorityQueue = getRealDivide(dividerOfMinimum, A);

        while(!priorityQueue.isEmpty()){
            int trying = priorityQueue.remove();
            isDivided = false;
            for(int element : B){
                if(element % trying == 0){
                    isDivided = true;
                    break;
                }
            }
            if(!isDivided){
                return trying;
            }
        }
        return 0;
    }

    static Set<Integer> getDivider(int number){
        Set<Integer> divider = new HashSet<>();
        for(int i = 2; i <= Math.sqrt(number); i++){
            if(number % i == 0){
                divider.add(i);
                divider.add(number / i);
            }
        }
        divider.add(number);
        return divider;
    }
    static PriorityQueue<Integer> getRealDivide(Set<Integer> set, int[] array){
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        outfor : for(Integer element : set){
            for (int divided : array) {
                if (divided % element != 0) {
                    continue outfor;
                }
            }
            queue.add(element);
        }
        return queue;
    }

}