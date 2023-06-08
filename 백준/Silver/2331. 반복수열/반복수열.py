a, p = map(int, input().split())

def take_number(n, p):
    str_n = str(n)
    sum = 0
    for s in str_n:
        sum += (int(s) ** p)
    return sum

num_set = set()
num_set.add(a)

last_num = a
start_repeat = False
while True:
    new_num = take_number(last_num, p)
    if new_num in num_set:
        if not start_repeat:
            start_repeat = True
        num_set.remove(new_num)

    else:
        if start_repeat:
            break
        else:
            num_set.add(new_num)

    last_num = new_num

print(len(num_set))