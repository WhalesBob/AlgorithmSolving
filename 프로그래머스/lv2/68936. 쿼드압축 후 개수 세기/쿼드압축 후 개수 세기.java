import java.util.*;

class Solution {
    static List<Integer> result;
    public int[] solution(int[][] arr){
        result = new ArrayList<>();
        getAppend(arr, 0, arr[0].length, 0, arr.length);
        int[] answer = {0,0};

        for (Integer resultInteger : result) {
            answer[resultInteger]++;
        }
        return answer;
    }
    public void getAppend(int[][] matrix, int startX, int endX, int startY, int endY){
        if(isAllSame(matrix, startX, endX, startY, endY)){
            result.add(matrix[startY][startX]);
        }else{
            zipping(matrix, startX, endX, startY, endY);
        }
    }
    public void zipping(int[][] matrix, int startX, int endX, int startY, int endY){
        int halfX = (endX + startX) / 2;
        int halfY = (endY + startY) / 2;

        getAppend(matrix, startX, halfX, startY, halfY);
        getAppend(matrix, halfX, endX, startY, halfY);
        getAppend(matrix, startX, halfX, halfY, endY);
        getAppend(matrix, halfX, endX, halfY, endY);
    }
    
    public boolean isAllSame(int[][] matrix, int startX, int endX, int startY, int endY){
        int first = matrix[startY][startX];
        for(int i = startY; i < endY; i++){
            for(int j = startX; j < endX; j++){
                if(first != matrix[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}    