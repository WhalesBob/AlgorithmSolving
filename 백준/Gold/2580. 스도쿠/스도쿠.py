from sys import stdin, stdout
import sys
from collections import deque

matrix = []
to_fill = []
queue = deque()
rect = [set() for _ in range(9)]

for y in range(9):
    array = list(map(int, stdin.readline().split()))
    matrix.append(array)
    for x in range(9):
        if array[x] == 0:
            queue.append([x, y])

y_axis_set = [set([matrix[i][j] for i in range(9)]) for j in range(9)]

for y in range(9):
    for x in range(9):
        rect[(y // 3) * 3 + (x // 3)].add(matrix[y][x])

def print_matrix(matrix):
    for arr in matrix:
        for i in range(9):
            stdout.write(str(arr[i]))
            if (i < 8):
                stdout.write(" ")
        stdout.write("\n")

def can_be_there(value, x, y, matrix):
    can_put_x_axis = value not in matrix[y]
    can_put_y_axis = value not in y_axis_set[x]
    can_put_rect = value not in rect[(y // 3) * 3 + (x // 3)]
    return can_put_x_axis and can_put_y_axis and can_put_rect

def fill_value(x, y, value):
    matrix[y][x] = value
    y_axis_set[x].add(value)
    rect[(y // 3) * 3 + (x // 3)].add(value)

def fill_sudoku(matrix, index, fill):
    if index == len(fill):
        print_matrix(matrix)
        sys.exit(0)

    x, y = to_fill[index]
    for value in range(1, 10):
        if can_be_there(value, x, y, matrix):
            fill_value(x, y, value)
            fill_sudoku(matrix, index + 1, fill)

            matrix[y][x] = 0
            y_axis_set[x].remove(value)
            rect[(y // 3) * 3 + (x // 3)].remove(value)

def get_not_have(arr):
    for v in range(1, 10):
        if v not in arr:
            return v
    return None


while len(queue) != 0:
    element_x, element_y = queue.popleft()
    if matrix[element_y].count(0) == 1:
        fill_value(element_x, element_y, get_not_have(matrix[element_y]))
    elif len(y_axis_set[element_x]) == 9:
        fill_value(element_x, element_y, get_not_have(y_axis_set[element_x]))
    elif len(rect[(element_y // 3) * 3 + (element_x // 3)]) == 9:
        fill_value(element_x, element_y, get_not_have(rect[(element_y // 3) * 3 + (element_x // 3)]))
    else:
        to_fill.append([element_x, element_y])

if len(to_fill) != 0:
    fill_sudoku(matrix, 0, to_fill)
else:
    print_matrix(matrix)