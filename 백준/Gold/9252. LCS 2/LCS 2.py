from sys import stdin

input_a = stdin.readline().strip()
input_b = stdin.readline().strip()

len_a = len(input_a) + 1
len_b = len(input_b) + 1

lcs_table = [[0 for _ in range(len_a)] for _ in range(len_b)]

for b in range(1, len_b):
    for a in range(1, len_a):
        lcs_table[b][a] = lcs_table[b-1][a-1] + 1 if input_a[a-1] == input_b[b-1] else max(lcs_table[b-1][a], lcs_table[b][a-1])

loc_a, loc_b = len_a - 1, len_b - 1
common_str = []
print(lcs_table[loc_b][loc_a])


while lcs_table[loc_b][loc_a] > 0:
    if lcs_table[loc_b][loc_a] == lcs_table[loc_b - 1][loc_a]:
        loc_b -= 1
    elif lcs_table[loc_b][loc_a] == lcs_table[loc_b][loc_a - 1]:
        loc_a -= 1
    else:
        common_str.append(input_a[loc_a - 1])
        loc_b -= 1
        loc_a -= 1

common_str.reverse()
print("".join(common_str))