from sys import stdin
from collections import deque

n, w, max_weight = map(int, stdin.readline().split())
truck_queue = deque(map(int, stdin.readline().split()))

bridge_weight = 0
bridge_queue = deque()
time = 0

while len(truck_queue) != 0:
    time += 1

    for element in bridge_queue:
        element[0] += 1

    while len(bridge_queue) > 0 and bridge_queue[0][0] > w:
        bridge_weight -= bridge_queue[0][1]
        bridge_queue.popleft()

    if len(truck_queue) > 0 and bridge_weight + truck_queue[0] <= max_weight:
        new_weight = truck_queue.popleft()
        bridge_queue.append([1, new_weight])
        bridge_weight += new_weight

while len(bridge_queue) > 0:
    time += 1
    for element in bridge_queue:
        element[0] += 1

    while len(bridge_queue) > 0 and bridge_queue[0][0] > w:
        bridge_weight -= bridge_queue[0][1]
        bridge_queue.popleft()

print(time)