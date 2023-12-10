from sys import stdin

n, m = map(int, stdin.readline().split())
homes_road = []

for _ in range(m):
    a, b, road_length = map(int, stdin.readline().split())
    homes_road.append((a, b, road_length))

homes_road.sort(key=lambda road: road[2])

global parent
parent = [i for i in range(n + 1)]

def union(a, b):
    global parent
    pa = parent[a]
    pb = parent[b]
    parent[pb] = pa

def find(x):
    if x == parent[x]:
        return x

    parent[x] = find(parent[x])
    return parent[x]

count, length_sum = 0, 0

for start, end, weight in homes_road:
    if count >= n - 2:
        break
        
    if find(start) != find(end):
        union(start, end)
        count += 1
        length_sum += weight

print(length_sum)