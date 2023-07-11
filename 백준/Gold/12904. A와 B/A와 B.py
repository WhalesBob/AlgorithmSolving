from sys import stdin

s = stdin.readline().strip()
t = stdin.readline().strip()

def add_A_reverse(input):
    return input[:len(input) - 1]

def reverse_and_add_B_reverse(input):
    return (input[:len(input) - 1])[::-1]

start, terminate = s, t
while start != terminate and len(start) < len(terminate):
    if terminate[-1] == "A":
        terminate = add_A_reverse(terminate)
    elif terminate[-1] == "B":
        terminate = reverse_and_add_B_reverse(terminate)

print(1 if start == terminate else 0)