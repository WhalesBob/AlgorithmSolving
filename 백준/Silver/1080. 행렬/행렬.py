from sys import stdin

n, m = map(int, stdin.readline().split())

A = [list(stdin.readline().rstrip()) for _ in range(n)]
B = [list(stdin.readline().rstrip()) for _ in range(n)]

matrix_A = [[int(A[y][x]) for x in range(len(A[0]))] for y in range(len(A))]
matrix_B = [[int(B[y][x]) for x in range(len(B[0]))] for y in range(len(B))]

def compare_two_matrix(matrix_a, matrix_b):
    for y in range(len(matrix_a)):
        for x in range(len(matrix_a[0])):
            if matrix_a[y][x] != matrix_b[y][x]:
                return False

    return True

def change_matrix(matrix, start_x, start_y):
    end_y = min(len(matrix), start_y + 3)
    end_x = min(len(matrix[0]), start_x + 3)
    for y in range(start_y, end_y):
        for x in range(start_x, end_x):
            matrix[y][x] = (matrix[y][x] + 1) % 2

max_y = n - 3 + 1
max_x = m - 3 + 1
count = 0
for y in range(max_y):
    for x in range(max_x):
        if matrix_A[y][x] != matrix_B[y][x]:
            change_matrix(matrix_A, x, y)
            count += 1

print(count if compare_two_matrix(matrix_A, matrix_B) else -1)