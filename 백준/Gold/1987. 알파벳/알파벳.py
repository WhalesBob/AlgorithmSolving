from sys import stdin
import sys

r, c = map(int, stdin.readline().split())

matrix = [list(map(str, stdin.readline().rstrip())) for _ in range(r)]
global max_element_count
max_element_count = -1
direct = [[-1, 0], [0, -1], [1, 0], [0, 1]]

def can_go(x, y, matrix):
    return 0 <= x < len(matrix[0]) and 0 <= y < len(matrix)

def dfs(x, y, matrix, element_set):
    global max_element_count
    for i in range(4):
        new_x, new_y = x + direct[i][0], y + direct[i][1]
        if can_go(new_x, new_y, matrix) and matrix[new_y][new_x] not in element_set:
            element_set.add(matrix[new_y][new_x])
            dfs(new_x, new_y, matrix, element_set)
            element_set.remove(matrix[new_y][new_x])

    max_element_count = max(max_element_count, len(element_set))
    if max_element_count == 26:
        print(max_element_count)
        sys.exit(0)

dfs(0, 0, matrix,set(matrix[0][0]))
print(max_element_count)