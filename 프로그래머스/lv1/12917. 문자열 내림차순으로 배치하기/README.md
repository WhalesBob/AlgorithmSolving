# [level 1] 문자열 내림차순으로 배치하기 - 12917 

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/12917?language=python3) 

### 성능 요약

메모리: 10.2 MB, 시간: 0.01 ms

### 구분

코딩테스트 연습 > 연습문제

### 채점결과

Empty

### 문제 설명

<p>문자열 s에 나타나는 문자를 큰것부터 작은 순으로 정렬해 새로운 문자열을 리턴하는 함수, solution을 완성해주세요.<br>
s는 영문 대소문자로만 구성되어 있으며, 대문자는 소문자보다 작은 것으로 간주합니다.</p>

<h5>제한 사항</h5>

<ul>
<li>str은 길이 1 이상인 문자열입니다.</li>
</ul>

<h5>입출력 예</h5>
<table class="table">
        <thead><tr>
<th>s</th>
<th>return</th>
</tr>
</thead>
        <tbody><tr>
<td>"Zbcdefg"</td>
<td>"gfedcbZ"</td>
</tr>
</tbody>
      </table>

> 출처: 프로그래머스 코딩 테스트 연습, https://programmers.co.kr/learn/challenges

## 배운 점

- 아무래도, 파이썬에서는 문자열을 정렬하는 데 있어 아스키 코드의 방식을 따라가는가 보다.
        - 확실하지는 않다. 하지만, 대문자가 소문자보다 작은 것은 확실해 보인다.
- 알고 보니까, sorted(s) 와 같은 방식으로 이미 정렬된 문자열 배열을 받을 수 있다.
        - ''.join(sorted(s)) 가 가능하다는 이야기이다.
