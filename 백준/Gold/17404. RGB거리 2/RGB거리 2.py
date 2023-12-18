from sys import stdin
from copy import deepcopy

length = int(stdin.readline())
global inf
inf = 10 ** 100
input_matrix = [list(map(int, stdin.readline().split())) for _ in range(length)]

answer = inf

try_case = [(i, j) for j in range(3) for i in range(3) if i != j]


# print(input_matrix)

def get_try_matrix(matrix, a, b):
    return_matrix = deepcopy(matrix)
    for x in range(3):
        if x != a:
            return_matrix[0][x] = inf

    for x in range(3):
        if x != b:
            return_matrix[len(matrix) - 1][x] = inf

    return return_matrix


def get_minimum(matrix):
    for y in range(1, len(matrix)):
        for x in range(0, 3):
            compare_a = matrix[y - 1][(x + 1) % 3]
            compare_b = matrix[y - 1][(x + 2) % 3]
            matrix[y][x] += min(compare_a, compare_b)

    return min(matrix[len(matrix) - 1])


for (i, j) in try_case:
    matrix = get_try_matrix(input_matrix, i, j)
    answer = min(answer, get_minimum(matrix))

print(answer)
