class Solution {
    static int[] position;
    static int count;
    public int solution(int n){
        position = new int[n+1];
        putQueen(n, 1);
        return count;
    }
    static void putQueen(int n,int row){
        if(row == n+1){
            count++;
            return;
        }
        for(int i = 1; i <= n; i++){
            if(promising(row, i)){
                position[row] = i;
                putQueen(n, row+1);
            }
        }
        position[row] = 0;
    }

    static boolean promising(int row, int column){
        for(int i = 1; i < position.length; i++){
            if(position[i] != 0){
                boolean isDown = (position[i] == column);
                boolean fromLeftSlide = (row-column == i-position[i]);
                boolean fromRightSlide = row + column == i + position[i];

                if(isDown || fromLeftSlide || fromRightSlide){
                    return false;
                }
            }
        }
        return true;
    }
}