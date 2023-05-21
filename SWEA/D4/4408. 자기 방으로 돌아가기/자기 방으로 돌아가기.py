case = int(input())

for c in range(case):
    check_list = [0] * 201
    n = int(input())
    for _ in range(n):
        a, b = map(int, input().split())
        a, b = min(a,b), max(a,b)
        a = (a+1) // 2
        b = (b+1) // 2
        for i in range(a, b+1):
            check_list[i] += 1

    print("#{} {}".format(c+1, max(check_list)))