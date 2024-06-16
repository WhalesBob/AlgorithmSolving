from sys import stdin
import sys
from collections import deque

people_count, relation_count, have_money = map(int, stdin.readline().split())

friend_money = list(map(int, stdin.readline().split()))
friend = [(i, friend_money[i-1]) for i in range(1, people_count + 1)]

friend_dict = dict()

for i in range(1, people_count + 1):
    friend_dict[i] = set()

for _ in range(relation_count):
    a, b = map(int, stdin.readline().split())
    friend_dict[a].add(b)
    friend_dict[b].add(a)

visit = [False] * (people_count + 1)
visit_queue = deque()

friend.sort(key=lambda x: x[1])

need_money = 0

for person in friend:
    p, money = person
    if visit[p]:
        continue

    visit[p] = True
    need_money += money

    if need_money > have_money:
        print("Oh no")
        sys.exit(0)

    for f in friend_dict[p]:
        visit[f] = True
        visit_queue.append(f)

    while len(visit_queue) > 0:
        _p = visit_queue.popleft()
        for f in friend_dict[_p]:
            if not visit[f]:
                visit[f] = True
                visit_queue.append(f)

print(need_money)