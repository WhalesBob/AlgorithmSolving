import java.util.HashSet;
import java.util.Set;

class Solution {
    static int sumOfAll;
    public int solution(int[] elements){

        sumOfAll = 0;
        for (int element : elements) {
            sumOfAll += element;
        }

        Set<Integer> sumSet = new HashSet<>();

        for(int i = elements.length; i >= elements.length/2; i--){
            summary(sumSet,elements,i);
        }
        return sumSet.size() - 1;
    }
    static void summary(Set<Integer> set, int[] elements, int howMany){
        for(int index = 0; index < elements.length; index++){
            int toAdd = sumOfSubArray(elements,index,howMany);
            set.add(toAdd);
            set.add(sumOfAll - toAdd);
        }
    }
    static int sumOfSubArray(int[] elements, int start, int howMany){
        int sum = 0;
        for(int i = 0; i < howMany; i++){
            int index = (start + i) % elements.length;
            sum += elements[index];
        }
        return sum;
    }
}
