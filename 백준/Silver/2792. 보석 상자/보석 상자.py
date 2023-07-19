from sys import stdin
import math

n, m = map(int, stdin.readline().split())
jew_list = sorted([int(stdin.readline()) for _ in range(m)], reverse=True)

left, right, answer = 1, jew_list[0], 0

while left <= right:
    mid = math.ceil((left + right) / 2)
    count = 0

    for i in range(m):
        count += (jew_list[i] // mid)
        if jew_list[i] % mid != 0:
            count += 1

    if count <= n:
        right = mid - 1
        answer = mid
    else:
        left = mid + 1
print(answer)