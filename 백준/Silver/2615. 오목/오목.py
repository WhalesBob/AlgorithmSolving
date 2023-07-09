from sys import stdin
import sys

matrix = [list(map(int, stdin.readline().split())) for _ in range(19)]
first_x, first_y = 0, 0
global is_decided
is_decided = False

column_all_set = set()

def can_go(x, y, matrix):
    return (0 <= x < len(matrix)) and (0 <= y < len(matrix))


def get_next_point(current_x, current_y, direction):
    if direction == 0:
        return current_x + 1, current_y
    elif direction == 1:
        return current_x, current_y + 1
    elif direction == 2:
        return current_x + 1, current_y + 1
    elif direction == 3:
        return current_x + 1, current_y - 1


def already_in_set(col_set):
    for element_set in column_all_set:
        if col_set.issubset(element_set):
            return True
    return False



def dfs(current_x, current_y, element, matrix, direction, count, column_set):
    global is_decided
    next_x, next_y = get_next_point(current_x, current_y, direction)
    if can_go(next_x, next_y, matrix) and matrix[next_y][next_x] == element:
        column_set.add((current_x, current_y))
        dfs(next_x, next_y, element, matrix, direction, count + 1, column_set)
    else:
        if not already_in_set(column_set):
            if count == 5:
                is_decided = True
                print(element)
                print(first_y, first_x)
                sys.exit(0)
            elif count > 5:
                column_all_set.add(frozenset(column_set))
        return


for x in range(19):
    for y in range(19):
        if matrix[y][x] != 0:
            first_x, first_y = x + 1, y + 1
            for d in range(4):
                dfs(x, y, matrix[y][x], matrix, d, 1, set())

if not is_decided:
    print(0)