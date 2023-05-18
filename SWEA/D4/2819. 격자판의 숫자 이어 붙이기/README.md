# [D4] 격자판의 숫자 이어 붙이기 - 2819 

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7I5fgqEogDFAXB) 

### 성능 요약

메모리: 69,892 KB, 시간: 461 ms, 코드길이: 728 Bytes



> 출처: SW Expert Academy, https://swexpertacademy.com/main/code/problem/problemList.do

## 느낀 점

- 4 by 4로 한정되어 있었던 점. 재귀를 돌아도, 어차피 7개가 끝인 점, 동서남북 4개씩 재귀를 돌아도, 4^6 = 2^12 = 4096 이었던 점.
- 합쳐서 보는 것이 10만이 되지 않았던 점이 완전탐색의 힌트였다!
