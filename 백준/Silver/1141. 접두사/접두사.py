from sys import stdin

n = int(stdin.readline())
array = sorted([stdin.readline().rstrip() for _ in range(n)], key=lambda k: len(k), reverse=True)
s = set()

for element in array:
    contain = False
    if len(s) != 0:
        for added_element in s:
            if added_element.find(element) == 0:
                contain = True
                break

    if not contain:
        s.add(element)

print(len(s))