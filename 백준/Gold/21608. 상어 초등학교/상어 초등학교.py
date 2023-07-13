from sys import stdin
import heapq

direction = [(-1, 0), (1, 0), (0, -1), (0, 1)]

n = int(stdin.readline())

stu_like_list = [list(map(int, stdin.readline().split())) for _ in range(n ** 2)]
stu_like_dict = dict()
matrix = [[0 for _ in range(n)] for _ in range(n)]

def can_go(x, y, matrix):
    return (0 <= x < len(matrix[0])) and (0 <= y < len(matrix))

def count_blank(x, y, matrix):
    count = 0
    for d in direction:
        see_x, see_y = x + d[0], y + d[1]
        if can_go(see_x, see_y, matrix) and matrix[see_y][see_x] == 0:
            count += 1
    return count

def count_like_stu(x, y, matrix, like_list):
    count = 0
    for d in direction:
        see_x, see_y = x + d[0], y + d[1]
        if can_go(see_x, see_y, matrix) and matrix[see_y][see_x] in like_list[1:]:
            count += 1
    return count

for student in stu_like_list:
    heap_list = []
    for y in range(len(matrix)):
        for x in range(len(matrix[0])):
            if matrix[y][x] == 0:
                heap_list.append((-1 * count_like_stu(x, y, matrix, student), -1 * count_blank(x, y, matrix), y, x))

    heapq.heapify(heap_list)
    pop_tuple = heapq.heappop(heap_list)
    matrix[pop_tuple[2]][pop_tuple[3]] = student[0]
    stu_like_dict[student[0]] = student

like_score = 0

for y in range(n):
    for x in range(n):
        count = count_like_stu(x, y, matrix, stu_like_dict[matrix[y][x]])
        like_score = like_score if count == 0 else like_score + (10 ** (count-1))

print(like_score)