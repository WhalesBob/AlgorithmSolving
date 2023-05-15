from sys import stdin
import sys
from collections import deque

a, b, c = map(int, stdin.readline().split())

big_matrix = []
queue = deque()
have_zero = False

for height in range(c):
    temp = []
    for h in range(b):
        array = list(map(int, stdin.readline().split()))
        temp.append(array)
        have_zero = True if have_zero or (0 in array) else False
        for w in range(a):
            if array[w] == 1:
                queue.append([w, h, height])
    big_matrix.append(temp)

if not have_zero:
    print(0)
    sys.exit(0)

direct = [[-1, 0, 0], [0, -1, 0], [1, 0, 0], [0, 1, 0], [0, 0, 1], [0, 0, -1]]

def can_go(x, y, z):
    return (0 <= x < a) and (0 <= y < b) and (0 <= z < c) and big_matrix[z][y][x] == 0

def spread():
    count = 0
    while len(queue) != 0:
        insert = False
        current_queue_size = len(queue)
        for i in range(current_queue_size):
            current_loc = queue.popleft()
            current_loc_x, current_loc_y, current_loc_z = current_loc[0], current_loc[1], current_loc[2]
            for d in direct:
                next_x, next_y, next_z = current_loc_x + d[0], current_loc_y + d[1], current_loc_z + d[2]
                if can_go(next_x, next_y, next_z):
                    insert = True
                    big_matrix[next_z][next_y][next_x] = 1
                    queue.append([next_x, next_y, next_z])
        if insert:
            count += 1

    for z in range(c):
        for y in range(b):
            if 0 in big_matrix[z][y]:
                return -1

    return count

print(spread())