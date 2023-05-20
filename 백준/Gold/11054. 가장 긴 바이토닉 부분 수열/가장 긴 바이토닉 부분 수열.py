length = int(input())
array = list(map(int, input().split(" ")))

increase_array = [0] * len(array)
decrease_array = [0] * len(array)
increase_array[0] = decrease_array[len(decrease_array) - 1] = 1

for take_idx in range(1, len(array)):
    compare_max, compare_idx = -1, -1
    for c in range(take_idx):
        if increase_array[compare_idx] <= increase_array[c] and array[c] < array[take_idx]:
            compare_max = array[c]
            compare_idx = c

    increase_array[take_idx] = 1 if compare_max == -1 else increase_array[compare_idx] + 1

for take_idx in range(len(array)-2, -1, -1):
    compare_max, compare_idx = -1, -1
    for c in range(len(array)-1, take_idx, -1):
        if decrease_array[compare_idx] <= decrease_array[c] and array[c] < array[take_idx]:
            compare_max = array[c]
            compare_idx = c

    decrease_array[take_idx] = 1 if compare_max == -1 else decrease_array[compare_idx] + 1

answer = sorted([(increase_array[i] + decrease_array[i] - 1) for i in range(len(array))], reverse=True)

print(answer[0], end="")