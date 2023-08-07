import java.util.*;

class Solution {
    static List<List<Integer>> direction = Arrays.asList(Arrays.asList(0,1), Arrays.asList(1,0), Arrays.asList(-1,-1)); 
    public int[] solution(int n) {
        int[][] matrix = new int[n][n];
		int[] array = new int[(n*(n+1))/2];
		
        int turnCount = 0;
        int index = 0;
        int addValue = 1;
        int x = 0, y = -1;
        
        while(true) {
        	int nextX = x + direction.get(index).get(0);
        	int nextY = y + direction.get(index).get(1);
        	
        	if(canGo(nextX, nextY, matrix) && matrix[nextY][nextX] == 0) {
        		turnCount = 0;
        		matrix[nextY][nextX] = addValue++;
        		x = nextX;
        		y = nextY;
        		
        	}else {
        		if(turnCount == 3) {
        			break;
        		}
        		turnCount++;
        		index = (index + 1) % 3;
        	}
        }
        
        index = 0;
        for(y = 0; y < matrix.length; y++) {
        	for(x = 0; x <= y; x++) {
        		array[index++] = matrix[y][x];
        	}
        }
        return array;
	}
	static boolean canGo(int x, int y, int[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
}