# [Silver I] 오목 - 2615 
[문제 링크](https://www.acmicpc.net/problem/2615) 
### 성능 요약

메모리: 13172 KB, 시간: 124 ms

### 문제 설명

<p>오목은 바둑판에 검은 바둑알과 흰 바둑알을 교대로 놓아서 겨루는 게임이다. 바둑판에는 19개의 가로줄과 19개의 세로줄이 그려져 있는데 가로줄은 위에서부터 아래로 1번, 2번, ... ,19번의 번호가 붙고 세로줄은 왼쪽에서부터 오른쪽으로 1번, 2번, ... 19번의 번호가 붙는다.</p>

![image](https://github.com/WhalesBob/AlgorithmSolving/assets/96509257/f7300113-1412-4d0b-8faf-98321af17f8a)

<p>위의 그림에서와 같이 같은 색의 바둑알이 연속적으로 다섯 알을 놓이면 그 색이 이기게 된다. 여기서 연속적이란 가로, 세로 또는 대각선 방향 모두를 뜻한다. 즉, 위의 그림은 검은색이 이긴 경우이다. <b>하지만 여섯 알 이상이 연속적으로 놓인 경우에는 이긴 것이 아니다. </b> </p>

<p>입력으로 바둑판의 어떤 상태가 주어졌을 때, 검은색이 이겼는지, 흰색이 이겼는지 또는 아직 승부가 결정되지 않았는지를 판단하는 프로그램을 작성하시오. 단, 검은색과 흰색이 동시에 이기거나 검은색 또는 흰색이 두 군데 이상에서 동시에 이기는 경우는 입력으로 들어오지 않는다.</p>

### 입력 
 <p>19줄에 각 줄마다 19개의 숫자로 표현되는데, 검은 바둑알은 1, 흰 바둑알은 2, 알이 놓이지 않는 자리는 0으로 표시되며, 숫자는 한 칸씩 띄어서 표시된다.</p>

### 출력 

 <p>첫줄에 검은색이 이겼을 경우에는 1을, 흰색이 이겼을 경우에는 2를, 아직 승부가 결정되지 않았을 경우에는 0을 출력한다. 검은색 또는 흰색이 이겼을 경우에는 둘째 줄에 연속된 다섯 개의 바둑알 중에서 <b>가장 왼쪽에 있는 바둑알(연속된 다섯 개의 바둑알이 세로로 놓인 경우, 그 중 가장 위에 있는 것)의 가로줄 번호와, 세로줄 번호를 순서대로 출력한다.</b> </p>

 ### 바둑알을 굳이 오른쪽 아래로만 내릴 수 있는 것이 아닙니다! 오른쪽 위로도 올릴 수 있습니다! <br/>
 ### 오른쪽 위로 바둑알이 향할 경우, "가장 왼쪽에 있는 바둑알" 을 출력합니다. 이 때는, 왼쪽 아래에 있는 바둑알 좌표를 출력하는 것입니다.

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

### 논리 전개

#### 1. 먼저, DFS로 어떻게 처리할 지 생각합니다.

![image](https://github.com/WhalesBob/AlgorithmSolving/assets/96509257/b1ef7376-1656-469f-bdfe-afab5ffa77d0)

- 만약에, DFS를 돌면서, 다음 x 와 y 를 구해준 다음, 다음 지점의 숫자가 처음 비교하고자 했던 숫자와 같다면, 그 다음으로 넘어갈 수 있을 것입니다.
	- (3,2) 의 숫자와 (4,3) 의 숫자를 비교한다고 가정해 보겠습니다. x = 3, y = 2 라는 지점에서, x = 4, y = 3 이라는 **다음 x,y 를 도출** 할 수 있다면, 다음 이차원 배열의 요소와 비교하고자 하는 숫자를 비교할 수 있습니다.
	- 보고자 하는 이차원 배열의 요소가 처음 비교하고자 하는 숫자와 일치하면, DFS 를 진행합니다.

- 첫번째 조건문 ` if(canGo(nextX, nextY, matrix) && matrix[nextY][nextX] == compareValue) ` 를 만족한다면, 무조건 재귀를 계속 가져가며 재귀함수를 호출합니다.
	- 5목을 초과하는 것도 다 확인하기 위해서, 재귀함수를 부를 때, 몇 개의 돌이 연속하는지 계속 `count` 를 추가해 줍니다.
   	- 위의 if 문의 조건에 true 가 걸리지 않으면, 탈출조건을 확인하러 가는 식입니다.
<br/><br/>
#### 1.1 그럼 재귀에서 탈출조건은 어떻게?
- 이번 케이스에서는, DFS를 먼저 업데이트 하는 식으로 재귀함수 흐름이 진행됩니다.
  	- 어차피 if 문에서, 재귀를 계속할지 체크하는 로직이 존재하기 때문에, if 문을 통과하지 않는 한 StackOverFlowException 이 발생할 염려가 없습니다.
- 어차피, 위의 if 문에서 걸리지 않았으면, 함수가 끝나는 셈이므로 자동으로 return 이 될 것입니다.
<br/><br/>
#### 2. 기본적인 논리 전개는 Okay. 하지만, 구현은 어떤 식으로 할 것인지?

- 위에서, 이차원 배열의 모든 점을 start 지점으로 하여, DFS를 모두 돌려버려도 된다는 확신이 있었습니다. 그렇다면, 그냥 2중 for 문을 만들어 모든 지점에 있어 DFS 를 진행해 줍니다.
- 또 다른 애로사항으로, 오른쪽, 아래쪽, 오른쪽 아래, 오른쪽 위 로 다음 x,y 를 어떻게 도출할 것이냐는 점이 있습니다. 이 때, 저는 아래와 같은 방법으로 미리 direction 을 지정해 줍니다. BFS 알고리즘을 사용할 때도 애용하는 방법이기도 합니다.

 ```Java
static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0), Arrays.asList(0,1),
                                        Arrays.asList(1,1), Arrays.asList(1, -1));
// 위와 같은 방법으로, 미리 가고자 하는 방향의 인덱스만 지정해 준다면, 계속 같은 방향으로 나아가게 할 수 있을 것입니다.
// DFS 에서, 파라미터로만 계속 집어넣어 주면 되는 것입니다.
```
<br/><br/>
#### 3. 구현도 이해했다! 그런데, 6목 7목을 걸러주는 코드는 어떻게 할거야?

- 저는 전역변수로, `static Set<Set<List<Integer>>> longLineSet` 과 같이 Set 을 지정했습니다.
	- 내부의 Element 인 `Set<List<Integer>>` 는, List<Integer> 로 (x,y) point 를 모아 주는 Set<> 이라고 이해하시면 될 것 같습니다.
	- 파이썬에서는 `Tuple` 자료구조를 사용할 것을, Java 에서는 그냥 List<Integer> 와 같이 써 주는 것입니다.
   	- 굳이 객체를 구현했다고 하면, `Set<Point>` 와 같이 표현할 수 있을 것입니다.

- 5목을 초과한 케이스에 대해, 위의 전역변수에 Set<List<Integer>> 에 모아 둔 Set 을 추가해 줍니다.
- 이후의 5목을 발견한 경우, `longLineSet` 의  `Set<List<Integer>>` 요소와, 재귀를 타면서 업데이트해준  `Set<List<Integer>>` 를 비교합니다. 이 때, 재귀를 돌면서 모아 온 포인트들의 Set 이, `longLineSet` 이 가지고 있는 포인트 집합과 포함 관계에 있다면, 발견한 오목은 승리 조건이 되지 못합니다. return 해 주면 될 것입니다.


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
