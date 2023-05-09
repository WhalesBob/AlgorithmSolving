import java.util.*;

class Solution {
    public long solution(int[] weights) {
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(weights);

        for(int weight : weights){
            try{
                Integer count = map.get(weight);
                map.replace(weight, count + 1);
            }catch (NullPointerException e){
                map.put(weight, 1);
            }
        }

        long result = 0;
        double[] ratio = {1.0,1.5,(4D/3D),2.0};

        for(int weight : weights){
            deleteValue(map,weight);
            for(double r : ratio){
                double value = weight * r;
                if(isInteger(value) && map.containsKey((int)value)){
                    result += map.get((int)value);
                }
            }
        }
        return result;
    }
    static boolean isInteger(double value){
        return value == (int)value;
    }
    static void deleteValue(Map<Integer, Integer> map, int value){
        try{
            Integer deletedValueCount = map.get(value);
            if(deletedValueCount == 1){
                map.remove(value);
            }else{
                map.replace(value, deletedValueCount - 1);
            }
        }catch(NullPointerException ignored){}
    }
}
