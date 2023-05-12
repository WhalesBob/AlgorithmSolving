# [level 2] 삼각 달팽이 - 68645 

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/68645) 

### 성능 요약

메모리: 41.2 MB, 시간: 67.96 ms

### 구분

코딩테스트 연습 > 월간 코드 챌린지 시즌1

### 채점결과

Empty

### 문제 설명

<p>정수 n이 매개변수로 주어집니다. 다음 그림과 같이 밑변의 길이와 높이가 n인 삼각형에서 맨 위 꼭짓점부터 반시계 방향으로 달팽이 채우기를 진행한 후, 첫 행부터 마지막 행까지 모두 순서대로 합친 새로운 배열을 return 하도록 solution 함수를 완성해주세요.</p>

<p><img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/e1e53b93-dcdf-446f-b47f-e8ec1292a5e0/examples.png" title="" alt="examples.png"></p>

<hr>

<h5>제한사항</h5>

<ul>
<li>n은 1 이상 1,000 이하입니다.</li>
</ul>

<hr>

<h5>입출력 예</h5>
<table class="table">
        <thead><tr>
<th>n</th>
<th>result</th>
</tr>
</thead>
        <tbody><tr>
<td>4</td>
<td><code>[1,2,9,3,10,8,4,5,6,7]</code></td>
</tr>
<tr>
<td>5</td>
<td><code>[1,2,12,3,13,11,4,14,15,10,5,6,7,8,9]</code></td>
</tr>
<tr>
<td>6</td>
<td><code>[1,2,15,3,16,14,4,17,21,13,5,18,19,20,12,6,7,8,9,10,11]</code></td>
</tr>
</tbody>
      </table>
<hr>

<h5>입출력 예 설명</h5>

<p>입출력 예 #1</p>

<ul>
<li>문제 예시와 같습니다.</li>
</ul>

<p>입출력 예 #2</p>

<ul>
<li>문제 예시와 같습니다.</li>
</ul>

<p>입출력 예 #3</p>

<ul>
<li>문제 예시와 같습니다.</li>
</ul>

> 출처: 프로그래머스 코딩 테스트 연습, https://programmers.co.kr/learn/challenges

## 생각정리


        0
        1 2
        3 4 5
        6 7 8 9

        -> 0 1 3 6 -> 7 8 9 -> 5 2 -> 4

        0 1 3 6 은 수학적으로 그냥 계차수열이다.
        뒤에 7 8 9 는 그냥 더해준것이다
        뒤에는 -4 -3 한것이다.
        그리고 +2

        0
        1 2
        3 4 5
        6 7 8 9
        10 11 12 13 14

        -> 0 1 3 6 10 -> 11 12 13 14 -> 9 5 2 -> 4 7 -> 8

        더해주는 값 컨트롤. 1 2 3 4 -> 1 1 1 1 -> -5 -4 -3 -> 2 3 -> 1
