import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0), Arrays.asList(0,1),
			Arrays.asList(1,1), Arrays.asList(1, -1)); // 각 방향 별로, x,y 를 얼마나 늘려야 하는지 정보를 주기 위해 만든 전역 변수입니다.
	static Set<Set<List<Integer>>> longLineSet;
	static boolean haveFoundWinner;
	static int startX;
	static int startY;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] matrix = makeMatrix(br); // 이차원 배열을 만들어 줍니다.

		longLineSet = new HashSet<>(); // 오목에서, 6개 이상의 숫자 열이 나올 때, List<Integer> 로 저장된 x,y 값들을 저장하기 위해, set 을 만들어 주었습니다.
		// 이 set 을 통해서, 만약 5개의 숫자가 이미 카운트되었을 때 위의 set에 해당 숫자열이 이미 존재한다면, 이긴 것으로 하지 않고, dfs 를 끝내기 위해 만들어 주었습니다.
		startX = 0; // startX, startY 를 모두 0으로 초기화합니다.
		startY = 0;
		haveFoundWinner = false;
		for(int x = 0; x < matrix[0].length; x++) { // 왼쪽 0,0 부터 y 값을 먼저 올리며 확인하기 위해, 이중 for 문에서 x 를 먼저 넣어줍니다.
			for(int y = 0; y < matrix.length; y++) { // y 도 for 문에 넣어 줍니다.
				if(matrix[y][x] != 0) { // 이차원 배열에서 0이 아닌 경우에만, DFS를 실행시켜 주기 위함입니다.
					startX = x;
					startY = y; // DFS method 안에서 출력을 위해, startX, startY 를 x,y 로 세팅해 줍니다.
					for(int i = 0; i < 4; i++) { // 오른쪽 방향, 아래 방향, 오른쪽 아래방향, 오른쪽 위 방향  을 순차적으로 DFS 를 돌려 줍니다.
						checkWin(x, y, matrix, matrix[y][x], i, 1, new HashSet<>());
					}
				}
			}
		}
		
		if(!haveFoundWinner) { // 만약, DFS 를 모두 돌며 한번도 haveFoundWinner 가 true 로 바뀌지 않았을 경우, 0을 출력해 줍니다.
			System.out.println(0);
		}
	}
	static void checkWin(int x, int y, int[][] matrix, int startValue, int directIndex, int count, Set<List<Integer>> pointSet) {
		
		int nextX = x + direction.get(directIndex).get(0); // directionIndex 를 보며 다음 x를 업데이트합니다.
		int nextY = y + direction.get(directIndex).get(1); // directionIndex 를 보며, 다음 y 를 업데이트합니다.
		if(canGo(nextX, nextY, matrix) && matrix[nextY][nextX] == startValue) { // 만약 다음 포인트로 갈수 있으며, 이미 받았던 startValue 와 다음 포인트의 값이 같을 때
			pointSet.add(Arrays.asList(x, y)); // pointSet 에 기존의 포인트 정보를 업데이트해 줍니다.
			checkWin(nextX, nextY, matrix, startValue, directIndex, count + 1, pointSet); // DFS를 순회합니다.
		}
		
		if(count >= 5) { // 만약 다음  DFS 를 순회하지 못했는데, count 가 5가 넘었을 경우, 이겼는지 아닌지 체크합니다. 그리고, 6개가 넘는 케이스에서는,
			// 전역 변수 longLineSet 에 이미 받았던 pointSet 를 추가해, 이후 점검에서 활용합니다.
			
			if(count > 5) {
				addLongLineSet(pointSet); 
			}else {
				if(alreadyHaveThisLine(pointSet)) { // longLineSet 에 이미 들어가 있는 pointSet 인지 확인하고, 이미 들어있는 포인트 열이라면, 리턴합니다.
					return;
				}
				haveFoundWinner = true; // 위의 조건에 걸리지 않았다면, haveFoundWinner 를 true 로 갱신한 후, 출력하고, 시스템을 닫습니다.
				System.out.println(startValue);
				System.out.println((startY + 1) + " " + (startX + 1));
				System.exit(0);
			}
		}
	}
	static void addLongLineSet(Set<List<Integer>> pointSet) {
		longLineSet.add(pointSet);
	}
	static boolean alreadyHaveThisLine(Set<List<Integer>> pointSet) {
		for(Set<List<Integer>> haveSet : longLineSet ) {
			if(haveSet.containsAll(pointSet)) { // 두 Set 을 비교해서, haveSet 이 pointSet 을 모두 포함하고 있다면, true 를 반환해 줍니다.
				return true;
			}
		}
		return false;
	}
	static int[][] makeMatrix(BufferedReader br) throws IOException{
		int[][] matrix = new int[19][19];
		for(int i = 0; i < 19; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < 19; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return matrix;
	}
	static boolean canGo(int x, int y, int[][] matrix) { // 물리적으로 다음 x,y 에 접근할 수 있는지 확인하는 메소드입니다.
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
}