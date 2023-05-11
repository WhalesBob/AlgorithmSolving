# [level 1] 삼총사 - 131705 

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/131705?language=python3) 

### 성능 요약

메모리: 10.2 MB, 시간: 0.22 ms

### 구분

코딩테스트 연습 > 연습문제

### 채점결과

Empty

### 문제 설명

<p>한국중학교에 다니는 학생들은 각자 정수 번호를 갖고 있습니다. 이 학교 학생 3명의 정수 번호를 더했을 때 0이 되면 3명의 학생은 삼총사라고 합니다. 예를 들어, 5명의 학생이 있고, 각각의 정수 번호가 순서대로 -2, 3, 0, 2, -5일 때, 첫 번째, 세 번째, 네 번째 학생의 정수 번호를 더하면 0이므로 세 학생은 삼총사입니다. 또한, 두 번째, 네 번째, 다섯 번째 학생의 정수 번호를 더해도 0이므로 세 학생도 삼총사입니다. 따라서 이 경우 한국중학교에서는 두 가지 방법으로 삼총사를 만들 수 있습니다.</p>

<p>한국중학교 학생들의 번호를 나타내는 정수 배열 <code>number</code>가 매개변수로 주어질 때, 학생들 중 삼총사를 만들 수 있는 방법의 수를 return 하도록 solution 함수를 완성하세요.</p>

<hr>

<h5>제한사항</h5>

<ul>
<li>3 ≤ <code>number</code>의 길이 ≤ 13</li>
<li>-1,000 ≤ <code>number</code>의 각 원소 ≤ 1,000</li>
<li>서로 다른 학생의 정수 번호가 같을 수 있습니다.</li>
</ul>

<hr>

<h5>입출력 예</h5>
<table class="table">
        <thead><tr>
<th>number</th>
<th>result</th>
</tr>
</thead>
        <tbody><tr>
<td>[-2, 3, 0, 2, -5]</td>
<td>2</td>
</tr>
<tr>
<td>[-3, -2, -1, 0, 1, 2, 3]</td>
<td>5</td>
</tr>
<tr>
<td>[-1, 1, -1, 1]</td>
<td>0</td>
</tr>
</tbody>
      </table>
<hr>

<h5>입출력 예 설명</h5>

<p><strong>입출력 예 #1</strong></p>

<ul>
<li>문제 예시와 같습니다.</li>
</ul>

<p><strong>입출력 예 #2</strong></p>

<ul>
<li>학생들의 정수 번호 쌍 (-3, 0, 3), (-2, 0, 2), (-1, 0, 1), (-2, -1, 3), (-3, 1, 2) 이 삼총사가 될 수 있으므로, 5를 return 합니다.</li>
</ul>

<p><strong>입출력 예 #3</strong></p>

<ul>
<li>삼총사가 될 수 있는 방법이 없습니다.</li>
</ul>


> 출처: 프로그래머스 코딩 테스트 연습, https://programmers.co.kr/learn/challenges

## 배운 점 : Combination 만들기 

#### Combination 만드는 것은, 저번에도 가로막혀서 문제를 똑바로 해결하지 못했을 만큼 유용하면서도 가끔씩 나오는 문제 해결 방법이다. 꼭 알아 놓도록 하자.

### Combination by BackTracking

```python
combination = []

def makeCombination(arr,visited,start,n,r):
    if(r == 0):
        combination.append(makeArray(arr,visited)) # combination 배열에 케이스 집어넣는 부분
        return
    
    for i in range(start, n):
        visited[i] = True # 방문한 부분만 True 로 표시한다.
        makeCombination(arr, visited, i+1, n, r-1) ## start 에 i+1 을 집어넣음으로써, 중복 방문을 피한다. 그리고 r 자리에 r-1 넣음으로써 몇개를 더 뽑아야 하는지를 알려 준다.
        ## 이렇게 해 주면, range 의 start 가 더 커지면서, 뽑아야 하는 범위를 계속 좁힌다.
        visited[i] = False ## 재귀를 빠져나왔으면, 다시 False 로 바꾸어 준다. 추후에 다른 재귀에서 영향이 받지 않게 해준다.
        
def makeArray(arr, visited):
    array = []
    for i in range(0, len(arr)):
        if(visited[i]): ## 걸려서 방문한 애들만 append 해 주는 부분이다.
            array.append(arr[i])
            
    return array        
```    
