from sys import stdin
from collections import deque

left_chain = 6
right_chain = 2

queue_matrix = [deque(list(map(int, stdin.readline().rstrip()))) for _ in range(4)]
c = int(stdin.readline())

for _ in range(c):
    n, rotate = map(int, stdin.readline().split(" "))
    rotate_info = [0] * 4
    n -= 1
    rotate_info[n] = rotate
    for i in range(n, 0, -1):
        changed_idx = i-1
        if queue_matrix[i][left_chain] != queue_matrix[changed_idx][right_chain]:
            rotate_info[changed_idx] = (rotate_info[i] * -1)
        else:
            break

    for i in range(n, 3):
        changed_idx = i+1
        if queue_matrix[i][right_chain] != queue_matrix[changed_idx][left_chain]:
            rotate_info[changed_idx] = (rotate_info[i] * -1)

    for i in range(4):
        if rotate_info[i] != 0:
            queue_matrix[i].rotate(rotate_info[i])

answer = 0
for i in range(4):
    answer += (queue_matrix[i][0] * 2**(i))

print(answer)