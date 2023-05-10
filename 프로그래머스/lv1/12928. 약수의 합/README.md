# [level 1] 약수의 합 - 12928 

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/12928?language=python3) 

### 성능 요약

메모리: 10.3 MB, 시간: 0.02 ms

### 구분

코딩테스트 연습 > 연습문제

### 채점결과

Empty

### 문제 설명

<p>정수 n을 입력받아 n의 약수를 모두 더한 값을 리턴하는 함수, solution을 완성해주세요.</p>

<h5>제한 사항</h5>

<ul>
<li><code>n</code>은 0 이상 3000이하인 정수입니다.</li>
</ul>

<h5>입출력 예</h5>
<table class="table">
        <thead><tr>
<th>n</th>
<th>return</th>
</tr>
</thead>
        <tbody><tr>
<td>12</td>
<td>28</td>
</tr>
<tr>
<td>5</td>
<td>6</td>
</tr>
</tbody>
      </table>
<h6>입출력 예 설명</h6>

<p>입출력 예 #1<br>
12의 약수는 1, 2, 3, 4, 6, 12입니다. 이를 모두 더하면 28입니다.</p>

<p>입출력 예 #2<br>
5의 약수는 1, 5입니다. 이를 모두 더하면 6입니다.</p>


> 출처: 프로그래머스 코딩 테스트 연습, https://programmers.co.kr/learn/challenges

## 배운 점
- Set 자료구조는 s = set() 와 같은 방법으로 선언한다.
- 올림을 사용하는 방법은 math.ceil(), 제곱근은 math.sqrt()
- 하나씩 set에 집어넣는 방법은 s.add(). 이때는 원소를 한개씩 넣는다
- 여러 개 set에 한번에 집어넣을 수 있다. 이때는, set.update() 를 사용하며, 대괄호로 감싸주기만 하면 된다.
