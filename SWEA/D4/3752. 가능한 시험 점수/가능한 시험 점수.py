case = int(input())

for c in range(case):
    n = int(input())
    array = sorted(map(int, input().split()))

    all_sum = sum(array)
    visit_array = [0 for _ in range(all_sum+1)]
    visit_array[0] = 1

    for element in array:
        for i in range(len(visit_array)-1, -1, -1):
            if visit_array[i] != 0:
                visit_array[i+element] += 1

    answer = [visit_array[i] for i in range(len(visit_array)) if visit_array[i] != 0]
    print("#{0} {1}".format(c+1, len(answer)))