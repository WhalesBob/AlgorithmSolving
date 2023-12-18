from sys import stdin
from collections import deque
import heapq

def get_need_build(target, set_list):
    result_set, need_queue = set(), deque()
    need_queue.append(target)
    result_set.add(target)

    while len(need_queue) > 0:
        element = need_queue.popleft()

        for e in set_list[element]:
            if e not in result_set:
                result_set.add(e)
                need_queue.append(e)

    return result_set

def get_build_time(target, time_list, set_list, next_list):
    need_to_build = get_need_build(target, set_list)
    result = 0

    const_heap = [[time_list[i], i] for i in range(1, len(set_list)) if len(set_list[i]) == 0]
    heapq.heapify(const_heap)

    while len(const_heap) > 0:
        time, element = heapq.heappop(const_heap)

        for i in range(len(const_heap)):
            const_heap[i][0] -= time

        result += time

        for next_element in next_list[element]:
            set_list[next_element].remove(element)
            if next_element in need_to_build and len(set_list[next_element]) == 0:
                heapq.heappush(const_heap, [time_list[next_element], next_element])

    return result

test_case = int(stdin.readline())

for _ in range(test_case):
    build_cnt, rule_cnt = map(int, stdin.readline().split())
    build_time = list(map(int, stdin.readline().split()))

    next_list = [set() for _ in range(build_cnt)]
    prev_set_list = [set() for _ in range(build_cnt)]

    build_time.insert(0, None)
    prev_set_list.insert(0, None)
    next_list.insert(0, None)

    for _ in range(rule_cnt):
        start, end = map(int, stdin.readline().split())
        prev_set_list[end].add(start)
        next_list[start].add(end)

    target = int(stdin.readline())
    need_time = get_build_time(target, build_time, prev_set_list, next_list)
    print(need_time)