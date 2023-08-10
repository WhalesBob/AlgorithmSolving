from sys import stdin
import sys

n = int(stdin.readline())
array = list(map(int, stdin.readline().split()))
global min_value, min_positive, min_negative
min_value = 10 ** 100

def binary_search(positive_value, negative_array):
    global min_value, min_positive, min_negative
    left, right, answer = 0, len(negative_array) - 1, 0

    while left <= right:
        mid = (left + right) // 2
        if positive_value + negative_array[mid] == 0:
            print(negative_array[mid], positive_value)
            sys.exit(0)

        value = positive_value + negative_array[mid]
        if abs(value) < min_value:
            min_value = abs(value)
            min_positive, min_negative = positive_value, negative_array[mid]

        if value > 0:
            right = mid - 1
        else:
            left = mid + 1

if array[0] * array[-1] > 0:
    if array[-1] < 0:
        print("{0} {1}".format(array[-2], array[-1]))
    else:
        print("{0} {1}".format(array[0], array[1]))
else:
    first_positive = 0
    for i in range(len(array)):
        if array[i] < 0:
            first_positive = i

    first_positive += 1
    for positive_value in array[first_positive:]:
        binary_search(positive_value, array[:first_positive])

    if first_positive + 1 < len(array) and min_value > array[first_positive] + array[first_positive + 1]:
        print(array[first_positive], array[first_positive + 1])
    elif min_value > abs(array[first_positive - 1] + array[first_positive - 2]):
        print(array[first_positive - 2], array[first_positive - 1])
    else:
        print(min_negative, min_positive)