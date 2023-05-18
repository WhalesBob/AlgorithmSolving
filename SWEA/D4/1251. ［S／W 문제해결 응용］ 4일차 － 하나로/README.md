# [D4] [S/W 문제해결 응용] 4일차 - 하나로 - 1251 

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD) 

### 성능 요약

메모리: 63,392 KB, 시간: 235 ms, 코드길이: 1,110 Bytes



> 출처: SW Expert Academy, https://swexpertacademy.com/main/code/problem/problemList.do

## 생각의 흐름

인도네시아 내의 모든 섬을 해저터널로 연결하자 
그래프 

두 해저 터널이 교차되어도, 물리적으로는 연결되지 않는다. 
해저터널 건설로 인해 파괴되는 자연을 위해, 환경부담금 정책이 있다 

세율 (E) * 해저터널 길이 제곱(L**2) 의 곱 = E * L**2

총 환경 부담금을 최소화하여, N 개의 모든 섬을 연결할 수 있게 교통 시스템을 설계해라
이거 그냥, 다 연결되어 잇다 치고 다익스트라를 써서 문제를 풀어야 한다. 


정확하게는 다익스트라 변형
일단 A에서부터 시작하는것으로 잡자. 뭐 어차피 이어져야 하므로, 상관없다 

tunnel_distance = [0, 10000, 160000, 250000]
최소거리 : 10000

visited = [True, True, False, False]
tunnel_distance = [0, 10000, 160000, 160000]

visited = [True, True, True, False]
tunnel_dist = [0, 10000, 160000, 10000]

## 느낀 점

- 항상 문제를 마지막까지 꼼꼼히 보자. 반올림을 하라는지, 버림을 하라는지
- min_index -> 이왕 줄거면, 화끈하게 주자! 파이썬이잖아! 괜히 999_999 이딴짓 하기 없기.
