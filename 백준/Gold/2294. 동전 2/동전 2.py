from sys import stdin
from collections import deque

n, m = map(int, stdin.readline().split())
coin = set()
for i in range(n):
    coin.add(int(stdin.readline()))

array = [-1] * (m+1)
queue = deque()

array[0] = 0
queue.append(0)
while array[m] == -1 and len(queue) != 0:
    current_idx = queue.popleft()
    for element in coin:
        next_idx = current_idx + element
        if next_idx <= m and array[next_idx] == -1:
            array[next_idx] = array[current_idx] + 1
            queue.append(next_idx)

print(array[m])