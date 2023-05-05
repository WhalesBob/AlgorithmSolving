class Solution {
   public int[] solution(int brown, int yellow){
        int length = brown - 4;
        
        int sum = length / 2;
        for(int a = 1; a < sum; a++){
            int b = sum - a;
            if(a * b == yellow){
                return new int[]{b+2,a+2};
            }
        }
        return null;
    }
}