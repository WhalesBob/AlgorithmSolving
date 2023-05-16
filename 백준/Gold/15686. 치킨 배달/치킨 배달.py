from sys import stdin
from itertools import combinations

n, m = map(int, stdin.readline().split())
matrix = []
house = []
chicken = []
all_chicken_length = 10**100

for i in range(n):
    array = list(map(int, stdin.readline().split()))
    matrix.append(array)
    for j in range(0,n):
        if array[j] == 1:
            house.append([i,j])
        if array[j] == 2:
            chicken.append([i,j])

for chi in combinations(chicken, m):
    temp = 0
    for h in house:
        chi_len = 999
        for j in range(0, len(chi)):
            chi_len = min(chi_len, abs(h[0] - chi[j][0]) + abs(h[1] - chi[j][1]))
        temp += chi_len
    all_chicken_length = min(all_chicken_length, temp)

print(all_chicken_length)