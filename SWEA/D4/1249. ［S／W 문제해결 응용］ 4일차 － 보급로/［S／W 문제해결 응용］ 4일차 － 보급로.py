from collections import deque

direct = [[-1,0],[0,-1],[1,0],[0,1]]
case = int(input())

def can_go(x,y, matrix):
    return 0 <= x < len(matrix[0]) and 0 <= y < len(matrix)

def restore(matrix, fix_matrix):
    queue = deque()
    queue.append([0,0])
    while len(queue) != 0:
        curr_x, curr_y = queue.popleft()
        for i in range(4):
            next_x, next_y = curr_x + direct[i][0], curr_y + direct[i][1]
            if can_go(next_x, next_y, matrix):
                next_value = fix_matrix[curr_y][curr_x] + matrix[next_y][next_x]
                if can_go(next_x, next_y, matrix) and next_value < fix_matrix[next_y][next_x]:
                    fix_matrix[next_y][next_x] = next_value
                    queue.append([next_x, next_y])

for t in range(case):
    length = int(input())
    matrix = [list(map(int, input())) for _ in range(length)]
    fix_matrix = [[9999 for _ in range(length)] for _ in range(length)]
    fix_matrix[0][0] = matrix[0][0]
    restore(matrix, fix_matrix)
    print("#{0} {1}".format(t+1, fix_matrix[length-1][length-1]))