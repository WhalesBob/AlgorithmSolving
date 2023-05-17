from sys import stdin

direct = [[0, -1], [1, 0], [0, 1], [-1, 0]]
n, m = map(int, stdin.readline().split())
global see
y, x, see = map(int, stdin.readline().split())

matrix = [list(map(int, stdin.readline().split())) for i in range(n)]

def four_line_all_clean(x, y):
    global see
    for i in range(1,5):
        next_see = see - i if see - i >= 0 else see - i + 4
        next_x = x + direct[next_see][0]
        next_y = y + direct[next_see][1]
        if matrix[next_y][next_x] == 0:
            see = next_see
            return see
    return -1

count = 0
while (True):
    if matrix[y][x] == 0:
        count += 1
        matrix[y][x] = count+1

    next_direction = four_line_all_clean(x, y)
    if next_direction == -1:
        retrieve_x = x - direct[see][0]
        retrieve_y = y - direct[see][1]
        if matrix[retrieve_y][retrieve_x] == 1:
            break
        else:
            x, y = retrieve_x, retrieve_y
            continue

    else:
        next_x = x + direct[see][0]
        next_y = y + direct[see][1]
        x, y = next_x, next_y

print(count)