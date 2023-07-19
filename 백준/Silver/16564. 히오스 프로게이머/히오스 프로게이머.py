from sys import stdin

n, k = map(int, stdin.readline().split())
level_list = sorted([int(stdin.readline()) for _ in range(n)])

def available(remain_level, dest):
    for element in level_list:
        if element >= dest:
            break
        remain_level -= (dest - element)
        if remain_level < 0:
            return False
    return True

left, right, answer = level_list[0], level_list[0] + k, 0

while left <= right:
    mid = (left + right) // 2
    if available(k, mid):
        answer = mid
        left = mid + 1
    else:
        right = mid - 1
print(answer)