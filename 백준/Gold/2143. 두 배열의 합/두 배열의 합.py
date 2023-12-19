from sys import stdin

target = int(stdin.readline())
length_a = int(stdin.readline())
A = list(map(int, stdin.readline().split()))

length_b = int(stdin.readline())
B = list(map(int, stdin.readline().split()))

matrix_a = [[0 for x in range(length_a)] for y in range(length_a)]
matrix_b = [[0 for x in range(length_b)] for y in range(length_b)]

def set_matrix_get_dict(matrix, arr):
    result_dict = dict()

    for y in range(len(matrix)):
        for x in range(y, len(matrix)):
            matrix[y][x] = matrix[y][x - 1] + arr[x]

            if matrix[y][x] in result_dict:
                result_dict[matrix[y][x]] += 1
            else:
                result_dict[matrix[y][x]] = 1

    return result_dict

dict_A = set_matrix_get_dict(matrix_a, A)
dict_B = set_matrix_get_dict(matrix_b, B)

cnt = 0

for key in dict_A:
    to_find = target - key
    if to_find in dict_B:
        cnt += (dict_A[key] * dict_B[to_find])

print(cnt)