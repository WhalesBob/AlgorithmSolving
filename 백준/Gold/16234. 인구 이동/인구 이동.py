from sys import stdin
import math

n, l, r = map(int, stdin.readline().split(" "))
global root

population = [0]
for i in range(n):
    population.extend(list(map(int, stdin.readline().split(" "))))


def find(x):
    global root
    if root[x] == x:
        return x
    else:
        root[x] = find(root[x])
        return root[x]

def union(x,y):
    x, y = find(x), find(y)
    x, y = min(x, y), max(x, y)
    root[y] = x

def move_population():
    global root
    pop_dict = {}
    index_dict = {}
    for i in range(1, n**2 + 1):
        if root[i] in pop_dict:
            pop_dict[root[i]].append(population[i])
            index_dict[root[i]].append(i)
        else:
            pop_dict[root[i]] = [population[i]]
            index_dict[root[i]] = [i]

    for key, value in pop_dict.items():
        if len(value) != 1:
            aver = math.floor(sum(value) / len(value))
            for idx in index_dict[key]:
                population[idx] = aver

def root_check():
    global root
    for i in range(1, n ** 2 + 1):
        root[i] = find(i)

count = 0
while(True):
    root = list(range(0, n ** 2 + 1))
    is_open = False
    for index in range(1, n ** 2 + 1):
        down_idx = (index + n) if (index + n) <= n ** 2 else None
        right_idx = (index + 1) if index % n != 0 else None

        if down_idx is not None and l <= abs(population[index] - population[down_idx]) <= r:
            union(index, down_idx)
            is_open = True
        if right_idx is not None and l <= abs(population[index] - population[right_idx]) <= r:
            union(index, right_idx)
            is_open = True

    if is_open:
        root_check()
        move_population()
        count += 1
    else:
        break

print(count)