# [Gold I] 보석 모으기 - 1480 

[문제 링크](https://www.acmicpc.net/problem/1480) 

### 성능 요약

메모리: 24464 KB, 시간: 388 ms

### 분류

비트마스킹, 다이나믹 프로그래밍, 비트필드를 이용한 다이나믹 프로그래밍

### 문제 설명

<p>세준이는 잘 모르겠지만, 세준이는 보석에 미쳐있다. 따라서, 숌 보석상에 있는 모든 보석을 다 훔치려고 한다. 하지만, 세준이는 보석을 다 가져올 수는 없다. 그 이유는 가방의 개수에 제한이 있고, 한 가방마다 넣을 수 있는 보석의 개수가 제한이 있기 때문이다. 세준이는 M개의 가방을 가지고 있다. 그리고 각각의 가방은 C그램의 보석을 담을 수 있다.</p>

<p>숌 보석상에는 보석이 N개 있다. N개의 보석의 무게가 주어졌을 때, 세준이가 훔칠 수 있는 보석의 최대 개수를 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 보석의 개수 N, 가방의 개수 M, 가방의 최대 한도 C가 주어진다. N은 1보다 크거나 같고, 13보다 작거나 같은 자연수이고, M은 1보다 크거나 같고, 10보다 작거나 같은 자연수이다. C는 1보다 크고, 20보다 작거나 같은 자연수이다. 둘째 줄에 보석의 무게가 하나씩 주어진다. 보석의 무게는 1보다 크거나 같고, 20보다 작거나 같은 자연수이다.</p>

### 출력 

 <p>첫째 줄에 세준이가 가져갈 수 있는 최대 보석의 개수를 출력한다.</p>

### 배우고 느낀 점

- "비트필드를 이용한 다이나믹 프로그래밍"... 이라는 문제 태그를 처음 본다. 앞으로 이런 태그를 알아두도록 하자.
- 메모이제이션을 정말 세부적으로 진행하는 것 같다. 3차원 배열에서 메모이제이션을 진행했다.
 - 첫번째에, jewelCount 만큼 비트를 왼쪽으로 밀어 행? 을 지정했고, 두번째는 남은 가방 카운트, 세번째는 남은 가방 무게 로 테이블을 지정했다.
 - 가방을 몇 개째 쓰고 있는지, 해당 가방의 남은 카운트가 얼마나 되는지, 그때 몇 개를 사용했는지를 메모이제이션한다.

- 이렇게 하면, 이때동안 어떤 요소를 포함했는지 비트마스킹(AND 연산)으로 알 수 있다.
- 그리고, 추가 포함시키는 것을 OR 연산으로 포함시킬 수 있다.
- 그리고, dp[visit][index][leftWeight] 의 더 max 값을 dp 에서 업데이트하며, dfs 내에서 모두 진행했을 때 그 이전의 최대값을 DP 테이블에 메모이제이션할 수 있을 것이다.
- 결론적으로, 원래는 중복해서 보는 값이 아예 없을 수는 없다. 그렇지만, 이렇게 함으로 인해서 백트래킹에서 보지 않아도 될 연산을 아껴서 시간을 단축시킬 수 있나 보다.
- 
