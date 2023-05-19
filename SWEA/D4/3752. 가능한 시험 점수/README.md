# [D4] 가능한 시험 점수 - 3752 

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWHPkqBqAEsDFAUn) 

### 성능 요약

메모리: 63,056 KB, 시간: 283 ms, 코드길이: 499 Bytes



> 출처: SW Expert Academy, https://swexpertacademy.com/main/code/problem/problemList.do

## 배운 점

- 결론적으로, 내가 떠올려서 푼 문제가 아니다. 하지만, 이번 케이스에서 배열에 저장해 놓고 방문으로 저장하기 라는 접근 방법을 찾았다. 
- 경우의 수 구하기에서, 물론 완전탐색으로 찾아야 하는 케이스들이 존재한다. 하지만, 조합을 전부다 구해야 하는 케이스가 있는 반면, 아닌 케이스도 있다.
- SWEA 에서 itertools 가 되는 것을 확인했다. 그렇기에, 삼성 관련 코테에서 itertools 를 거의 모두 사용할 수 있을 것이다. 
- 이번 케이스에서는 숫자를 모두 더해서 나올 수 있는 경우의 수가 몇개 인가? 라는 케이스였다. 그냥 이중 for 문을 사용하면 문제를 풀 수 있었던 케이스.
- itertools 의 시간복잡도 = n! / r!(n - r)! -> 반드시 필요한 케이스 : 내용물을 모두 파악해야 할 때.
- 조합이라고 생각햇는데, combinations 가 필요 없을 때 -> 숫자에서 합 경우의 수 구하기 정도? 일단 이럴 수 있다는 사실을 염두에 두도록 하자. 나중에 비슷한 문제를 만났을 때 떠올릴 수 있을 것이다.
