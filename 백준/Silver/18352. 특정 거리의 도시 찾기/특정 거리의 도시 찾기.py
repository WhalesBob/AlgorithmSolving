import sys
from sys import stdin
def makeDict(count):
    d = dict()

    for c in range(0, count):
        a, b = map(int, stdin.readline().split())
        if(a in d):
            d[a].append(b)
        else:
            d[a] = [b]
    return d

N,M,dist,start = map(int, stdin.readline().split(" "))
if(dist == 0):
    print(start)
    sys.exit(0)

adjacencyMap = makeDict(M)
visited = [False] * (N+1)

visited[start] = True
d = dict()

queue = [start]
d[start] = 0
count = 0
for a in range(0, dist):
    count += 1
    queueSize = len(queue)
    for i in range(0, queueSize):
        city = queue.pop(0)
        if(city not in adjacencyMap):
            continue
        for c in adjacencyMap[city]:
            if (not visited[c]):
                visited[c] = True
                queue.append(c)
                d[c] = count

answer = []
for key,value in d.items():
    if(value == dist):
        answer.append(key)

if(len(answer) == 0):
    answer.append(-1)

answer.sort()
for a in answer :
    print(a)