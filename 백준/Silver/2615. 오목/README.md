# [Silver I] 오목 - 2615 

[문제 링크](https://www.acmicpc.net/problem/2615) 

### 성능 요약

메모리: 13172 KB, 시간: 124 ms

### 분류

브루트포스 알고리즘, 구현

### 문제 설명

<p>오목은 바둑판에 검은 바둑알과 흰 바둑알을 교대로 놓아서 겨루는 게임이다. 바둑판에는 19개의 가로줄과 19개의 세로줄이 그려져 있는데 가로줄은 위에서부터 아래로 1번, 2번, ... ,19번의 번호가 붙고 세로줄은 왼쪽에서부터 오른쪽으로 1번, 2번, ... 19번의 번호가 붙는다.</p>

![image](https://github.com/WhalesBob/AlgorithmSolving/assets/96509257/f7300113-1412-4d0b-8faf-98321af17f8a)

<p>위의 그림에서와 같이 같은 색의 바둑알이 연속적으로 다섯 알을 놓이면 그 색이 이기게 된다. 여기서 연속적이란 가로, 세로 또는 대각선 방향 모두를 뜻한다. 즉, 위의 그림은 검은색이 이긴 경우이다. 하지만 여섯 알 이상이 연속적으로 놓인 경우에는 이긴 것이 아니다.</p>

<p>입력으로 바둑판의 어떤 상태가 주어졌을 때, 검은색이 이겼는지, 흰색이 이겼는지 또는 아직 승부가 결정되지 않았는지를 판단하는 프로그램을 작성하시오. 단, 검은색과 흰색이 동시에 이기거나 검은색 또는 흰색이 두 군데 이상에서 동시에 이기는 경우는 입력으로 들어오지 않는다.</p>

### 입력 

 <p>19줄에 각 줄마다 19개의 숫자로 표현되는데, 검은 바둑알은 1, 흰 바둑알은 2, 알이 놓이지 않는 자리는 0으로 표시되며, 숫자는 한 칸씩 띄어서 표시된다.</p>

### 출력 

 <p>첫줄에 검은색이 이겼을 경우에는 1을, 흰색이 이겼을 경우에는 2를, 아직 승부가 결정되지 않았을 경우에는 0을 출력한다. 검은색 또는 흰색이 이겼을 경우에는 둘째 줄에 연속된 다섯 개의 바둑알 중에서 가장 왼쪽에 있는 바둑알(연속된 다섯 개의 바둑알이 세로로 놓인 경우, 그 중 가장 위에 있는 것)의 가로줄 번호와, 세로줄 번호를 순서대로 출력한다.</p>

<br/><br/>

# 오목 문제풀이 방향

문제는 읽어보았다 치고, 바로 어떻게 접근할 수 있는지 확인해 보겠습니다.
잡아야 하는 것은, 오목에서 딱 5개의 열이 존재한다면, "y x" 식으로 출력하는 것입니다. 
#### 이때, 6목 7목인 것을 배제해야 합니다! 

### 문제에 다가간 Hint
#### 1. 19 by 19 로 인풋이 고정되어 있음.
   - 만약에 2차원 배열을 모두 순회한다고 해도, 최대 361 개의 start 지점을 가지고 갈 수 있습니다.
   - 시간 제한은 **1초** 입니다. <b> 그리고 알고리즘 문제에서는, 1초당 1억 번의 연산을 보장해 주는 편입니다! </b>
   - 그렇게 바라본다면, 2차원 배열의 각 요소에서 제가 시도할 수 있는 연산의 갯수는, 250_000 번입니다.
   - 어차피 가로, 세로, 대각선 두 방향 모두를 합해도, 최대 80번 정도 비교를 하면 됩니다. 이때, 이 문제를 완전탐색과 같은 방향으로 풀 수 있겠다는 확신을 얻었습니다.
#### 2. 혹시, 조금 더 깔끔하게 풀 수 있는 방법이 있는가? 
   - 저는, 완전탐색 시 DFS를 애용합니다.
   - 각 시작점에서 재귀를 이용하며, 일정 조건이 되었을 때 탈출하게 만들어 준다면, 완전탐색에도 애용할 수 있습니다.

### Code
```Java
import java.io.*;
import java.util.*;

public class BOJ2615 {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0), Arrays.asList(0,1),
                                        Arrays.asList(1,1), Arrays.asList(1, -1)); 
	static Set<Set<List<Integer>>> longLineSet;
	static boolean haveFoundWinner;
	static int startX, startY;
	static int[][] matrix;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		matrix = makeMatrix(br); 

		longLineSet = new HashSet<>(); 
		haveFoundWinner = false;
		for(int x = 0; x < matrix[0].length; x++) { 
			for(int y = 0; y < matrix.length; y++) { 
				if(matrix[y][x] != 0) { 
					startX = x;
					startY = y; 
					for(int directionIndex = 0; directionIndex < 4; directionIndex++) {
						checkWinByDFS(x, y, matrix[y][x], directionIndex, 1, new HashSet<>());
					}
				}
			}
		}
		
		if(!haveFoundWinner) { 
			System.out.println(0);
		}
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
	static void checkWinByDFS(int x, int y, int compareValue, int directIndex, int count, Set<List<Integer>> pointSet) {
		
		int nextX = x + direction.get(directIndex).get(0); 
		int nextY = y + direction.get(directIndex).get(1); 
		if(canGo(nextX, nextY, matrix) && matrix[nextY][nextX] == compareValue) {
			pointSet.add(Arrays.asList(x, y)); 
			checkWinByDFS(nextX, nextY, compareValue, directIndex, count + 1, pointSet); 
		}
		
		if(count >= 5) { 
			if(count > 5) {
				longLineSet.add(pointSet);
			}else {
				if(alreadyHaveThisLine(pointSet)) {
					return;
				}
				
				haveFoundWinner = true; 
				System.out.println(compareValue);
				System.out.println((startY + 1) + " " + (startX + 1));
				System.exit(0);
			}
		}
	}
	static boolean canGo(int x, int y, int[][] matrix) { 
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}

	static boolean alreadyHaveThisLine(Set<List<Integer>> pointSet) {
		for(Set<List<Integer>> haveSet : longLineSet ) {
			if(haveSet.containsAll(pointSet)) { 
				return true;
			}
		}
		return false;
	}
}
```
