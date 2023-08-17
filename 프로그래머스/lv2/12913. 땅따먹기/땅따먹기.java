class Solution {
    int solution(int[][] land) {
		int[][] record = new int[land.length][land[0].length];
		for(int i = 0; i < 4; i++) {
			record[0][i] = land[0][i];
		}
		
		for(int y = 1; y < record.length; y++) {
			for(int x = 0; x < 4; x++) {
				record[y][x] = land[y][x] + findMax(record[y-1], x);
			}
		}
		
		int max = -1;
		for(int i = 0; i < 4; i++) {
			if(max < record[record.length - 1][i]) {
				max = record[record.length - 1][i];
			}
		}
		return max;
	}
	static int findMax(int[] array, int currentX) {
		int max = -1;
		for(int i = 0; i < array.length; i++) {
			if(i != currentX && array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
}