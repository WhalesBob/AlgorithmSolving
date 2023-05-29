from sys import stdin
from collections import deque

n, k = map(int, stdin.readline().split())
array = [int(stdin.readline()) for _ in range(n)]
stack = deque(array)

count = 0
while k > 0:
    element = stack.pop()
    if k // element > 0:
        count += (k // element)
        k -= (k // element) * element

print(count)