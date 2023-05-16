from collections import deque

def solution(priorities, location):
    priority_queue = deque(priorities)
    priority = deque(sorted(priorities,reverse=True))
    top_priority = priority.popleft()
    queue = deque([chr(x) for x in range (65, 65+len(priorities))])

    count = 0
    while(len(queue) != 0):
        tmp = priority_queue.popleft()
        c = queue.popleft()
        if tmp == top_priority:
            count += 1
            if chr(location + 65) == c:
                return count
            top_priority = priority.popleft()
        else:
            priority_queue.append(tmp)
            queue.append(c)
    return null    