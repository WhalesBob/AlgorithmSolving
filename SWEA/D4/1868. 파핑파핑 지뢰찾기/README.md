# [D4] 파핑파핑 지뢰찾기 - 1868 

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LwsHaD1MDFAXc) 

### 성능 요약

메모리: 107,308 KB, 시간: 863 ms, 코드길이: 1,782 Bytes



> 출처: SW Expert Academy, https://swexpertacademy.com/main/code/problem/problemList.do

## 의식의 흐름

0 클릭 횟수 + 남은 부분 횟수
0 이 뭉쳐 있을 때는 어떻게 할 것인지?
일단 0인 곳 아무곳이나 클릭하면, 연쇄적으로 다른 곳도 쭉 켜질 테니까
그 부분을 추가적으로 queue 에서 뺄 때 그냥 카운트하지 않고 빼기만 하는 쪽으로 하자.

합쳐서 칸이 9만이었다. 하지만, 0인 것을 카운트하는 것을 기준으로 가서 조금 더 빨리 푸는 것이 가능하지 않았나 싶다. 
솔직히 완탐... 이 될까? 하고 아슬했던...? 케이스였지 않나 싶다. 
