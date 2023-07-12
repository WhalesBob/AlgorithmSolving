from sys import stdin

test_case = 0

direction_tuple_list = [(-1, 0), (-1, -1), (0, -1), (1, -1)]

def can_go(x, y, matrix):
    return (0 <= x < len(matrix[0])) and (0 <= y < len(matrix))


while True:
    n = int(stdin.readline())
    test_case += 1

    if n == 0:
        break

    matrix = [list(map(int, stdin.readline().split())) for _ in range(n)]

    matrix[0][2] += matrix[0][1]
    matrix[0][0] = 999_999_999

    for y in range(1, n):
        for x in range(3):
            min_list = []
            for element in direction_tuple_list:
                prev_x, prev_y = x + element[0], y + element[1]
                if can_go(prev_x, prev_y, matrix):
                    min_list.append(matrix[prev_y][prev_x])

            matrix[y][x] += min(min_list)

    print("{0}. {1}".format(test_case, matrix[len(matrix) - 1][1]))