from sys import stdin, stdout
import sys

sys.setrecursionlimit(10 ** 4)
n, m = map(int, stdin.readline().split())
root_list = list(range(n + 1))

def find(x):
    if root_list[x] == x:
        return x
    else:
        root_list[x] = find(root_list[x])
        return root_list[x]

def union(x, y):
    x, y = find(x), find(y)
    root_list[y] = x

def write_if_same_set(a, b):
    return "YES" if find(a) == find(b) else "NO"

for i in range(m):
    oper, a, b = map(int, stdin.readline().split())
    if oper == 0:
        union(a, b)
    else:
        stdout.write(write_if_same_set(a, b) + "\n")