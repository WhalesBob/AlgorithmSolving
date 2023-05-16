from itertools import combinations
from collections import deque
from sys import stdout

l, c = map(int, input().split())
total_string = sorted(input().replace(" ", ""))
mother_char = ['a', 'e', 'i', 'o', 'u']

mother = deque()
index = 0

for i in range(len(total_string)):
    if total_string[i] in mother_char:
        mother.append(total_string[i])

while len(mother) != 0 and index <= len(total_string) - l:
    first_letter = total_string[index]
    if first_letter in mother_char:
        mother.popleft()

    for element in combinations(total_string[index + 1:], l - 1):
        have_mother = (first_letter in mother_char)
        son_count = 0 if have_mother else 1
        append_str = first_letter

        for i in range(len(element)):
            append_str += element[i]
            if element[i] in mother_char:
                have_mother = True
            else:
                son_count += 1

        if have_mother and son_count >= 2:
            stdout.write(append_str + "\n")

    index += 1