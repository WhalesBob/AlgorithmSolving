from sys import stdin
from itertools import combinations

n, m, k = map(int, stdin.readline().split())
k = min(n * m, k)

matrix = [list(map(int, stdin.readline().split())) for _ in range(n)]

def is_close(point_1, point_2):
    return abs(point_1[0] - point_2[0]) + abs(point_1[1] - point_2[1]) < 2

def all_not_close(element_list):
    s = set()
    for element in element_list:
        for compare in s:
            if is_close(element, compare):
                return False
        s.add(element)
    return True

iter_list = [(x, y) for x in range(m) for y in range(n)]

number_set = set()
for element_array in combinations(iter_list, k):
    number_sum = 0
    if all_not_close(element_array):
        for element in element_array:
            number_sum += matrix[element[1]][element[0]]
        number_set.add(number_sum)

print(max(number_set))