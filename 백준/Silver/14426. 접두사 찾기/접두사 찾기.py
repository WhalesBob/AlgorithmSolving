from sys import stdin
from collections import deque

n, m = map(int, stdin.readline().split())

def dict_process(queue, dictionary):
    if len(queue) > 0:
        element_char = queue.popleft()
        if element_char not in dictionary:
            dictionary[element_char] = dict()
        dict_process(queue, dictionary[element_char])

def follow_dict(queue, dictionary):
    if len(queue) == 0:
        return True
    else:
        element = queue.popleft()
        if element not in dictionary:
            return False
        return follow_dict(queue, dictionary[element])

s_dict = dict()

for _ in range(n):
    char_array = deque([*(stdin.readline().rstrip())])
    dict_process(char_array, s_dict)

count = 0
for _ in range(m):
    char_array = deque([*(stdin.readline().rstrip())])
    if follow_dict(char_array, s_dict):
        count += 1

print(count)