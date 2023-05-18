# [D4] [S/W 문제해결 응용] 4일차 - 보급로 - 1249 

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD) 

### 성능 요약

메모리: 65,748 KB, 시간: 304 ms, 코드길이: 1,071 Bytes



> 출처: SW Expert Academy, https://swexpertacademy.com/main/code/problem/problemList.do

## 생각의 흐름

BFS 로 업데이트 하되, 기존의 누적거리를 엄청 높게 주고 
min() 으로 해 주면서, 더 작게 되는 애들만 queue 에 넣는다.
