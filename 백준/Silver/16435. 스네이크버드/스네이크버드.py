from sys import stdin

n, length = map(int, stdin.readline().split())
array = sorted(list(map(int, stdin.readline().split())))

for i in range(len(array)):
    if array[i] <= length:
        length += 1
    else:
        break
print(length)