import sys
n, k = map(int, input().split())
if k <= 2:
    answer = n+1 if k == 2 else 1
    print(answer)
    sys.exit(0)

array = list(range(n+1, 0, -1))

for i in range(3, k+1):
    new_array = [sum(array[j:]) for j in range(len(array))]
    array = new_array

print(array[0] % 1_000_000_000)