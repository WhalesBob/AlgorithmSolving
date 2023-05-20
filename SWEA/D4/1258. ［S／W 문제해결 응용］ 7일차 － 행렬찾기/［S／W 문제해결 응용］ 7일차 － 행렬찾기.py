case = int(input())

def can_go(matrix, x, y):
    return 0 <= x < len(matrix[0]) and 0 <= y < len(matrix)

def take_rectangle(matrix, start_x, start_y):
    see_x, see_y = start_x,start_y
    while can_go(matrix, see_y+1, start_x) and matrix[see_y+1][start_x] != 0:
        see_y += 1

    while can_go(matrix, see_y, see_x + 1) and matrix[see_y][see_x+1] != 0:
        see_x += 1

    for idx_y in range(start_y, see_y + 1):
        for idx_x in range(start_x, see_x + 1):
            matrix[idx_y][idx_x] = 0

    len_x, len_y = see_x - start_x + 1, see_y - start_y + 1
    return len_y, len_x


for c in range(case):
    n = int(input())
    matrix = [list(map(int, input().split())) for _ in range(n)]

    answer = []
    for y in range(n):
        for x in range(n):
            if matrix[y][x] != 0:
                length_y, length_x = take_rectangle(matrix, x, y)
                answer.append([length_y, length_x])

    answer.sort(key=lambda k: (k[0]*k[1], k[0]))
    print("#{0} {1}".format(c+1, len(answer)), end=" ")
    for i in range(len(answer)):
        print("{0} {1}".format(answer[i][0], answer[i][1]), end="")
        if i < len(answer) - 1:
            print(" ", end="")
    if c < case - 1:
        print()