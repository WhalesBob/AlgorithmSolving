from sys import stdin
from collections import deque

n, m, v = map(int, stdin.readline().split())
visited = [False for i in range(n + 1)]

matrix = [[0 for j in range(n + 1)] for _ in range(n + 1)]

for i in range(m):
    a, b = map(int, stdin.readline().split())
    matrix[a][b] = matrix[b][a] = 1


def dfs(element, matrix):
    visited[element] = True
    print(element, end=" ")
    for i in range(len(matrix)):
        if matrix[element][i] == 1 and not visited[i]:

            dfs(i, matrix)


def bfs(start, matrix):
    queue = deque()
    queue.append(start)
    print(start, end=" ")
    visited[start] = True
    while len(queue) != 0:
        element = queue.popleft()
        visited[element] = True
        for i in range(1, len(matrix)):
            if matrix[element][i] == 1 and not visited[i]:
                visited[i] = True
                print(i, end=" ")
                queue.append(i)


dfs(v, matrix)
print()
visited = [False for i in range(n + 1)]
bfs(v, matrix)