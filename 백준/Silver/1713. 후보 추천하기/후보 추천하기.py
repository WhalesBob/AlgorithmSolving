from sys import stdin
import heapq

n = int(stdin.readline().strip())
total_suggest = int(stdin.readline().strip())
suggest_list = list(map(int, stdin.readline().split()))

heap_list = []


def check_and_get_element(heap_queue, e):
    for element in heap_queue:
        if element[2] == e:
            return element
    return None


time = 0
for element in suggest_list:
    element_tuple = check_and_get_element(heap_list, element)

    if element_tuple is not None:
        heap_list.remove(element_tuple)
        heapq.heappush(heap_list, (element_tuple[0] + 1, element_tuple[1], element_tuple[2]))
    else:
        if len(heap_list) >= n:
            heapq.heapify(heap_list)
            heapq.heappop(heap_list)
        heapq.heappush(heap_list, (1, time, element))

    time += 1

final_list = sorted([item[2] for item in heap_list])
for i in range(len(final_list)):
    if i < len(final_list) - 1:
        print(final_list[i], end=" ")
    else:
        print(final_list[i])