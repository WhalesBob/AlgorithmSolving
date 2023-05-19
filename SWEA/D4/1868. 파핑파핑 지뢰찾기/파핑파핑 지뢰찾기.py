from collections import deque

case = int(input())
direct = [[-1,-1],[0,-1],[1,-1], [-1,0],[1,0],[-1,1],[0,1],[1,1]]

def can_go(matrix, x, y):
    return 0 <= x < len(matrix[0]) and 0 <= y < len(matrix)

def all_clear(matrix, x, y):
    for i in range(8):
        next_x = x + direct[i][0]
        next_y = y + direct[i][1]
        if can_go(matrix, next_x, next_y) and matrix[next_y][next_x] == '*':
            return False
    return matrix[y][x] != '*'

def clear_others(matrix, x, y):
    global non_zero_count
    matrix[y][x] = '0'
    queue = deque()
    queue.append([x,y])
    while len(queue) != 0:
        new_x, new_y = queue.popleft()
        for i in range(8):
            next_x, next_y = new_x + direct[i][0], new_y + direct[i][1]
            if can_go(matrix, next_x, next_y) and matrix[next_y][next_x] != '0':
                matrix[next_y][next_x] = '0'
                if all_clear(matrix, next_x, next_y):
                    queue.append([next_x, next_y])
                else:
                    non_zero_count -= 1

for c in range(case):
    n = int(input())
    matrix = []
    zero_point = deque()
    global non_zero_count, answer
    non_zero_count = 0
    for i in range(n):
        matrix.append(list(map(str, input())))

    for y in range(n):
        for x in range(n):
            if all_clear(matrix, x, y):
                zero_point.append([x,y])
            elif matrix[y][x] != '*':
                non_zero_count += 1
            else:
                pass

    answer = 0
    while len(zero_point) != 0:
        x,y = zero_point.popleft()
        if matrix[y][x] == '0':
            continue
        else:
            answer += 1
            clear_others(matrix, x, y)

    answer += non_zero_count
    print("#{0} {1}".format(c+1,answer))