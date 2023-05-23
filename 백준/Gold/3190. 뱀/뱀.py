from sys import stdin
from collections import deque

direct = deque([[1, 0], [0, 1], [-1, 0], [0, -1]])

n = int(stdin.readline())
matrix = [[0] * n for _ in range(n)]
apple_count = int(stdin.readline())
for i in range(apple_count):
    y, x = map(int, stdin.readline().split())
    matrix[y-1][x-1] = 2

operate_count = int(stdin.readline())
operate_queue = deque([list(map(str, stdin.readline().split())) for _ in range(operate_count)])

snake_head_axis = [0, 0]
matrix[0][0] = 1
time_count = 0
snake_body = deque([snake_head_axis])

def can_go(matrix, x, y):
    return 0 <= x < len(matrix[0]) and 0 <= y < len(matrix)


while True:

    time_count += 1
    current_to_go = direct[0]
    next_point_x, next_point_y = snake_head_axis[0] + current_to_go[0], snake_head_axis[1] + current_to_go[1]

    if not can_go(matrix, next_point_x, next_point_y) or matrix[next_point_y][next_point_x] == 1:
        break

    if matrix[next_point_y][next_point_x] != 2:
        last_x, last_y = snake_body.popleft()
        matrix[last_y][last_x] = 0

    matrix[next_point_y][next_point_x] = 1
    snake_head_axis = [next_point_x, next_point_y]
    snake_body.append(snake_head_axis)

    if len(operate_queue) != 0 and int(operate_queue[0][0]) == time_count:
        operate = operate_queue.popleft()
        if operate[1] == 'D':
            direct.rotate(-1)
        else:
            direct.rotate(1)

print(time_count)