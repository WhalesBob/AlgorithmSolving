import sys
n, k = map(int, input().split())

number_to_add = 1
while number_to_add * 2 <= n:
    number_to_add *= 2

if number_to_add == n:
    print(0)
    sys.exit(0)

while k > 1:
    n -= number_to_add
    k -= 1
    while number_to_add >= n:
        number_to_add /= 2

print(int(number_to_add * 2 - n))