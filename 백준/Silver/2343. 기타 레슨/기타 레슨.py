import math
from sys import stdin

n, m = map(int, stdin.readline().split())
lecture = list(map(int, stdin.readline().split()))

def is_good_to_save(limit_time):
    array = [0 for _ in range(m)]
    index = 0
    for element in lecture:
        if array[index] + element <= limit_time:
            array[index] += element
        elif index + 1 < len(array):
            index += 1
            array[index] += element
        else:
            array[index] += element
    return max(array) <= limit_time

right = sum(lecture)
left = max(lecture)

while left <= right:
    mid = math.ceil((left + right) / 2)
    if is_good_to_save(mid):
        right = mid - 1
    else:
        left = mid + 1

print(left)