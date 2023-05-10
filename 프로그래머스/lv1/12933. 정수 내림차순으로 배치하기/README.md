# [level 1] 정수 내림차순으로 배치하기 - 12933 

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/12933?language=python3) 

### 성능 요약

메모리: 10.4 MB, 시간: 0.02 ms

### 구분

코딩테스트 연습 > 연습문제

### 채점결과

Empty

### 문제 설명

<p>함수 solution은 정수 n을 매개변수로 입력받습니다. n의 각 자릿수를 큰것부터 작은 순으로 정렬한 새로운 정수를 리턴해주세요. 예를들어 n이 118372면 873211을 리턴하면 됩니다.</p>

<h5>제한 조건</h5>

<ul>
<li><code>n</code>은 1이상 8000000000 이하인 자연수입니다.</li>
</ul>

<h5>입출력 예</h5>
<table class="table">
        <thead><tr>
<th>n</th>
<th style="text-align: center">return</th>
</tr>
</thead>
        <tbody><tr>
<td>118372</td>
<td style="text-align: center">873211</td>
</tr>
</tbody>
      </table>

> 출처: 프로그래머스 코딩 테스트 연습, https://programmers.co.kr/learn/challenges

## 배운 점

- array.sort() 를 사용하면, 자동으로 정렬이 되며, array.sort(reverse=True) 하면 역방향으로 정렬한다. 
        - 이 때, string 배열이라도 상관없다

- ' '.join(s for s in array) 하면, 띄어쓰기를 지원하면서 array 의 내용물을 string 으로 모두 바꿔준다.
        - 띄어쓰기가 필요없으면, ''.join(s for s in array) 하면 되는듯 하다.
