from sys import stdin
from collections import deque

case = int(stdin.readline())

for c in range(case):
    max_profit = 0
    n = int(stdin.readline())
    array = list(map(int, stdin.readline().split()))
    stack = deque()

    stack.append(array[len(array) - 1])
    for i in range(len(array) - 2, -1, -1):
        if array[i] < stack[0]:
            max_profit += (stack[0] - array[i])
        else:
            stack.appendleft(array[i])
    print(max_profit)