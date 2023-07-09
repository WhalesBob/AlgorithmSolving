from sys import stdin

n, m, r = map(int, stdin.readline().split())
matrix = [list(map(int, stdin.readline().split())) for _ in range(n)]


def rotate_one_layer(matrix, start_x, start_y, end_x, end_y):
    previous_temp = matrix[start_y][start_x + 1]
    current_temp = matrix[start_y][start_x]
    next_temp = matrix[start_y + 1][start_x]
    for y in range(start_y, end_y):
        matrix[y][start_x] = previous_temp
        matrix[y + 1][start_x] = current_temp
        previous_temp, current_temp = current_temp, next_temp
        next_temp = matrix[y + 2][start_x] if y + 2 <= end_y else matrix[end_y][start_x + 1]

    for x in range(start_x, end_x):
        matrix[end_y][x] = previous_temp
        matrix[end_y][x + 1] = current_temp
        previous_temp, current_temp = current_temp, next_temp
        next_temp = matrix[end_y][x + 2] if x + 2 <= end_x else matrix[end_y - 1][end_x]

    for y in range(end_y, start_y, -1):
        matrix[y][end_x] = previous_temp
        matrix[y - 1][end_x] = current_temp
        previous_temp, current_temp = current_temp, next_temp
        next_temp = matrix[y - 2][end_x] if y - 2 >= start_y else matrix[start_y][end_x - 1]

    for x in range(end_x, start_x, -1):
        matrix[start_y][x] = previous_temp
        matrix[start_y][x - 1] = current_temp
        previous_temp, current_temp = current_temp, next_temp
        next_temp = matrix[start_y][x - 2] if x - 2 >= start_x else None

for i in range(r):
    s = 0
    while s < min(n, m) - s:
        rotate_one_layer(matrix, s, s, len(matrix[0]) - 1 - s, len(matrix) - 1 - s)
        s += 1

for i in range(len(matrix)):
    print(" ".join(map(str, matrix[i])))