import math

def solution(left, right):
    sum = 0
    for i in range(left, right+1):
        if (math.ceil(math.sqrt(i)) == math.sqrt(i)):
            sum -= i
        else:
            sum += i
    return sum        