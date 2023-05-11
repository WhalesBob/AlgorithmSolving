# [level 1] 최대공약수와 최소공배수 - 12940 

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/12940?language=python3) 

### 성능 요약

메모리: 10.2 MB, 시간: 0.00 ms

### 구분

코딩테스트 연습 > 연습문제

### 채점결과

Empty

### 문제 설명

<p>두 수를 입력받아 두 수의 최대공약수와 최소공배수를 반환하는 함수, solution을 완성해 보세요. 배열의 맨 앞에 최대공약수, 그다음 최소공배수를 넣어 반환하면 됩니다. 예를 들어 두 수 3, 12의 최대공약수는 3, 최소공배수는 12이므로 solution(3, 12)는 [3, 12]를 반환해야 합니다.</p>

<h5>제한 사항</h5>

<ul>
<li>두 수는 1이상 1000000이하의 자연수입니다.</li>
</ul>

<h5>입출력 예</h5>
<table class="table">
        <thead><tr>
<th>n</th>
<th>m</th>
<th>return</th>
</tr>
</thead>
        <tbody><tr>
<td>3</td>
<td>12</td>
<td>[3, 12]</td>
</tr>
<tr>
<td>2</td>
<td>5</td>
<td>[1, 10]</td>
</tr>
</tbody>
      </table>
<h5>입출력 예 설명</h5>

<p>입출력 예 #1<br>
위의 설명과 같습니다.</p>

<p>입출력 예 #2<br>
자연수 2와 5의 최대공약수는 1, 최소공배수는 10이므로 [1, 10]을 리턴해야 합니다.</p>


> 출처: 프로그래머스 코딩 테스트 연습, https://programmers.co.kr/learn/challenges

## 배운 점 : 유클리드 호제법

### A 와 B의 최대공약수는, A = BQ + R 에서 B 와 R의 최대공약수와 같다

증명 : https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=papers&logNo=140207307545

- 임의의 최대공약수가 있다 치고, 그로 나누어진 두개의 수가 서로소임을 증명하는 과정이다. 
- 귀류법으로 이루어지며, 반대의 케이스가 모순임을 잡으면 증명된다.

#### 최대공약수, 최소공배수는 언제나 나올 수 있는 수학적인 도구이니, 유클리드 호제법은 항상 알아 두도록 하자.
