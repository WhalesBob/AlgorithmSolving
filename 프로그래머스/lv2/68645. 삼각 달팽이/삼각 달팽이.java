class Solution {
    public int[] solution(int n) {
        int[] array = new int[(n*(n+1))/2];
        int turn_count = 0;
        int value = 0;
        int index = 0;
        int add_value = 1;

        while(turn_count < n){
            if(turn_count % 3 == 0){
                for(int i = 0; i < n - turn_count; i++){
                    index += value;
                    array[index] = add_value;
                    add_value++;
                    value++;
                }
            }else if(turn_count % 3 == 1){
                for(int i = 0; i < n - turn_count; i++){
                    index += 1;
                    array[index] = add_value;
                    add_value++;
                }
            }else{
                for(int i = 0; i < n - turn_count; i++){
                    index -= value;
                    array[index] = add_value;
                    add_value++;
                    value--;
                }
            }
            turn_count++;
        }
        return array;
    }
}