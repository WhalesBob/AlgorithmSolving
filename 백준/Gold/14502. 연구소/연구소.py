from sys import stdin
from collections import deque
from itertools import combinations
from copy import deepcopy

n, m = map(int, stdin.readline().split(" "))
matrix = []

virus_queue = deque()
zero_list = []

max_of_safe = -1
direct = [[-1, 0], [0, -1], [1, 0], [0, 1]]


def can_go(matrix, x, y):
    return 0 <= x < len(matrix[0]) and 0 <= y < len(matrix)

def take_safe_count(matrix, zero_count):
    virus_copy_queue = deepcopy(virus_queue)
    sample_matrix = deepcopy(matrix)

    while len(virus_copy_queue) != 0:
        x, y = virus_copy_queue.popleft()
        for i in range(4):
            new_x, new_y = x + direct[i][0], y + direct[i][1]
            if can_go(sample_matrix, new_x, new_y) and sample_matrix[new_y][new_x] == 0:
                sample_matrix[new_y][new_x] = 2
                virus_copy_queue.append([new_x, new_y])
                zero_count -= 1

    return zero_count

for y in range(n):
    array = list(map(int, stdin.readline().split(" ")))
    matrix.append(array)
    for x in range(len(array)):
        if array[x] == 2:
            virus_queue.append([x, y])
        elif array[x] == 0:
            zero_list.append([x, y])

for element in combinations(zero_list, 3):
    for e in element:
        put_x, put_y = e
        matrix[put_y][put_x] = 1

    max_of_safe = max(max_of_safe, take_safe_count(matrix,len(zero_list) - 3))

    for e in element:
        put_x, put_y = e
        matrix[put_y][put_x] = 0

print(max_of_safe)